package in.hopscotch.moments.api.response;

import java.io.Serializable;
import java.util.List;

public class ShopThisLookPageResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String customerName;

    private String description;

    private String imageUrl;

    private int likes;

    private String kidNames;

    private List<ShopThisLookProductInfo> shopThisLookProductInfos;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getKidNames() {
        return kidNames;
    }

    public void setKidNames(String kidNames) {
        this.kidNames = kidNames;
    }

    public List<ShopThisLookProductInfo> getShopThisLookProductInfos() {
        return shopThisLookProductInfos;
    }

    public void setShopThisLookProductInfos(List<ShopThisLookProductInfo> shopThisLookProductInfos) {
        this.shopThisLookProductInfos = shopThisLookProductInfos;
    }

}
