package in.hopscotch.moments.repository;

import java.util.List;

import in.hopscotch.moments.api.request.ImageFileRequest;
import in.hopscotch.moments.api.response.ChildInfo;
import in.hopscotch.moments.api.response.DeliveredProducts;
import in.hopscotch.moments.api.response.ImageResponse;
import in.hopscotch.moments.entity.helper.CustomerInfo;

public interface UploadHSMomentsPhotosRepository {

    ImageResponse uploadImageFile(ImageFileRequest imageFileRequest) throws Exception;
    
    List<DeliveredProducts> getDeliveredProductInfo(Integer customerId);

    List<ChildInfo> getChildInfo(Integer customerId);
    
    CustomerInfo getCustomerId(String uuid);

}
