package in.hopscotch.moments.converter;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import in.hopscotch.moments.api.cookie.CookieContext;
import in.hopscotch.moments.api.request.HSMomentPhotoRequest;
import in.hopscotch.moments.entity.HSMomentsData;

@Component
public class DTO2BOConverter {

    @Inject
    CookieContext cookieContext;

    public HSMomentsData convertHSMomentPhotoRequestTOHSMomentsData(HSMomentPhotoRequest hsMomentPhotoRequest) {
        HSMomentsData hsMomentsData = new HSMomentsData();
        hsMomentsData.setHsImageURL(hsMomentPhotoRequest.getImageId());
        hsMomentsData.setTaggedKidNames(hsMomentPhotoRequest.getTaggedKidNames());
        hsMomentsData.setDescription(hsMomentPhotoRequest.getDescription());
        hsMomentsData.setTaggedProductIds(hsMomentPhotoRequest.getTaggedProductIds());
        hsMomentsData.setLikes(0);
        return hsMomentsData;

    }

}
