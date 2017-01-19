package in.hopscotch.moments.repository;

import java.util.List;

import in.hopscotch.moments.entity.HSMomentsData;

public interface HSMomentsRepository {
    
    public List<HSMomentsData> getHSMomentsData(boolean newest, int pageNo, int pageSize);

}
