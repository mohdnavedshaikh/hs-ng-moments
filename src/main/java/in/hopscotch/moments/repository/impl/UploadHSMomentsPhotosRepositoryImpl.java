package in.hopscotch.moments.repository.impl;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import in.hopscotch.moments.api.request.ImageFileRequest;
import in.hopscotch.moments.api.response.ImageResponse;
import in.hopscotch.moments.db.util.JPAAccess;
import in.hopscotch.moments.helper.UploadStrategy;
import in.hopscotch.moments.repository.UploadHSMomentsPhotosRepository;
import in.hopscotch.moments.util.S3Client;

@Repository
public class UploadHSMomentsPhotosRepositoryImpl implements UploadHSMomentsPhotosRepository {

    @Inject
    S3Client s3Client;
    @Inject
    JPAAccess jpaAccess;

    @Value("${CommonUploadsTStrategy}")
    String uploadStrategy;

    public ImageResponse uploadImageFile(ImageFileRequest imageFileRequest) throws Exception {
        String className = uploadStrategy;
        UploadStrategy uploadStrategy = (UploadStrategy) Class.forName(className).newInstance();
        uploadStrategy.setJpaAccess(jpaAccess);
        uploadStrategy.setS3Client(s3Client);
        return uploadStrategy.uploadImageFile(imageFileRequest, new Date());
    }

}
