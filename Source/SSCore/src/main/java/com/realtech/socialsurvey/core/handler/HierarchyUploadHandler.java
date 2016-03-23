package com.realtech.socialsurvey.core.handler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.realtech.socialsurvey.core.starter.HierarchyUploadProcessor;

@Component
public class HierarchyUploadHandler
{
    public static final Logger LOG = LoggerFactory.getLogger( HierarchyUploadHandler.class );

    private ExecutorService executor;

    @Autowired
    private HierarchyUploadProcessor hierarchyUploadProcessor;


    public void startHierarchyUpload()
    {
        executor = Executors.newFixedThreadPool( 1 );
        executor.execute( hierarchyUploadProcessor );
    }
}