package in.hopscotch.moments.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class InstagramAccessTokenResponse {
    
    @XmlElement(name = "access_token")
    String accessToken;
    
    @XmlElement(name = "user")
    InstagramUser user;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public InstagramUser getUser() {
        return user;
    }

    public void setUser(InstagramUser user) {
        this.user = user;
    }
}
