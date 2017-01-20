package in.hopscotch.moments.api.response;

import in.hopscotch.moments.entity.PublingPostDetail;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PublingPostResponse extends PublingResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "detail")
    PublingPostDetail detail;

    public PublingPostDetail getDetail() {
        return detail;
    }

    public void setDetail(PublingPostDetail detail) {
        this.detail = detail;
    }
    
}
