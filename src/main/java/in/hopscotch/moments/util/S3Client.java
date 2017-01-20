package in.hopscotch.moments.util;

import java.io.File;

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

public class S3Client {

    private String bucketName;

    private String resourcePrefix;

    private final AmazonS3 s3Client;

    public S3Client(String region) {
        s3Client = new AmazonS3Client(new ClasspathPropertiesFileCredentialsProvider());
        if (StringUtils.hasText(region)) {
            s3Client.setRegion(Region.getRegion(Regions.fromName(region)));
        }
    }

    public PutObjectResult uploadFile(String key, File file) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setCacheControl("max-age=2592000");
        PutObjectRequest putObjectRequest = new PutObjectRequest(this.getBucketName(), this.getResourcePrefix() + key, file).withCannedAcl(CannedAccessControlList.PublicReadWrite)
                .withMetadata(metadata);
        return s3Client.putObject(putObjectRequest);
    }

    public void deleteFile(String key) {
        s3Client.deleteObject(this.getBucketName(), this.getResourcePrefix() + key);
    }

    public void copyFile(String oldKey, String newKey) {
        try {
            s3Client.copyObject(new CopyObjectRequest(this.getBucketName(), this.getResourcePrefix() + oldKey, this.getBucketName(), this.getResourcePrefix() + newKey)
                    .withCannedAccessControlList(CannedAccessControlList.PublicReadWrite));
        } catch (AmazonS3Exception e) {
            return;
        }
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getResourcePrefix() {
        return resourcePrefix;
    }

    public void setResourcePrefix(String resourcePrefix) {
        this.resourcePrefix = resourcePrefix;
    }
}
