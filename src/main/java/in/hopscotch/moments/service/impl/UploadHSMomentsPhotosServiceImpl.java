package in.hopscotch.moments.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.hopscotch.moments.entity.HSMomentsData;
import in.hopscotch.moments.repository.UploadHSMomentsPhotosRepository;
import in.hopscotch.moments.service.UploadHSMomentsPhotosService;

@Service
public class UploadHSMomentsPhotosServiceImpl implements UploadHSMomentsPhotosService {
    
    @Inject
    UploadHSMomentsPhotosRepository uploadHSMomentsPhotosRepository;

    @Override
    @Transactional
    public void uploadMomentData(HSMomentsData hsMomentsData) {
        uploadHSMomentsPhotosRepository.uploadMomentData(hsMomentsData);
    }
    
    

}
