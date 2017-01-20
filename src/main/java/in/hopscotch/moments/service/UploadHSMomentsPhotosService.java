package in.hopscotch.moments.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import in.hopscotch.moments.api.response.ChildInfo;
import in.hopscotch.moments.api.response.DeliveredProducts;
import in.hopscotch.moments.api.response.UploadImagePageResponse;
import in.hopscotch.moments.api.response.UploadInfo;
import in.hopscotch.moments.entity.helper.CustomerInfo;

public interface UploadHSMomentsPhotosService {

    UploadInfo uploadImageFile(MultipartHttpServletRequest request, HttpServletResponse response);
    
    List<DeliveredProducts> getDeliveredProductInfo(Integer customerId);
    
    List<ChildInfo> getChildInfo(Integer customerId);
    
    UploadImagePageResponse getUploadPageInfo(String uuId);
    
    CustomerInfo getCustomerId(String uuId);

}
