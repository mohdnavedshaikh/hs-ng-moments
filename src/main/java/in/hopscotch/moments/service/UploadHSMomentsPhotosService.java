package in.hopscotch.moments.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface UploadHSMomentsPhotosService {

    void uploadImageFile(MultipartHttpServletRequest request, HttpServletResponse response);

}
