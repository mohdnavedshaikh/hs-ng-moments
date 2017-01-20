package in.hopscotch.moments.api.response;

import java.io.Serializable;

public class ShopThisLookProductInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer productId;

    private String caption;

    private String productImageUrl;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

}
