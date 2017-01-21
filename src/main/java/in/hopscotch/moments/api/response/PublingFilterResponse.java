package in.hopscotch.moments.api.response;

import in.hopscotch.moments.entity.PublingFilterDetail;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PublingFilterResponse extends PublingResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @XmlElement(name = "detail")
    PublingFilterDetail detail;

    public PublingFilterDetail getDetail() {
        return detail;
    }

    public void setDetail(PublingFilterDetail detail) {
        this.detail = detail;
    }

}
