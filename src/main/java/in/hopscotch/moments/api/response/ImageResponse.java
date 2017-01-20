package in.hopscotch.moments.api.response;

import java.io.Serializable;
import java.util.List;

public class ImageResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String modelName;

    private String imageId;

    private String imageUrl;

    private Long version;

    private List<ImageArea> imageAreas;

    public static ImageResponse createImageResponse(String modelName, String imageId, String imageUrl, Long version, List<ImageArea> imageAreas) {
        ImageResponse response = new ImageResponse();
        response.setModelName(modelName);
        response.setImageId(imageId);
        response.setImageUrl(imageUrl);
        response.setVersion(version);
        response.setImageAreas(imageAreas);
        return response;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public List<ImageArea> getImageAreas() {
        return imageAreas;
    }

    public void setImageAreas(List<ImageArea> imageAreas) {
        this.imageAreas = imageAreas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

}