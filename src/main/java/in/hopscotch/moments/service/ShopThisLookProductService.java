package in.hopscotch.moments.service;

import java.util.List;

import in.hopscotch.moments.entity.helper.ShopThisProductHelperEntity;

public interface ShopThisLookProductService {
    
    List<ShopThisProductHelperEntity> getShopThisProductHelperEntities(String productIds);

}
