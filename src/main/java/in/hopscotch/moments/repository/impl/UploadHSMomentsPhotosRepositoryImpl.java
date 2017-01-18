package in.hopscotch.moments.repository.impl;

import org.springframework.stereotype.Repository;

import in.hopscotch.moments.entity.HSMomentsData;
import in.hopscotch.moments.repository.UploadHSMomentsPhotosRepository;

@Repository
public class UploadHSMomentsPhotosRepositoryImpl extends AbstractRepository implements UploadHSMomentsPhotosRepository {

    @Override
    public void uploadMomentData(HSMomentsData hsMomentsData) {
       this.jpaAccess.save(hsMomentsData);
    }

}
