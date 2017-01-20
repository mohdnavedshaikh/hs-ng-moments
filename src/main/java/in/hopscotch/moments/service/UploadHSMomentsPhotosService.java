package in.hopscotch.moments.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import in.hopscotch.moments.api.response.UploadInfo;

public interface UploadHSMomentsPhotosService {

    UploadInfo uploadImageFile(MultipartHttpServletRequest request, HttpServletResponse response);

}
