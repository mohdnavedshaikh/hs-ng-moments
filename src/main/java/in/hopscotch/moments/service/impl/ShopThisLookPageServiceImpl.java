package in.hopscotch.moments.service.impl;

import java.util.Arrays;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import in.hopscotch.moments.api.response.ShopThisLookPageResponse;
import in.hopscotch.moments.entity.HSMomentsData;
import in.hopscotch.moments.helper.ImageLibraryServiceHelper;
import in.hopscotch.moments.service.HSMomentsService;
import in.hopscotch.moments.service.ShopThisLookPageService;
import in.hopscotch.moments.service.ShopThisLookProductService;

@Service
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
        if(!StringUtils.isEmpty(taggedProductIds)){
            shopThisLookProductService.getShopThisProductHelperEntities(taggedProductIds);
        }
        return shopThisLookPageResponse;

    }

}
