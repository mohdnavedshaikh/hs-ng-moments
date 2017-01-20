package in.hopscotch.moments.api;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import in.hopscotch.moments.api.cookie.CookieContext;
import in.hopscotch.moments.api.request.HSMomentPhotoRequest;
import in.hopscotch.moments.api.response.UploadHSMomentPhotoInfo;
import in.hopscotch.moments.api.response.UploadInfo;
import in.hopscotch.moments.converter.DTO2BOConverter;
import in.hopscotch.moments.entity.HSMomentsData;
import in.hopscotch.moments.helper.ImageLibraryServiceHelper;
import in.hopscotch.moments.service.HSMomentsService;
import in.hopscotch.moments.service.UploadHSMomentsPhotosService;

@RestController
@RequestMapping("/hsmoments")
public class UploadHSMomentsPhotoController {

    @Inject
    UploadHSMomentsPhotosService uploadHSMomentsPhotosService;
    @Inject
    ImageLibraryServiceHelper imageLibraryServiceHelper;
    @Inject
    DTO2BOConverter  DTO2BOConverter;
    @Inject
    HSMomentsService hsMomentsService;
    @Inject
    CookieContext cookieContext;

    @RequestMapping(value = "/uploadMomentsPhoto", method = RequestMethod.POST)
    public UploadHSMomentPhotoInfo uploadImage(MultipartHttpServletRequest request, HttpServletResponse response) {
        UploadInfo uploadInfo = uploadHSMomentsPhotosService.uploadImageFile(request, response);
        UploadHSMomentPhotoInfo uploadHSMomentPhotoInfo = new UploadHSMomentPhotoInfo();
        uploadHSMomentPhotoInfo.setStatus(uploadInfo.isStatus());
        uploadHSMomentPhotoInfo.setErrorMsg(uploadInfo.getErrorMsg());
        if (uploadInfo.isStatus() && null != uploadInfo.getImageResponse()) {
            String imageId = uploadInfo.getImageResponse().getImageId();
            if (!StringUtils.isEmpty(imageId)) {
                uploadHSMomentPhotoInfo.setImageId(imageId);
                uploadHSMomentPhotoInfo.setImageUrl(imageLibraryServiceHelper.getImageCDNUrl(true, imageId));
            }
        }
        return uploadHSMomentPhotoInfo;
    }
    
    @RequestMapping(value = "/uploadMomentsPhotoData", method = RequestMethod.POST)
    public String uploadMomentsPhotoData(@RequestBody HSMomentPhotoRequest hsMomentPhotoRequest) {
        HSMomentsData hsMomentsData =  DTO2BOConverter.convertHSMomentPhotoRequestTOHSMomentsData(hsMomentPhotoRequest);
        hsMomentsService.saveHSMomentsData(hsMomentsData);
        
        return "success";
    }
    
    @RequestMapping(value = "/uploadMomentsPhoto", method = RequestMethod.GET)
    public UploadInfo getUploadImagePage(HttpServletResponse response) {
        
        return null;
        //return uploadHSMomentsPhotosService.uploadImageFile(request, response);
    }

}
