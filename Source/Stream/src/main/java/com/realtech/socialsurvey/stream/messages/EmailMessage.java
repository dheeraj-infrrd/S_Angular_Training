package com.realtech.socialsurvey.stream.messages;

import java.io.Serializable;
import java.util.List;

/**
 * Email message for sending mail, contains all the attributes required for mail sending
 */

public class EmailMessage implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * Constants for indicating the recipient type, i.e mail to be sent as to/cc or bcc
     */
    public static final int RECIPIENT_TYPE_TO = 0;
    public static final int RECIPIENT_TYPE_CC = 1;
    public static final int RECIPIENT_TYPE_BCC = 2;

    private String randomUUID;
    private List<String> recipients;
    private String subject;
    private String body;
    private int recipientType;
    private String senderEmailId;
    private String senderName;
    private String senderPassword;
    private String sendEmailThrough;
    private String mailType;
    private long companyId;
    private boolean sendMailToSalesLead;
    private boolean holdSendingMail;
    private String surveySourceId;
    private List<String> recipientsName;
    private String branchName;
    private String regionName;
    private long branchId;
    private long regionId;
    private long agentId;
    private String agentEmailId;
    
    private List<EmailAttachment> attachments;


    public String getRandomUUID()
    {
        return randomUUID;
    }


    public void setRandomUUID( String uuId )
    {
        this.randomUUID = uuId;
    }


    public List<String> getRecipients()
    {
        return recipients;
    }


    public void setRecipients( List<String> recipients )
    {
        this.recipients = recipients;
    }


    public String getSubject()
    {
        return subject;
    }


    public void setSubject( String subject )
    {
        this.subject = subject;
    }


    public String getBody()
    {
        return body;
    }


    public void setBody( String body )
    {
        this.body = body;
    }


    public int getRecipientType()
    {
        return recipientType;
    }


    public void setRecipientType( int recipientType )
    {
        this.recipientType = recipientType;
    }


    public String getSenderEmailId()
    {
        return senderEmailId;
    }


    public void setSenderEmailId( String senderEmailId )
    {
        this.senderEmailId = senderEmailId;
    }


    public String getSenderName()
    {
        return senderName;
    }


    public void setSenderName( String senderName )
    {
        this.senderName = senderName;
    }


    public String getSenderPassword()
    {
        return senderPassword;
    }


    public void setSenderPassword( String senderPassword )
    {
        this.senderPassword = senderPassword;
    }


    public String getSendEmailThrough()
    {
        return sendEmailThrough;
    }


    public void setSendEmailThrough( String sendEmailThrough )
    {
        this.sendEmailThrough = sendEmailThrough;
    }


    public String getMailType()
    {
        return mailType;
    }


    public void setMailType( String mailType )
    {
        this.mailType = mailType;
    }


    public long getCompanyId()
    {
        return companyId;
    }


    public void setCompanyId( long companyId )
    {
        this.companyId = companyId;
    }


    public boolean isSendMailToSalesLead()
    {
        return sendMailToSalesLead;
    }


    public void setSendMailToSalesLead( boolean sendMailToSalesLead )
    {
        this.sendMailToSalesLead = sendMailToSalesLead;
    }


    public boolean isHoldSendingMail()
    {
        return holdSendingMail;
    }


    public void setHoldSendingMail( boolean holdSendingMail )
    {
        this.holdSendingMail = holdSendingMail;
    }


    public List<EmailAttachment> getAttachments()
    {
        return attachments;
    }


    public void setAttachments( List<EmailAttachment> attachments )
    {
        this.attachments = attachments;
    }

    public String getSurveySourceId() {
        return surveySourceId;
    }

    public void setSurveySourceId(String surveySourceId) {
        this.surveySourceId = surveySourceId;
    }

    public List<String> getRecipientsName() {
        return recipientsName;
    }

    public void setRecipientsName(List<String> recipientsName) {
        this.recipientsName = recipientsName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public long getBranchId() {
        return branchId;
    }

    public void setBranchId(long branchId) {
        this.branchId = branchId;
    }

    public long getRegionId() {
        return regionId;
    }

    public void setRegionId(long regionId) {
        this.regionId = regionId;
    }

    public long getAgentId() {
        return agentId;
    }

    public void setAgentId(long agentId) {
        this.agentId = agentId;
    }

    public String getAgentEmailId() {
        return agentEmailId;
    }

    public void setAgentEmailId(String agentEmailId) {
        this.agentEmailId = agentEmailId;
    }

    @Override
    public String toString() {
        return "EmailMessage{" +
                "randomUUID='" + randomUUID + '\'' +
                ", recipients=" + recipients +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", recipientType=" + recipientType +
                ", senderEmailId='" + senderEmailId + '\'' +
                ", senderName='" + senderName + '\'' +
                ", senderPassword='" + senderPassword + '\'' +
                ", sendEmailThrough='" + sendEmailThrough + '\'' +
                ", mailType='" + mailType + '\'' +
                ", companyId=" + companyId +
                ", sendMailToSalesLead=" + sendMailToSalesLead +
                ", holdSendingMail=" + holdSendingMail +
                ", surveySourceId='" + surveySourceId + '\'' +
                ", recipientsName=" + recipientsName +
                ", branchName='" + branchName + '\'' +
                ", regionName='" + regionName + '\'' +
                ", branchId=" + branchId +
                ", regionId=" + regionId +
                ", agentId=" + agentId +
                ", agentEmailId='" + agentEmailId + '\'' +
                ", attachments=" + attachments +
                '}';
    }
}

// JIRA: SS-7: By RM02: EOC