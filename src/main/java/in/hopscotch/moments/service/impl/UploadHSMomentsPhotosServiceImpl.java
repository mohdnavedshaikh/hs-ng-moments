package in.hopscotch.moments.service.impl;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import in.hopscotch.moments.api.request.ImageFileRequest;
import in.hopscotch.moments.api.response.ChildInfo;
import in.hopscotch.moments.api.response.DeliveredProducts;
import in.hopscotch.moments.api.response.ImageArea;
import in.hopscotch.moments.api.response.ImageResponse;
import in.hopscotch.moments.api.response.UploadImageInfo;
import in.hopscotch.moments.api.response.UploadImagePageResponse;
import in.hopscotch.moments.api.response.UploadInfo;
import in.hopscotch.moments.helper.ImageLibraryServiceHelper;
import in.hopscotch.moments.repository.UploadHSMomentsPhotosRepository;
import in.hopscotch.moments.service.UploadHSMomentsPhotosService;
import in.hopscotch.moments.util.Convert;
import in.hopscotch.moments.util.ImageUtil;

@Service
@Transactional
public class UploadHSMomentsPhotosServiceImpl implements UploadHSMomentsPhotosService {

    private final Logger logger = LoggerFactory.getLogger(UploadHSMomentsPhotosServiceImpl.class);

    @Inject
    UploadHSMomentsPhotosRepository uploadHSMomentsPhotosRepository;

    @Inject
    ImageLibraryServiceHelper imageLibraryServiceHelper;
    
    public UploadInfo uploadImageFile(MultipartHttpServletRequest request, HttpServletResponse response) {
        UploadInfo info = new UploadInfo();
        String modelName = "hsmoments";
        String status = "Y";
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            response.setDateHeader("Expires", 0);
            MultipartFile imageFile = request.getFile("imageFile");
            this.commonHandleSingleUploadImage(imageFile, modelName, status, request, info);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            info.setStatus(false);
            info.setErrorMsg(e.getMessage());
        } 
        return info;
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
        ImageResponse imageResponse = uploadHSMomentsPhotosRepository.uploadImageFile(imageFileRequest);
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

    @Override
    public List<DeliveredProducts> getDeliveredProductInfo(Integer customerId) {
        List<DeliveredProducts> deliveredProducts = uploadHSMomentsPhotosRepository.getDeliveredProductInfo(customerId);
        for(DeliveredProducts deliveredProduct : deliveredProducts) {
            if(deliveredProduct.getImageUrl() != null) {
                String imageUrl = imageLibraryServiceHelper.getImageCDNUrl(false, deliveredProduct.getImageUrl());
                if (imageUrl != null) {
                deliveredProduct.setImageUrl(imageUrl);
                }
            }
        }
        return deliveredProducts;
    }

    @Override
    public List<ChildInfo> getChildInfo(Integer customerId) {
        return uploadHSMomentsPhotosRepository.getChildInfo(customerId);
    }

    @Override
    public UploadImagePageResponse getUploadPageInfo(Integer customerId) {
        UploadImagePageResponse uploadImagePageResponse = new UploadImagePageResponse();
        uploadImagePageResponse.setChildInfos(getChildInfo(customerId));
        uploadImagePageResponse.setDeliveredProducts(getDeliveredProductInfo(customerId));
        return uploadImagePageResponse;
    }

    @Override
    public Integer getCustomerId(String uuId) {
        return uploadHSMomentsPhotosRepository.getCustomerId(uuId);
    }

}
