package in.hopscotch.moments.service;

import java.util.List;

import in.hopscotch.moments.entity.HSMomentsData;

public interface HSMomentsService {

    public List<HSMomentsData> getHSMomentsData(boolean newest, int pageNo, int pageSize);
}
