package in.hopscotch.moments.api.request;

import java.io.InputStream;

public class ImageFileRequest {

    private String imageId;

    private String fileName;

    private InputStream inputStream;

    private String contentType;

    private Long fileSize;

    private String modelName;

    private Long limitSize = 50 * 1024L;

    private boolean cutOutImage;

    private String imageType;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Long getLimitSize() {
        return limitSize;
    }

    public void setLimitSize(Long limitSize) {
        this.limitSize = limitSize;
    }

    public boolean isCutOutImage() {
        return cutOutImage;
    }

    public void setCutOutImage(boolean cutOutImage) {
        this.cutOutImage = cutOutImage;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

}