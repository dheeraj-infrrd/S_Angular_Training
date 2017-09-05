package com.realtech.socialsurvey.core.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table ( name = "overview_company_year")
public class OverviewCompanyYear implements Serializable
{


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    @Column ( name = "overview_company_year_id")
    private String overviewCompanyYearId;

    @Column ( name = "company_id")
    private long companyId;

    @Column ( name = "processed")
    private int processed;

    @Column ( name = "completed")
    private int completed;

    @Column ( name = "incomplete")
    private int incomplete;

    @Column ( name = "social_posts")
    private int socialPosts;

    @Column ( name = "zillow_reviews")
    private int zillowReviews;

    @Column ( name = "unprocessed")
    private int unprocessed;

    @Column ( name = "unassigned")
    private int unassigned;

    @Column ( name = "duplicate")
    private int duplicate;

    @Column ( name = "corrupted")
    private int corrupted;

    @Column ( name = "other")
    private int other;

    @Column ( name = "complete_percentage")
    private float completePercentage;

    @Column ( name = "incomplete_percentage")
    private float incompletePercentage;

    @Column ( name = "rating")
    private float rating;

    @Column ( name = "total_review")
    private int totalReview;

    @Column ( name = "year")
    private int year;

    public String getOverviewCompanyYearId()
    {
        return overviewCompanyYearId;
    }

    public void setOverviewCompanyYearId( String overviewCompanyYearId )
    {
        this.overviewCompanyYearId = overviewCompanyYearId;
    }

    public long getCompanyId()
    {
        return companyId;
    }

    public void setCompanyId( long companyId )
    {
        this.companyId = companyId;
    }

    public int getProcessed()
    {
        return processed;
    }

    public void setProcessed( int processed )
    {
        this.processed = processed;
    }

    public int getCompleted()
    {
        return completed;
    }

    public void setCompleted( int completed )
    {
        this.completed = completed;
    }

    public int getIncomplete()
    {
        return incomplete;
    }

    public void setIncomplete( int incomplete )
    {
        this.incomplete = incomplete;
    }

    public int getSocialPosts()
    {
        return socialPosts;
    }

    public void setSocialPosts( int socialPosts )
    {
        this.socialPosts = socialPosts;
    }

    public int getZillowReviews()
    {
        return zillowReviews;
    }

    public void setZillowReviews( int zillowReviews )
    {
        this.zillowReviews = zillowReviews;
    }

    public int getUnprocessed()
    {
        return unprocessed;
    }

    public void setUnprocessed( int unprocessed )
    {
        this.unprocessed = unprocessed;
    }

    public int getUnassigned()
    {
        return unassigned;
    }

    public void setUnassigned( int unassigned )
    {
        this.unassigned = unassigned;
    }

    public int getDuplicate()
    {
        return duplicate;
    }

    public void setDuplicate( int duplicate )
    {
        this.duplicate = duplicate;
    }

    public int getCorrupted()
    {
        return corrupted;
    }

    public void setCorrupted( int corrupted )
    {
        this.corrupted = corrupted;
    }

    public int getOther()
    {
        return other;
    }

    public void setOther( int other )
    {
        this.other = other;
    }

    public float getCompletePercentage()
    {
        return completePercentage;
    }

    public void setCompletePercentage( float completePercentage )
    {
        this.completePercentage = completePercentage;
    }

    public float getIncompletePercentage()
    {
        return incompletePercentage;
    }

    public void setIncompletePercentage( float incompletePercentage )
    {
        this.incompletePercentage = incompletePercentage;
    }

    public float getRating()
    {
        return rating;
    }

    public void setRating( float rating )
    {
        this.rating = rating;
    }

    public int getTotalReview()
    {
        return totalReview;
    }

    public void setTotalReview( int totalReview )
    {
        this.totalReview = totalReview;
    }

    public int getYear()
    {
        return year;
    }

    public void setYear( int year )
    {
        this.year = year;
    }

    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }

    @Override
    public String toString()
    {
        return "OverviewCompanyYear [overviewCompanyYearId=" + overviewCompanyYearId + ", companyId=" + companyId
            + ", processed=" + processed + ", completed=" + completed + ", incomplete=" + incomplete + ", socialPosts="
            + socialPosts + ", zillowReviews=" + zillowReviews + ", unprocessed=" + unprocessed + ", unassigned=" + unassigned
            + ", duplicate=" + duplicate + ", corrupted=" + corrupted + ", other=" + other + ", completePercentage="
            + completePercentage + ", incompletePercentage=" + incompletePercentage + ", rating=" + rating + ", totalReview="
            + totalReview + ", year=" + year + "]";
    }
    
    

}
