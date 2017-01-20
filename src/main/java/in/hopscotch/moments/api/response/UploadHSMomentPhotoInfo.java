package in.hopscotch.moments.api.response;

import java.io.Serializable;

public class UploadHSMomentPhotoInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    private boolean status;
    private String errorMsg;
    private String imageUrl;
    private String imageId;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

}
