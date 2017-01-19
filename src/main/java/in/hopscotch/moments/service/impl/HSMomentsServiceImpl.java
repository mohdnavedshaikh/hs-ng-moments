package in.hopscotch.moments.service.impl;

import java.util.List;

import javax.inject.Inject;

import in.hopscotch.moments.entity.HSMomentsData;
import in.hopscotch.moments.repository.HSMomentsRepository;
import in.hopscotch.moments.service.HSMomentsService;

public class HSMomentsServiceImpl implements HSMomentsService {

    @Inject
    HSMomentsRepository hsMomentsRepository;

    @Override
    public List<HSMomentsData> getHSMomentsData(boolean newest, int pageNo, int pageSize) {
        return hsMomentsRepository.getHSMomentsData(newest, pageNo, pageSize);
    }

}
