package in.hopscotch.moments.service;

import in.hopscotch.moments.api.response.HSMomentsResponse;

public interface HSMomentsPageService {
    
    public HSMomentsResponse getHSMomentsPageData(boolean newest, int pageNo, int pageSize);

}
