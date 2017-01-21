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
import in.hopscotch.moments.helper.ImageLibraryInfo;
import in.hopscotch.moments.helper.ImageLibraryServiceHelper;

@Component
public class BO2DTOConverter {

    @Inject
    ImageLibraryServiceHelper imageLibraryHelper;

    public List<MomentsPhoto> convertListofHSMomentsDataToMomentsPhoto(List<HSMomentsData> hsMomentsDatas) {
        List<MomentsPhoto> momentsPhotos = new ArrayList<>();
        if (CollectionUtils.isEmpty(hsMomentsDatas))
            return momentsPhotos;

        hsMomentsDatas.forEach(mdata -> {
            MomentsPhoto mp = new MomentsPhoto();
            mp.setMomentsPhotoId(mdata.getId());
            ImageLibraryInfo imageLibraryInfo = imageLibraryHelper.getImageLibraryInfo(true, mdata.getHsImageURL());
            mp.setImageURL(imageLibraryInfo.getImageURL());
            mp.setLikes(mdata.getLikes());
            mp.setTitle(mdata.getTitle());
            mp.setName(!StringUtils.isEmpty(mdata.getTaggedKidNames()) ? mdata.getTaggedKidNames() : mdata.getCustomerName());
            mp.setWidth(imageLibraryInfo.getWidth());
            mp.setHeight(imageLibraryInfo.getHeight());
            momentsPhotos.add(mp);
        });
        return momentsPhotos;
    }

    public ContestWinnerInfo convertContestWinnerToContestWinnerInfo(ContestWinner contestWinner) {
        if (null == contestWinner)
            return null;

        ContestWinnerInfo contestWinnerInfo = new ContestWinnerInfo();
        String imageUrl = !StringUtils.isEmpty(contestWinner.getHsmomentsdata().getHsImageURL()) ? imageLibraryHelper.getImageCDNUrl(true, contestWinner.getHsmomentsdata().getHsImageURL())
                : contestWinner.getHsmomentsdata().getInstagramImageURL();
        contestWinnerInfo.setImageURL(imageUrl);
        contestWinnerInfo.setContestDescription(contestWinner.getHsMomentsContest().getDescription());
        contestWinnerInfo.setContestTitle(contestWinner.getHsMomentsContest().getContestName());
        contestWinnerInfo.setCustomerName(contestWinner.getHsmomentsdata().getCustomerName());
        contestWinnerInfo.setKidName(contestWinner.getHsmomentsdata().getTaggedKidNames());
        return contestWinnerInfo;
    }

}
