package in.hopscotch.moments.service;

import java.util.List;

import in.hopscotch.moments.api.request.ImageFileRequest;
import in.hopscotch.moments.api.response.ImageResponse;
import in.hopscotch.moments.entity.HSMomentsData;

public interface HSMomentsService {

    List<HSMomentsData> getHSMomentsData(boolean newest, int pageNo, int pageSize);

    void incrementLike(Long momentsPhotoId);

    HSMomentsData getHSMomentsById(Long momentsPhotoId);

    // ImageResponse uploadImageFile(ImageFileRequest imageFileRequest) throws Exception;
}
