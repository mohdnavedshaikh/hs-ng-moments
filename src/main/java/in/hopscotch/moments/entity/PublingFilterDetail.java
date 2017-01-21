package in.hopscotch.moments.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PublingFilterDetail {

    @XmlElement(name = "filters")
    List<PublingFilter> filters;

    public List<PublingFilter> getFilters() {
        return filters;
    }

    public void setFilters(List<PublingFilter> filters) {
        this.filters = filters;
    }
    
}
