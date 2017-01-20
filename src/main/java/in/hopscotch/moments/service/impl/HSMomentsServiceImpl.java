package in.hopscotch.moments.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import in.hopscotch.moments.api.request.ImageFileRequest;
import in.hopscotch.moments.api.response.ImageResponse;
import in.hopscotch.moments.entity.HSMomentsData;
import in.hopscotch.moments.repository.HSMomentsRepository;
import in.hopscotch.moments.service.HSMomentsService;

@Service
public class HSMomentsServiceImpl implements HSMomentsService {

    @Inject
    HSMomentsRepository hsMomentsRepository;

    @Override
    public List<HSMomentsData> getHSMomentsData(boolean newest, int pageNo, int pageSize) {
        return hsMomentsRepository.getHSMomentsData(newest, pageNo, pageSize);
    }

    @Override
    public void incrementLike(Long momentsPhotoId) {
        hsMomentsRepository.incrementLike(momentsPhotoId);
    }

    @Override
    public ImageResponse uploadImageFile(ImageFileRequest imageFileRequest) throws Exception {
        return hsMomentsRepository.uploadImageFile(imageFileRequest);
    }
}
