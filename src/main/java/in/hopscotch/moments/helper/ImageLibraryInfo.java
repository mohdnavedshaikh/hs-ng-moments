package in.hopscotch.moments.helper;

import java.io.Serializable;

public class ImageLibraryInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String imageURL;

    private int width;

    private int height;

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
