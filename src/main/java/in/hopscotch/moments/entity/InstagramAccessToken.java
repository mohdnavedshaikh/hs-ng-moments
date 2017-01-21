package in.hopscotch.moments.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "InstagramAccessToken")
@Table(name = "hsmoments.instagram_access_token")
public class InstagramAccessToken {
    
    @Id
    @Column(name = "customer_id")
    private Integer customerId;
    
    @Column(name = "access_token", length = 100)
    private String accessToken;
    
    @Column(name = "created_date", length = 300)
    private LocalDateTime createdDate;
    
    @Column(name = "updated_date", length = 300)
    private LocalDateTime updatedDate = LocalDateTime.now();

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
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
