package in.hopscotch.moments.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import in.hopscotch.moments.api.response.ChildInfo;
import in.hopscotch.moments.api.response.DeliveredProducts;
import in.hopscotch.moments.api.response.UploadImagePageResponse;
import in.hopscotch.moments.api.response.UploadInfo;

public interface UploadHSMomentsPhotosService {

    UploadInfo uploadImageFile(MultipartHttpServletRequest request, HttpServletResponse response);
    
    List<DeliveredProducts> getDeliveredProductInfo(Integer customerId);
    
    List<ChildInfo> getChildInfo(Integer customerId);
    
    UploadImagePageResponse getUploadPageInfo(Integer customerId);
    
    Integer getCustomerId(String uuId);

}
