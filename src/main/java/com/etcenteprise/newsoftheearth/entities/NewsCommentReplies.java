package com.etcenteprise.newsoftheearth.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class NewsCommentReplies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;
    private String reply;
    private Date createdTime;
    private Date updatedTime;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private boolean isActive;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "newsComments_id", nullable = false)
    private NewsComments newsComments;

    public NewsCommentReplies() {
    }

    public NewsCommentReplies(Long replyId, String reply, Date createdTime, Date updatedTime, User user, boolean isActive, NewsComments newsComments) {
        this.replyId = replyId;
        this.reply = reply;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.user = user;
        this.isActive = isActive;
        this.newsComments = newsComments;
    }

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public NewsComments getNewsComments() {
        return newsComments;
    }

    public void setNewsComments(NewsComments newsComments) {
        this.newsComments = newsComments;
    }
}
