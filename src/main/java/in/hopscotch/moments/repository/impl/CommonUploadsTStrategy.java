package in.hopscotch.moments.repository.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import in.hopscotch.moments.api.request.ImageFileRequest;
import in.hopscotch.moments.api.response.ImageArea;
import in.hopscotch.moments.api.response.ImageResponse;
import in.hopscotch.moments.entity.ImageLibrary;
import in.hopscotch.moments.helper.CompressImage;
import in.hopscotch.moments.helper.UploadStrategy;

@Component
public class CommonUploadsTStrategy extends UploadStrategy {

    @Override
    public ImageResponse uploadImageFile(ImageFileRequest imageFileRequest, Date sysdate) throws IOException {
        ImageLibrary imageLibrary = super.uploadImage(imageFileRequest, sysdate);
        final List<ImageArea> imageAreas = new ArrayList<ImageArea>();
        String imageDiskPath = FILESYSTEMPATH + imageLibrary.getImagePath();

        CompressImage compressImage = CompressImage.getInstance();

        Integer[] fullSize = getImgInfo(imageDiskPath);
        Integer[] bigSize = compressImage.compress(imageDiskPath, getBigFile(imageDiskPath), 420, 420, imageFileRequest.getContentType());
        Integer[] smallSize = compressImage.compress(imageDiskPath, getSmallFile(imageDiskPath), 70, 70, imageFileRequest.getContentType());
        // compressImage.compress(imageDiskPath, imageDiskPath, fullSize[0],
        // fullSize[1], imageFileRequest.getContentType());

        imageLibrary.setLargeSize(bigSize[0] + "," + bigSize[1]);
        imageLibrary.setThumbnailSize(smallSize[0] + "," + smallSize[1]);
        imageLibrary.setVersion(System.currentTimeMillis());
        jpaAccess.save(imageLibrary);

        super.uploadFileToS3(imageLibrary.getImagePath());

        imageAreas.add(new ImageArea("full", fullSize[0], fullSize[1]));
        imageAreas.add(new ImageArea("large", bigSize[0], bigSize[1]));
        imageAreas.add(new ImageArea("thumbnail", smallSize[0], smallSize[1]));
        return ImageResponse.createImageResponse(imageFileRequest.getModelName(), imageLibrary.getImageId(), imageLibrary.getImagePath(), imageLibrary.getVersion(), imageAreas);
    }

}