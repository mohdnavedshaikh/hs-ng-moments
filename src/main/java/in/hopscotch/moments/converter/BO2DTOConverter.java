package in.hopscotch.moments.converter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import in.hopscotch.moments.api.response.ContestWinnerInfo;
import in.hopscotch.moments.api.response.MomentsPhoto;
import in.hopscotch.moments.entity.ContestWinner;
import in.hopscotch.moments.entity.HSMomentsData;
import in.hopscotch.moments.util.HSMomentsUtil;

@Component
public class BO2DTOConverter {

    @Inject
    HSMomentsUtil hsMomentsUtil;

    public List<MomentsPhoto> convertListofHSMomentsDataToMomentsPhoto(List<HSMomentsData> hsMomentsDatas) {
        List<MomentsPhoto> momentsPhotos = new ArrayList<>();
        if (CollectionUtils.isEmpty(hsMomentsDatas))
            return momentsPhotos;

        hsMomentsDatas.forEach(mdata -> {
            MomentsPhoto mp = new MomentsPhoto();
            mp.setMomentsPhotoId(mdata.getId());
            mp.setImageURL(!StringUtils.isEmpty(mdata.getHsImageURL()) ? hsMomentsUtil.getImageCDNUrl(mdata.getHsImageURL()) : mdata.getInstagramImageURL());
            mp.setLikes(mdata.getLikes());
            mp.setTitle(mdata.getTitle());
            mp.setName(!StringUtils.isEmpty(mdata.getTaggedKidNames()) ? mdata.getTaggedKidNames() : mdata.getCustomerName());
            momentsPhotos.add(mp);
        });
        return momentsPhotos;
    }

    public ContestWinnerInfo convertContestWinnerToContestWinnerInfo(ContestWinner contestWinner) {
        if (null == contestWinner)
            return null;

        ContestWinnerInfo contestWinnerInfo = new ContestWinnerInfo();
        String imageUrl = !StringUtils.isEmpty(contestWinner.getHsmomentsdata().getHsImageURL()) ? hsMomentsUtil.getImageCDNUrl(contestWinner.getHsmomentsdata().getHsImageURL())
                : contestWinner.getHsmomentsdata().getInstagramImageURL();
        contestWinnerInfo.setImageURL(imageUrl);
        contestWinnerInfo.setContestDescription(contestWinner.getHsMomentsContest().getDescription());
        contestWinnerInfo.setContestTitle(contestWinner.getHsMomentsContest().getContestName());
        contestWinnerInfo.setCustomerName(contestWinner.getHsmomentsdata().getCustomerName());
        contestWinnerInfo.setKidName(contestWinner.getHsmomentsdata().getTaggedKidNames());
        return contestWinnerInfo;
    }

}
