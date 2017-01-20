package in.hopscotch.moments.api.response;

import java.io.Serializable;

public class UploadInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean status;

    private String modelName;

    private String errorMsg;

    private ImageResponse imageResponse;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public ImageResponse getImageResponse() {
        return imageResponse;
    }

    public void setImageResponse(ImageResponse imageResponse) {
        this.imageResponse = imageResponse;
    }

}