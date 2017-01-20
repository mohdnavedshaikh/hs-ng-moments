package in.hopscotch.moments.api.response;

import java.io.Serializable;

public class ImageArea implements Serializable {

    private String imageType;

    private Integer width;

    private Integer height;

    public ImageArea(String imageType, Integer width, Integer height) {
        super();
        this.imageType = imageType;
        this.width = width;
        this.height = height;
    }

    public ImageArea(Integer width, Integer height) {
        super();
        this.width = width;
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

}