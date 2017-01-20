package in.hopscotch.moments.api.response;

import java.util.List;

public class UploadImagePageResponse {

    private List<DeliveredProducts> deliveredProducts;
    
    private List<ChildInfo> childInfos;

    public List<DeliveredProducts> getDeliveredProducts() {
        return deliveredProducts;
    }

    public void setDeliveredProducts(List<DeliveredProducts> deliveredProducts) {
        this.deliveredProducts = deliveredProducts;
    }

    public List<ChildInfo> getChildInfos() {
        return childInfos;
    }

    public void setChildInfos(List<ChildInfo> childInfos) {
        this.childInfos = childInfos;
    }
    
}
