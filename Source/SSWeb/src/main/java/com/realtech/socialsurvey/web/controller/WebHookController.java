package com.realtech.socialsurvey.web.controller;

// JIRA: SS-15: By RM03

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.braintreegateway.WebhookNotification;
import com.realtech.socialsurvey.core.enums.DisplayMessageType;
import com.realtech.socialsurvey.core.exception.InvalidInputException;
import com.realtech.socialsurvey.core.services.payment.Payment;
import com.realtech.socialsurvey.core.utils.MessageUtils;
import com.realtech.socialsurvey.web.common.JspResolver;

/**
 * Handles the web hooks and recieves the notifications from Braintree.
 */

@Controller
public class WebHookController {

	@Autowired
	Payment gateway;

	@Autowired
	MessageUtils messageUtils;

	private static final Logger LOG = LoggerFactory.getLogger(WebHookController.class);

	/**
	 * Webhook for verifying the subscription hook by the GET request from Braintree.
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/webhook/subscription", method = RequestMethod.GET)
	public @ResponseBody
	Object subscribeNotification(HttpServletRequest request, HttpServletResponse response) {

		LOG.info("Notification Recieved!");

		String challenge = gateway.getGatewayInstance().webhookNotification().verify(request.getParameter("bt_challenge"));
		LOG.info("Recieved challenge : " + challenge);
		response.setContentType("text/html");
		return challenge;
	}

	/**
	 * Webhook for accepting subscription notifications from Braintree.
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/webhook/subscription", method = RequestMethod.POST)
	public @ResponseBody
	Object getSubscriptionNotifications(Model model, HttpServletRequest request, HttpServletResponse response) {

		LOG.info("Subscription notification recieved!");

		WebhookNotification webhookNotification = gateway.getGatewayInstance().webhookNotification()
				.parse(request.getParameter("bt_signature"), request.getParameter("bt_payload"));

		LOG.info("Webhook Received " + webhookNotification.getTimestamp().getTime() + " | Kind: " + webhookNotification.getKind()
				+ " | Subscription: " + webhookNotification.getSubscription().getId());

		try {

			if (webhookNotification.getKind() == WebhookNotification.Kind.SUBSCRIPTION_WENT_PAST_DUE) {
				gateway.retryPaymentAndUpdateLicenseTable(webhookNotification.getSubscription());
			}

		}
		catch (InvalidInputException e) {

			LOG.error("WebHookController getSubscriptionNotifications() : InvalidInput Exception thrown : "
					+ messageUtils.getDisplayMessage(e.getErrorCode(), DisplayMessageType.ERROR_MESSAGE));
			model.addAttribute("message", messageUtils.getDisplayMessage(e.getErrorCode(), DisplayMessageType.ERROR_MESSAGE));
			return JspResolver.MESSAGE_HEADER;

		}

		LOG.info("Subscription Notification handled!");
		response.setStatus(200);
		return ("");

	}

}
