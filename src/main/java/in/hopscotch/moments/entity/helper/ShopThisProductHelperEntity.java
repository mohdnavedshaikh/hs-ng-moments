package in.hopscotch.moments.entity.helper;

import java.io.Serializable;

public class ShopThisProductHelperEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer productId;

    private String productName;

    private String imageId;

    private Integer inv;

    private String status;

    private String rackStatus;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public Integer getInv() {
        return inv;
    }

    public void setInv(Integer inv) {
        this.inv = inv;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRackStatus() {
        return rackStatus;
    }

    public void setRackStatus(String rackStatus) {
        this.rackStatus = rackStatus;
    }

}
