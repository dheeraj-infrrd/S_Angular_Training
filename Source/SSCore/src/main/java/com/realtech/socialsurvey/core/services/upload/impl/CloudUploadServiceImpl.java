package com.realtech.socialsurvey.core.services.upload.impl;

import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.realtech.socialsurvey.core.exception.FatalException;
import com.realtech.socialsurvey.core.exception.InvalidInputException;
import com.realtech.socialsurvey.core.services.upload.FileUploadService;
import com.realtech.socialsurvey.core.utils.DisplayMessageConstants;
import com.realtech.socialsurvey.core.utils.EncryptionHelper;

@Component
public class CloudUploadServiceImpl implements FileUploadService {

	private static final Logger LOG = LoggerFactory.getLogger(CloudUploadServiceImpl.class);
	private static final String CACHE_PUBLIC = "public";

	@Autowired
	private EncryptionHelper encryptionHelper;

	@Autowired
	private UploadUtils uploadUtils;

	@Value("${AMAZON_ENDPOINT}")
	private String endpoint;

	@Value("${AMAZON_BUCKET}")
	private String bucket;

	@Value("${AMAZON_ENV_PREFIX}")
	private String envPrefix;

	@Value("${AMAZON_ACCESS_KEY}")
	private String accessKey;

	@Value("${AMAZON_SECRET_KEY}")
	private String secretKey;

	@Override
	public String fileUploadHandler(MultipartFile fileLocal, String logoName) throws InvalidInputException {
		LOG.info("Method fileUploadHandler inside AmazonUploadServiceImpl called");

		if (!fileLocal.isEmpty()) {
			try {
				File convFile = new File(fileLocal.getOriginalFilename());
				fileLocal.transferTo(convFile);
				uploadUtils.validateFile(convFile);

				StringBuilder amazonFileName = new StringBuilder(envPrefix).append("-");
				amazonFileName.append(encryptionHelper.encryptSHA512(logoName + (System.currentTimeMillis())));
				amazonFileName.append(logoName.substring(logoName.lastIndexOf(".")));

				PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, amazonFileName.toString(), convFile);
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setCacheControl(CACHE_PUBLIC);
				putObjectRequest.setMetadata(metadata);
				putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead);
				AmazonS3 s3Client = createAmazonClient(endpoint, bucket);

				PutObjectResult result = s3Client.putObject(putObjectRequest);
				LOG.info("Amazon Upload Etag: " + result.getETag());
				LOG.info("Amazon file URL: " + amazonFileName.toString());
				return amazonFileName.toString();
			}
			catch (IOException e) {
				LOG.error("IOException occured while reading file. Reason : " + e.getMessage(), e);
				throw new FatalException("IOException occured while reading file. Reason : " + e.getMessage(), e);
			}
		}
		else {
			LOG.error("Method fileUploadHandler inside AmazonUploadServiceImpl failed to upload");
			throw new InvalidInputException("Upload failed: " + logoName + " because the file was empty", DisplayMessageConstants.INVALID_LOGO_FILE);
		}
	}

	/**
	 * Method to create AmazonS3 client
	 */
	private AmazonS3 createAmazonClient(String endpoint, String bucket) {
		LOG.debug("Creating Amazon S3 Client");
		BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		Region region = Region.getRegion(Regions.US_WEST_1);

		AmazonS3 s3Client = new AmazonS3Client(credentials);
		s3Client.setRegion(region);
		s3Client.setEndpoint(endpoint);

		if (!s3Client.doesBucketExist(bucket)) {
			throw new FatalException("Bucket for Logo upload does not exists");
		}
		LOG.debug("Returning Amazon S3 Client");
		return s3Client;
	}
}