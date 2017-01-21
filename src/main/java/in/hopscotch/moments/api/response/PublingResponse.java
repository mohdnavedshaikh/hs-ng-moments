package in.hopscotch.moments.api.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PublingResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @XmlElement(name = "success")
    private Boolean success;
    @XmlElement(name = "type")
    private String type;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
