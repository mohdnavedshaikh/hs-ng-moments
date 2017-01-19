package in.hopscotch.moments.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "hsmoments.image_library")
public class ImageLibrary {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "image_id")
    private String imageId;

    @Column(name = "model_name", length = 50, nullable = false)
    private String modelName;

    @Column(name = "image_name", length = 255, nullable = false)
    private String imageName;

    @Column(name = "image_path", length = 255, nullable = false)
    private String imagePath;

    @Column(name = "full_Size", length = 255, nullable = true)
    private String fullSize;

    @Column(name = "large_size", length = 255, nullable = true)
    private String largeSize;

    @Column(name = "medium_size", length = 255, nullable = true)
    private String mediumSize;

    @Column(name = "thumbnail_size", length = 255, nullable = true)
    private String thumbnailSize;

    @Column(name = "upload_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadDate;

    @Column(name = "use_times", nullable = false)
    private Integer useTimes;

    @Column(name = "description", length = 255, nullable = true)
    private String description;

    @Column(name = "version", nullable = true)
    private Long version;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getFullSize() {
        return fullSize;
    }

    public void setFullSize(String fullSize) {
        this.fullSize = fullSize;
    }

    public String getLargeSize() {
        return largeSize;
    }

    public void setLargeSize(String largeSize) {
        this.largeSize = largeSize;
    }

    public String getMediumSize() {
        return mediumSize;
    }

    public void setMediumSize(String mediumSize) {
        this.mediumSize = mediumSize;
    }

    public String getThumbnailSize() {
        return thumbnailSize;
    }

    public void setThumbnailSize(String thumbnailSize) {
        this.thumbnailSize = thumbnailSize;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Integer getUseTimes() {
        return useTimes;
    }

    public void setUseTimes(Integer useTimes) {
        this.useTimes = useTimes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;

        ImageLibrary imageLibrary = (ImageLibrary) object;

        return !(imageId != null ? !imageId.equals(imageLibrary.imageId) : imageLibrary.imageId != null);
    }

    @Override
    public int hashCode() {
        return imageId != null ? imageId.hashCode() : 0;
    }

}
