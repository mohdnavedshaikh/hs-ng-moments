package in.hopscotch.moments.api.response;

import java.io.Serializable;

public class ContestWinnerInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String contestTitle;

    private String contestDescription;

    private String customerName;

    private String KidName;

    private String imageURL;

    private String hsMomentDataDescription;

    public String getContestTitle() {
        return contestTitle;
    }

    public void setContestTitle(String contestTitle) {
        this.contestTitle = contestTitle;
    }

    public String getContestDescription() {
        return contestDescription;
    }

    public void setContestDescription(String contestDescription) {
        this.contestDescription = contestDescription;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getKidName() {
        return KidName;
    }

    public void setKidName(String kidName) {
        KidName = kidName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getHsMomentDataDescription() {
        return hsMomentDataDescription;
    }

    public void setHsMomentDataDescription(String hsMomentDataDescription) {
        this.hsMomentDataDescription = hsMomentDataDescription;
    }

}
