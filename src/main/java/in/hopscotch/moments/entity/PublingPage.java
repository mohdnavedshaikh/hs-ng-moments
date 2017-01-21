package in.hopscotch.moments.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PublingPage implements Serializable {

    private static final long serialVersionUID = 1L;
    @XmlElement(name = "last_post_timestamp")
    Long lastPostTimestamp;
    @XmlElement(name = "first_post_timestamp")
    Long firstPostTimestamp;
    
    public Long getLastPostTimestamp() {
        return lastPostTimestamp;
    }
    public void setLastPostTimestamp(Long lastPostTimestamp) {
        this.lastPostTimestamp = lastPostTimestamp;
    }
    public Long getFirstPostTimestamp() {
        return firstPostTimestamp;
    }
    public void setFirstPostTimestamp(Long firstPostTimestamp) {
        this.firstPostTimestamp = firstPostTimestamp;
    }
}
