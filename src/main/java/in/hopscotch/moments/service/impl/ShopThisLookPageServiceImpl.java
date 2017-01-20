package in.hopscotch.moments.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import in.hopscotch.moments.api.response.ShopThisLookPageResponse;
import in.hopscotch.moments.api.response.ShopThisLookProductInfo;
import in.hopscotch.moments.entity.HSMomentsData;
import in.hopscotch.moments.entity.helper.ShopThisProductHelperEntity;
import in.hopscotch.moments.helper.ImageLibraryServiceHelper;
import in.hopscotch.moments.service.HSMomentsService;
import in.hopscotch.moments.service.ShopThisLookPageService;
import in.hopscotch.moments.service.ShopThisLookProductService;

@Service
@Transactional
public class ShopThisLookPageServiceImpl implements ShopThisLookPageService {

    @Inject
    HSMomentsService hsMomentsService;
    @Inject
    ImageLibraryServiceHelper imageLibraryHelper;
    @Inject
    ShopThisLookProductService shopThisLookProductService;

    public ShopThisLookPageResponse getShopThisLookPage(Long momentsPhotoId) {
        ShopThisLookPageResponse shopThisLookPageResponse = new ShopThisLookPageResponse();
        HSMomentsData hsMomentsData = hsMomentsService.getHSMomentsById(momentsPhotoId);
        shopThisLookPageResponse.setCustomerName(hsMomentsData.getCustomerName());
        shopThisLookPageResponse.setDescription(hsMomentsData.getDescription());
        shopThisLookPageResponse
                .setImageUrl(!StringUtils.isEmpty(hsMomentsData.getHsImageURL()) ? imageLibraryHelper.getImageCDNUrl(true, hsMomentsData.getHsImageURL()) : hsMomentsData.getInstagramImageURL());
        shopThisLookPageResponse.setKidNames(hsMomentsData.getTaggedKidNames());
        shopThisLookPageResponse.setLikes(hsMomentsData.getLikes());

        String taggedProductIds = hsMomentsData.getTaggedProductIds();
        if (!StringUtils.isEmpty(taggedProductIds)) {
            List<ShopThisProductHelperEntity> shopThisProductHelperEntities = shopThisLookProductService.getShopThisProductHelperEntities(taggedProductIds);
            List<ShopThisLookProductInfo> shopThisLookProductInfos = new ArrayList<>();
            if (!CollectionUtils.isEmpty(shopThisProductHelperEntities)) {
                shopThisProductHelperEntities.forEach(stphe -> {
                    ShopThisLookProductInfo shopThisLookProductInfo = new ShopThisLookProductInfo();
                    shopThisLookProductInfo.setProductImageUrl(imageLibraryHelper.getImageCDNUrl(false, stphe.getImageId()));
                    ;
                    if (stphe.getInv() > 0 && stphe.getStatus().equalsIgnoreCase("Y") && stphe.getRackStatus().equalsIgnoreCase("Y")) {
                        shopThisLookProductInfo.setCaption("Buy now");
                    } else {
                        shopThisLookProductInfo.setCaption("See similar");
                    }

                    shopThisLookProductInfo.setProductId(stphe.getProductId());
                    shopThisLookProductInfos.add(shopThisLookProductInfo);
                });

            }
            shopThisLookPageResponse.setShopThisLookProductInfos(shopThisLookProductInfos);
        }
        return shopThisLookPageResponse;

    }

}
