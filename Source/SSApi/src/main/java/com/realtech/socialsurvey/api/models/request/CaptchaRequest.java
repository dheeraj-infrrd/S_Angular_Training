package com.realtech.socialsurvey.api.models.request;

import java.io.Serializable;


public class CaptchaRequest implements Serializable
{
    private String remoteAddress;
    private String captchaResponse;


    public String getRemoteAddress()
    {
        return remoteAddress;
    }


    public void setRemoteAddress( String remoteAddress )
    {
        this.remoteAddress = remoteAddress;
    }


    public String getCaptchaResponse()
    {
        return captchaResponse;
    }


    public void setCaptchaResponse( String captchaResponse )
    {
        this.captchaResponse = captchaResponse;
    }


    @Override public String toString()
    {
        return "CaptchaRequest{" +
            "remoteAddress='" + remoteAddress + '\'' +
            ", captchaResponse='" + captchaResponse + '\'' +
            '}';
    }
}