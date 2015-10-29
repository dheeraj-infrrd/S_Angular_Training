package com.realtech.socialsurvey.version1.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;


public class HomePage extends BasePage
{

    private static final Logger LOG = LoggerFactory.getLogger( HomePage.class );

    public static final String HOME_PAGE_TITLE = "Professional Reputation Management | SocialSurvey.me";
    public static final String FORGOT_PASSWORD_PAGE_LOCATOR = "id=forgot-pwd";
    public static final String PRIVACY_PAGE_LOCATOR = "link=Privacy Policy";
    public static final String PRICING_PAGE_LOCATOR = "link=Pricing";
    public static final String FEATURES_PAGE_LOCATOR = "link=Features";
    public static final String TERMS_PAGE_LOCATOR = "link=Terms of Service";
    public static final String DASHBOARD_LOGOUT_LOCATOR = "id=user-logout";
    public static final String USER_NAME_LOCATOR = "id=login-user-id";
    public static final String USER_PASSWORD_LOCATOR = "id=login-pwd";
    public static final String FIRST_NAME_LOCATOR = "name=firstName";
    public static final String LAST_NAME_LOCATOR = "name=lastName";
    public static final String EMAIL_ID_LOCATOR = "name=emailId";
    private static final String PRO_FIRST_NAME_LOCATOR = "id=find-pro-first-name";
    private static final String PRO_LAST_NAME_LOCATOR = "id=find-pro-last-name";

    public static final String TOAST_MESSAGE_LOCATOR = "id=overlay-toast";
    public static final String INVALID_EMAILID_MESSAGE = "Please enter a valid user name.";


    public HomePage( WebDriver driver )
    {
        super( driver );
        if ( !getTitle().equals( HOME_PAGE_TITLE ) ) {
            Assert.fail( "This is not home page. Current page is: " + getTitle() );
        }

        waitForAjax();
    }


    /**
     * 
     * 
     * @param locators
     * @return
     */
    public boolean navigateToPage( String... locators )
    {

        //now process the locators      
        for ( int index = 0; index < locators.length; index++ ) {
            //Get current element locator
            String locator = locators[index];

            //check if current element is clickable
            if ( !isElementClickable( locator ) ) {
                return false;
            }

            //check if there's a child element
            if ( index < ( locators.length - 1 ) ) {
                //if there is, check if it is visible
                WebElement childElement = getElement( locators[index + 1] );

                //if it is, then don't click on the current element, just continue with the for loop
                if ( isVisible( childElement ) ) {
                    continue;
                }
            }

            //if there are no more child elements, or if the child isn't visible, then click on the current element
            waitForElementToAppear( locator ).click();
        }

        waitForAjax();

        return true;
    }


    public ForgotPasswordPage clickForgotPasswordLink()
    {
        navigateToForgotPasswordPage();
        waitForAjax();
        return new ForgotPasswordPage( driver );
    }


    public void navigateToForgotPasswordPage()
    {
        navigateToPage( FORGOT_PASSWORD_PAGE_LOCATOR );
    }


    public PricingPage clickPricingLink()
    {
        navigateToPricingPage();
        waitForAjax();
        return new PricingPage( driver );
    }


    public void navigateToPricingPage()
    {
        navigateToPage( PRICING_PAGE_LOCATOR );
    }


    public PrivacyPage clickPrivacyLink()
    {
        navigateToPrivacyPage();
        waitForAjax();
        return new PrivacyPage( driver );
    }


    public void navigateToPrivacyPage()
    {
        navigateToPage( PRIVACY_PAGE_LOCATOR );
    }


    public FeaturesPage clickFeaturesLink()
    {
        navigateToFeaturesPage();
        waitForAjax();
        return new FeaturesPage( driver );
    }


    public void navigateToFeaturesPage()
    {
        navigateToPage( FEATURES_PAGE_LOCATOR );
    }


    public TermsPage clickTermsLink()
    {
        navigateToTermsPage();
        waitForAjax();
        return new TermsPage( driver );
    }


    public void navigateToTermsPage()
    {
        navigateToPage( TERMS_PAGE_LOCATOR );
    }


    public DashboardPage loginUser( String userNameString, String passwordString )
    {

        WebElement userName = getElement( USER_NAME_LOCATOR );
        userName.clear();
        userName.sendKeys( userNameString );

        WebElement password = getElement( USER_PASSWORD_LOCATOR );
        password.clear();
        password.sendKeys( passwordString );

        //Press enter to login
        password.sendKeys( Keys.ENTER );


        waitForAjax();
        return new DashboardPage( driver );
    }


    public RegistrationSuccessPage registerUser( String firstNameString, String lastNameString, String emailIdString )
    {
        LOG.trace( "Register user with firstName : " + firstNameString + ", lastName : " + lastNameString + " &emailId : "
            + emailIdString );


        WebElement firstName = getElement( FIRST_NAME_LOCATOR );
        firstName.clear();
        firstName.sendKeys( firstNameString );

        WebElement lastName = getElement( LAST_NAME_LOCATOR );
        lastName.clear();
        lastName.sendKeys( lastNameString );

        WebElement emailId = getElement( EMAIL_ID_LOCATOR );
        emailId.clear();
        emailId.sendKeys( emailIdString );

        //press enter key
        emailId.sendKeys( Keys.ENTER );


        waitForAjax();
        return new RegistrationSuccessPage( driver );
    }


    public FinProPage findAPro( String proFirstNameString, String proLastNameString )
    {

        WebElement proFirstName = getElement( PRO_FIRST_NAME_LOCATOR );
        proFirstName.clear();
        proFirstName.sendKeys( proFirstNameString );

        WebElement proLastName = getElement( PRO_LAST_NAME_LOCATOR );
        proLastName.clear();
        proLastName.sendKeys( proLastNameString );

        //press enter key
        proLastName.sendKeys( Keys.ENTER );

        waitForAjax();
        return new FinProPage( driver );

    }


    public HomePage logout()
    {
        navigateToPage( DASHBOARD_LOGOUT_LOCATOR );
        waitForAjax();
        return new HomePage( driver );
    }


    public String getToastMessage()
    {
        WebElement toastMessage = getElement( TOAST_MESSAGE_LOCATOR );
        return toastMessage.getText();
    }

}
