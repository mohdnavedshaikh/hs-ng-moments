package in.hopscotch.moments.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import in.hopscotch.moments.entity.helper.ShopThisProductHelperEntity;
import in.hopscotch.moments.repository.ShopThisLookProductRepository;
import in.hopscotch.moments.service.ShopThisLookProductService;

@Service
public class ShopThisLookProductServiceImpl implements ShopThisLookProductService {

    @Inject
    ShopThisLookProductRepository shopThisLookProductRepository;

    public List<ShopThisProductHelperEntity> getShopThisProductHelperEntities(String productIds) {
        return shopThisLookProductRepository.getShopThisProductHelperEntities(productIds);
    }

}
