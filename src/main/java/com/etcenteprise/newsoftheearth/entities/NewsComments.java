package com.etcenteprise.newsoftheearth.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class NewsComments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    private String comment;
    private Date updatedTime;
    private Date createdTime;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "news_id", nullable = false)
    private News news;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private boolean isActive;
    @JsonIgnore
    @OneToMany(mappedBy = "newsComments", cascade = CascadeType.REMOVE)
    private List<NewsCommentReplies> newsCommentReplies;

    public NewsComments() {
    }


    public NewsComments(Long commentId, String comment, Date updatedTime, Date createdTime, News news, User user, boolean isActive) {
        this.commentId = commentId;
        this.comment = comment;
        this.updatedTime = updatedTime;
        this.createdTime = createdTime;
        this.news = news;
        this.user = user;
        this.isActive = isActive;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
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

    public List<NewsCommentReplies> getNewsCommentReplies() {
        return newsCommentReplies;
    }

    public void setNewsCommentReplies(List<NewsCommentReplies> newsCommentReplies) {
        this.newsCommentReplies = newsCommentReplies;
    }
}
