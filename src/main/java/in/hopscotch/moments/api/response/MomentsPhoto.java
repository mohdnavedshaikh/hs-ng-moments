package in.hopscotch.moments.api.response;

import java.io.Serializable;

public class MomentsPhoto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long momentsPhotoId;

    private String imageURL;

    private String Title;

    private String name;

    private int likes;

    public Long getMomentsPhotoId() {
        return momentsPhotoId;
    }

    public void setMomentsPhotoId(Long momentsPhotoId) {
        this.momentsPhotoId = momentsPhotoId;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

}
