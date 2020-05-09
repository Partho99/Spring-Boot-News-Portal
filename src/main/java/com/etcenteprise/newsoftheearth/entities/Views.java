package com.etcenteprise.newsoftheearth.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Views implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long viewsId;
    private long viewsCount;
    @OneToOne
    @JoinColumn(name = "news_id")
    private News news;

    public Views() {
    }


    public long getViewsId() {
        return viewsId;
    }

    public void setViewsId(long viewsId) {
        this.viewsId = viewsId;
    }

    public long getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(long viewsCount) {
        this.viewsCount = viewsCount;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }
}
