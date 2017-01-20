package in.hopscotch.moments.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PublingPost implements Serializable {

    private static final long serialVersionUID = 1L;
    @XmlElement(name = "id")
    String id;
    @XmlElement(name = "channel")
    String channel;
    @XmlElement(name = "sender_id")
    Integer SenderId;
    @XmlElement(name = "sender_name")
    String SenderName;
    @XmlElement(name = "sender_username")
    String senderUsername;
    @XmlElement(name = "sender_image_url")
    String senderImageUrl;
    @XmlElement(name = "published_at")
    String publishedAt;
    @XmlElement(name = "link")
    String link;
    @XmlElement(name = "message")
    String message;
    @XmlElement(name = "tags")
    String tags;
    @XmlElement(name = "type")
    String type;
    @XmlElement(name = "media_url")
    String mediaUrl;
    @XmlElement(name = "media_width")
    Integer mediaWidth;
    @XmlElement(name = "media_height")
    Integer mediaHeight;
    @XmlElement(name = "pinned")
    Boolean pinned;
    @XmlElement(name = "sender_url")
    String senderUrl;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getChannel() {
        return channel;
    }
    public void setChannel(String channel) {
        this.channel = channel;
    }
    public Integer getSenderId() {
        return SenderId;
    }
    public void setSenderId(Integer senderId) {
        SenderId = senderId;
    }
    public String getSenderName() {
        return SenderName;
    }
    public void setSenderName(String senderName) {
        SenderName = senderName;
    }
    public String getSenderUsername() {
        return senderUsername;
    }
    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }
    public String getSenderImageUrl() {
        return senderImageUrl;
    }
    public void setSenderImageUrl(String senderImageUrl) {
        this.senderImageUrl = senderImageUrl;
    }
    public String getPublishedAt() {
        return publishedAt;
    }
    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getTags() {
        return tags;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getMediaUrl() {
        return mediaUrl;
    }
    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }
    public Integer getMediaWidth() {
        return mediaWidth;
    }
    public void setMediaWidth(Integer mediaWidth) {
        this.mediaWidth = mediaWidth;
    }
    public Integer getMediaHeight() {
        return mediaHeight;
    }
    public void setMediaHeight(Integer mediaHeight) {
        this.mediaHeight = mediaHeight;
    }
    public Boolean getPinned() {
        return pinned;
    }
    public void setPinned(Boolean pinned) {
        this.pinned = pinned;
    }
    public String getSenderUrl() {
        return senderUrl;
    }
    public void setSenderUrl(String senderUrl) {
        this.senderUrl = senderUrl;
    }
}
