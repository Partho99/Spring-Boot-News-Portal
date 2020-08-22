package com.etcenteprise.newsoftheearth.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class NewsImage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long imageId;
    private String imageSource;
    @JsonIgnore
    private Date imageCreationDTM;
    @JsonIgnore
    private Date imageUpdationDTM;
    @JsonIgnore
    private boolean isActive;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "newsid")
    private News news;

    public NewsImage() {
    }

    public NewsImage(String imageSource, boolean isActive, Date imageCreationDTM, Date imageUpdationDTM, News news) {
        this.imageSource = imageSource;
        this.imageCreationDTM = imageCreationDTM;
        this.imageUpdationDTM = imageUpdationDTM;
        this.isActive = isActive;
        this.news = news;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public Date getImageCreationDTM() {
        return imageCreationDTM;
    }

    public void setImageCreationDTM(Date imageCreationDTM) {
        this.imageCreationDTM = imageCreationDTM;
    }

    public Date getImageUpdationDTM() {
        return imageUpdationDTM;
    }

    public void setImageUpdationDTM(Date imageUpdationDTM) {
        this.imageUpdationDTM = imageUpdationDTM;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }
}
