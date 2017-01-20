package in.hopscotch.moments.repository;

import java.util.List;

import in.hopscotch.moments.entity.helper.ShopThisProductHelperEntity;

public interface ShopThisLookProductRepository {

    List<ShopThisProductHelperEntity> getShopThisProductHelperEntities(String productIds);
}
