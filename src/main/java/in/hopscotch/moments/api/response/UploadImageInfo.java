package in.hopscotch.moments.api.response;

import org.springframework.web.multipart.MultipartFile;

public class UploadImageInfo {
    private MultipartFile imageFile;
    private String imageType;

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }
}
