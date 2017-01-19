package in.hopscotch.moments.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import in.hopscotch.moments.api.response.HSMomentsResponse;
import in.hopscotch.moments.converter.BO2DTOConverter;
import in.hopscotch.moments.entity.HSMomentsData;
import in.hopscotch.moments.repository.HSMomentsRepository;
import in.hopscotch.moments.service.HSMomentsPageService;
import in.hopscotch.moments.service.HSMomentsService;

@Service
public class HSMomentsPageServiceImpl implements HSMomentsPageService {

    @Inject
    HSMomentsService hsMomentsService;
    
    @Inject
    BO2DTOConverter converter;

    @Override
    public HSMomentsResponse getHSMomentsPageData(boolean newest, int pageNo, int pageSize) {
        HSMomentsResponse hsMomentsResponse = new HSMomentsResponse();
        List<HSMomentsData> hsMomentsDatas = hsMomentsService.getHSMomentsData(newest, pageNo, pageSize);
        if (CollectionUtils.isEmpty(hsMomentsDatas))
            hsMomentsDatas = new ArrayList<HSMomentsData>();
        hsMomentsResponse.setMomentsPhotos(converter.convertListofHSMomentsDataToMomentsPhoto(hsMomentsDatas));
        return hsMomentsResponse;
    }

}
