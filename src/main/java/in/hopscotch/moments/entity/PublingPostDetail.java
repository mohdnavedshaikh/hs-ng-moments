package in.hopscotch.moments.entity;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PublingPostDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "posts")
    List<PublingPost> posts;
    @XmlElement(name = "page")
    PublingPage page;

    public List<PublingPost> getPosts() {
        return posts;
    }

    public void setPosts(List<PublingPost> posts) {
        this.posts = posts;
    }

    public PublingPage getPage() {
        return page;
    }

    public void setPage(PublingPage page) {
        this.page = page;
    }
}
