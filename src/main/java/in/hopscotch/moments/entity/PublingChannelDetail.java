package in.hopscotch.moments.entity;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PublingChannelDetail implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @XmlElement(name = "channels")
    List<String> channels;

    public List<String> getChannels() {
        return channels;
    }

    public void setChannels(List<String> channels) {
        this.channels = channels;
    }
}
