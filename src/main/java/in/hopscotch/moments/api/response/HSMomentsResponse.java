package in.hopscotch.moments.api.response;

import java.io.Serializable;
import java.util.List;

public class HSMomentsResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private ContestWinnerInfo contestWinnerInfo;

    private List<MomentsPhoto> momentsPhotos;

    public ContestWinnerInfo getContestWinnerInfo() {
        return contestWinnerInfo;
    }

    public void setContestWinnerInfo(ContestWinnerInfo contestWinnerInfo) {
        this.contestWinnerInfo = contestWinnerInfo;
    }

    public List<MomentsPhoto> getMomentsPhotos() {
        return momentsPhotos;
    }

    public void setMomentsPhotos(List<MomentsPhoto> momentsPhotos) {
        this.momentsPhotos = momentsPhotos;
    }

}
