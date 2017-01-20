package in.hopscotch.moments.api.response;

import in.hopscotch.moments.entity.PublingErrorDetail;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

public class PublingErrorResponse extends PublingResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "detail")
    private PublingErrorDetail detail;

    public PublingErrorDetail getDetail() {
        return detail;
    }

    public void setDetail(PublingErrorDetail detail) {
        this.detail = detail;
    }    
}
