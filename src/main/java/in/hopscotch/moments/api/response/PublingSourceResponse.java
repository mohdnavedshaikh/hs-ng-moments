package in.hopscotch.moments.api.response;

import in.hopscotch.moments.entity.PublingSourceDetail;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PublingSourceResponse extends PublingResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @XmlElement(name = "detail")
    private PublingSourceDetail detail;

    public PublingSourceDetail getDetail() {
        return detail;
    }

    public void setDetail(PublingSourceDetail detail) {
        this.detail = detail;
    }
    
}
