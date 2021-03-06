package in.hopscotch.moments.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import in.hopscotch.moments.constant.NamedQueryConstant;

@Entity(name = "HSMomentsData")
@Table(name = "hsmoments.hs_moments_data")
@NamedQueries({ @NamedQuery(name = NamedQueryConstant.HSMOMENTSDATA_NEWEST, query = "select hsmomentdata From HSMomentsData hsmomentdata order by hsmomentdata.updatedDate DESC"),
        @NamedQuery(name = NamedQueryConstant.HSMOMENTSDATA_POPULAR, query = "select hsmomentdata From HSMomentsData hsmomentdata order by hsmomentdata.likes DESC") })
@NamedNativeQueries({ @NamedNativeQuery(name = NamedQueryConstant.HSMOMENTSDATA_INCREMENT_LIKES, query = "Update hsmoments.hs_moments_data SET likes = likes + 1") })
public class HSMomentsData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "hs_image_url", length = 300)
    private String hsImageURL;

    @Column(name = "instagram_image_url", length = 300)
    private String instagramImageURL;

    @Column(name = "likes")
    private int likes;

    @Column(name = "title", length = 20)
    private String title;

    @Column(name = "description", length = 500)
    private String description;

    // String caz user may tag more than 1 product
    @Column(name = "tagged_product_ids", length = 250)
    private String taggedProductIds;

    @Column(name = "tagged_kid_ids", length = 250)
    private String taggedKidIds;

    // contains comma separated names of tagged names
    @Column(name = "tagged_kid_names", length = 300)
    private String taggedKidNames;

    @Column(name = "customer_id")
    private Integer customerId;

    // contains first name + last name of user
    @Column(name = "customer_name", length = 100)
    private String customerName;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "updated_date")
    private LocalDateTime updatedDate = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHsImageURL() {
        return hsImageURL;
    }

    public void setHsImageURL(String hsImageURL) {
        this.hsImageURL = hsImageURL;
    }

    public String getInstagramImageURL() {
        return instagramImageURL;
    }

    public void setInstagramImageURL(String instagramImageURL) {
        this.instagramImageURL = instagramImageURL;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getTaggedKidIds() {
        return taggedKidIds;
    }

    public void setTaggedKidIds(String taggedKidIds) {
        this.taggedKidIds = taggedKidIds;
    }

    public String getTaggedKidNames() {
        return taggedKidNames;
    }

    public void setTaggedKidNames(String taggedKidNames) {
        this.taggedKidNames = taggedKidNames;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

}
