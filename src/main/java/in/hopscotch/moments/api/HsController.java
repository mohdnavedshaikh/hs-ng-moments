package in.hopscotch.moments.api;

import java.io.PrintWriter;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import in.hopscotch.moments.api.request.ImageFileRequest;
import in.hopscotch.moments.api.response.ImageArea;
import in.hopscotch.moments.api.response.ImageResponse;
import in.hopscotch.moments.api.response.UploadImageInfo;
import in.hopscotch.moments.api.response.UploadInfo;
import in.hopscotch.moments.service.HSMomentsService;
import in.hopscotch.moments.util.Convert;
import in.hopscotch.moments.util.ImageUtil;

@Controller
public class HsController {

    private final Logger logger = LoggerFactory.getLogger(HsController.class);

    @Inject
    HSMomentsService hSMomentsService;

    @RequestMapping(value = "/uploadngmoments", method = RequestMethod.POST)
    public void uploadImage(MultipartHttpServletRequest request, HttpServletResponse response) {
        UploadInfo info = new UploadInfo();
        String modelName = "ngmoments";
        String status = "Y";
        PrintWriter out = null;
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            response.setDateHeader("Expires", 0);
            out = response.getWriter();
            MultipartFile imageFile = request.getFile("imageFile");
            this.commonHandleSingleUploadImage(imageFile, modelName, status, request, info);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            info.setStatus(false);
            info.setErrorMsg(e.getMessage());
        } finally {
            String result = "result";//JSONBinder.toJSON(info); // todo
            out.write(result);
            out.flush();
            out.close();
        }
    }

    private void commonHandleSingleUploadImage(MultipartFile imageFile, String modelName, String status, MultipartHttpServletRequest request, UploadInfo info) throws Exception {
        Integer expectedHeight = request.getParameter("expectedHeight") != null ? Integer.parseInt(request.getParameter("expectedHeight")) : null;
        Integer expectedWidth = request.getParameter("expectedWidth") != null ? Integer.parseInt(request.getParameter("expectedWidth")) : null;
        Integer minWidth = Convert.toInt(request.getParameter("minWidth"), Integer.MIN_VALUE);
        Integer maxHeight = Convert.toInt(request.getParameter("maxHeight"), Integer.MAX_VALUE);
        Boolean strict = request.getParameter("strict") != null ? Boolean.TRUE : Boolean.FALSE;
        String imageType = request.getParameter("imageType");
        if (null == imageFile) {
            info.setStatus(false);
            info.setErrorMsg("The file is empty, please select picture again (support jpg. and png.).");
            return;
        }
        boolean isSuccess = validateImageFile(imageFile.getContentType());
        if (!isSuccess) {
            info.setStatus(isSuccess);
            info.setErrorMsg("Illegal file type. Pls try again, supports jpg, png.");
            return;
        }
        ImageArea imageArea = ImageUtil.getImageArea(imageFile.getInputStream());
        if (!(ImageUtil.isValidFile(expectedHeight, expectedWidth, imageArea))) {
            info.setStatus(false);
            info.setErrorMsg("Please upload file with correct dimensions.");
        } else if (strict && !(ImageUtil.isValidFileStrict(expectedHeight, expectedWidth, imageArea))) {
            info.setStatus(false);
            info.setErrorMsg("Please upload file with correct dimensions.");
        } else if (!ImageUtil.validImageSizeRange(minWidth, maxHeight, imageArea)) {
            info.setStatus(false);
            info.setErrorMsg(ImageUtil.getImageSizeRangeErrorMessage(minWidth, maxHeight));
        } else {
            UploadImageInfo updateImageInfo = new UploadImageInfo();
            updateImageInfo.setImageFile(imageFile);
            updateImageInfo.setImageType(imageType);
            uploadImage(info, updateImageInfo, modelName, status);
        }
    }

    private void uploadImage(UploadInfo info, UploadImageInfo updateImageInfo, String modelName, String status) throws Exception {
        ImageFileRequest imageFileRequest = new ImageFileRequest();
        String contentType = updateImageInfo.getImageFile().getContentType();
        String imageId = UUID.randomUUID().toString();
        imageFileRequest.setImageId(imageId);
        imageFileRequest.setFileName(getFileName(imageId, contentType));
        imageFileRequest.setFileSize(updateImageInfo.getImageFile().getSize());
        imageFileRequest.setInputStream(updateImageInfo.getImageFile().getInputStream());
        imageFileRequest.setContentType(contentType);
        imageFileRequest.setModelName(modelName);
        imageFileRequest.setCutOutImage("Y".equalsIgnoreCase(status));
        imageFileRequest.setImageType(updateImageInfo.getImageType());
        ImageResponse imageResponse = hSMomentsService.uploadImageFile(imageFileRequest);
        info.setStatus(true);
        info.setImageResponse(imageResponse);
    }

    
    public boolean validateImageFile(String contentType) {
        for (String ct : new String[] { "image/jpeg", "image/pjpeg", "image/x-png", "image/png" }) {
            if (contentType.equalsIgnoreCase(ct.trim())) {
                return true;
            }
        }
        return false;
    }
    
    public String getFileName(String uuid, String contentType) {
        return uuid + "_full." + (contentType.contains("png") ? "png" : contentType.contains("gif") ? "gif" : "jpg");
    }
}