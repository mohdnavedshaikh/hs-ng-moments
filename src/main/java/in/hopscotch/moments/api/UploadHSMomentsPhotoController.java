package in.hopscotch.moments.api;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import in.hopscotch.moments.api.response.UploadInfo;
import in.hopscotch.moments.service.UploadHSMomentsPhotosService;

@RestController
@RequestMapping("/hsmoments")
public class UploadHSMomentsPhotoController {

    @Inject
    UploadHSMomentsPhotosService uploadHSMomentsPhotosService;

    @RequestMapping(value = "/uploadMomentsPhoto", method = RequestMethod.POST)
    public UploadInfo uploadImage(MultipartHttpServletRequest request, HttpServletResponse response) {
        return uploadHSMomentsPhotosService.uploadImageFile(request, response);
    }

}
