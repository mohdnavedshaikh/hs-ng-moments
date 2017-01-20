package in.hopscotch.moments.repository;

import in.hopscotch.moments.api.request.ImageFileRequest;
import in.hopscotch.moments.api.response.ImageResponse;

public interface UploadHSMomentsPhotosRepository {

    ImageResponse uploadImageFile(ImageFileRequest imageFileRequest) throws Exception;

}
