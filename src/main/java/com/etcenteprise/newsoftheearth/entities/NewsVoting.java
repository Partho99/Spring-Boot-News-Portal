package com.etcenteprise.newsoftheearth.entities;

import javax.persistence.*;

@Entity
public class NewsVoting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newsVotingId;
    private Boolean upVote;
    private Boolean downVote;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "news_id", nullable = false)
    private News news;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public NewsVoting() {
    }

    public NewsVoting(Boolean upVote, Boolean downVote, News news, User user) {
        this.upVote = upVote;
        this.downVote = downVote;
        this.news = news;
        this.user = user;
    }

    public Long getNewsVotingId() {
        return newsVotingId;
    }

    public void setNewsVotingId(Long newsVotingId) {
        this.newsVotingId = newsVotingId;
    }

    public Boolean getUpVote() {
        return upVote;
    }

    public void setUpVote(Boolean upVote) {
        this.upVote = upVote;
    }

    public Boolean getDownVote() {
        return downVote;
    }

    public void setDownVote(Boolean downVote) {
        this.downVote = downVote;
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
}
