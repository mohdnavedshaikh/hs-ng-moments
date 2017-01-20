package in.hopscotch.moments.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import in.hopscotch.moments.constant.NamedQueryConstant;
import in.hopscotch.moments.entity.HSMomentsData;
import in.hopscotch.moments.repository.HSMomentsRepository;

@Repository
public class HSMomentsRepositoryImpl extends AbstractRepository<HSMomentsData> implements HSMomentsRepository {


    @Override
    public List<HSMomentsData> getHSMomentsData(boolean newest, int pageNo, int pageSize) {
        List<HSMomentsData> hsMomentsResponse = findByPaginationUsingNamedQuery(newest ? NamedQueryConstant.HSMOMENTSDATA_NEWEST : NamedQueryConstant.HSMOMENTSDATA_POPULAR, pageNo - 1, pageSize);
        return hsMomentsResponse;
    }

    public void incrementLike(Long momentsPhotoId) {
        executeUpdateUsingNamedQuery(NamedQueryConstant.HSMOMENTSDATA_INCREMENT_LIKES);
    }

    @Override
    public HSMomentsData getHSMomentsById(Long momentsPhotoId) {
        return get(momentsPhotoId);
    }

}
