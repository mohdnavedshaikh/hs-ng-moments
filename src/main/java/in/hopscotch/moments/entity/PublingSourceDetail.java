package in.hopscotch.moments.entity;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PublingSourceDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @XmlElement(name = "sources")
    List<PublingSource> sources;

    public List<PublingSource> getSources() {
        return sources;
    }

    public void setSources(List<PublingSource> sources) {
        this.sources = sources;
    }
    
}
