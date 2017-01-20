package in.hopscotch.moments.repository;

import java.util.List;

import in.hopscotch.moments.api.request.ImageFileRequest;
import in.hopscotch.moments.api.response.ChildInfo;
import in.hopscotch.moments.api.response.DeliveredProducts;
import in.hopscotch.moments.api.response.ImageResponse;

public interface UploadHSMomentsPhotosRepository {

    ImageResponse uploadImageFile(ImageFileRequest imageFileRequest) throws Exception;
    
    List<DeliveredProducts> getDeliveredProductInfo(Integer customerId);

    List<ChildInfo> getChildInfo(Integer customerId);
    
    Integer getCustomerId(String uuid);

}
