package in.hopscotch.moments.api.request;

import java.io.Serializable;


public class HSMomentPhotoRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String imageId;

    private String description;

    private String taggedProductIds;

    private String taggedKidIds;

    private String taggedKidNames;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTaggedProductIds() {
        return taggedProductIds;
    }

    public void setTaggedProductIds(String taggedProductIds) {
        this.taggedProductIds = taggedProductIds;
    }

    public String getTaggedKidNames() {
        return taggedKidNames;
    }

    public void setTaggedKidNames(String taggedKidNames) {
        this.taggedKidNames = taggedKidNames;
    }

}
