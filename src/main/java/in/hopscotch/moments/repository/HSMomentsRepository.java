package in.hopscotch.moments.repository;

import java.util.List;

import in.hopscotch.moments.api.response.ChildInfo;
import in.hopscotch.moments.api.response.DeliveredProducts;
import in.hopscotch.moments.entity.HSMomentsData;

public interface HSMomentsRepository {
    
    public List<HSMomentsData> getHSMomentsData(boolean newest, int pageNo, int pageSize);
    
    public List<DeliveredProducts> getDeliveredProductInfo(Integer customerId);
    
    public List<ChildInfo> getChildInfo(Integer customerId);

}
