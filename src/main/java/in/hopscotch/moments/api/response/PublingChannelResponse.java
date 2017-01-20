package in.hopscotch.moments.api.response;

import in.hopscotch.moments.entity.PublingChannelDetail;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PublingChannelResponse extends PublingResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @XmlElement(name = "detail")
    private PublingChannelDetail detail;

    public PublingChannelDetail getDetail() {
        return detail;
    }

    public void setDetail(PublingChannelDetail detail) {
        this.detail = detail;
    }
}
