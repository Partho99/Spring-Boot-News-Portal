package com.etcenteprise.newsoftheearth.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
public class News implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long newsId;
    private String newsHeading;
    private String newsTitle;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String newsDescription;
    private String newsTeaser;
    private String newsImageSource;
    private Date creationDTM;
    private Date updationDTM;
    private boolean isActive;

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "newscategoryid")
    private NewsCategory newsCategory;

    @ManyToOne
    @JoinColumn(name = "newssubcategoryid")
    private NewsSubCategory newsSubCategory;

    @JsonIgnore
    @OneToMany(mappedBy = "news", cascade = CascadeType.REMOVE)
    private Set<Image> image;

    @JsonIgnore
    @OneToOne(mappedBy = "news", cascade = CascadeType.REMOVE)
    private Views views;

    public News() {
    }

    public News(String newsHeading, String newsTitle, String newsDescription, String newsTeaser, String newsImageSource, Date creationDTM, Date updationDTM, boolean isActive, NewsCategory newsCategory, NewsSubCategory newsSubCategory) {
        this.newsHeading = newsHeading;
        this.newsTitle = newsTitle;
        this.newsDescription = newsDescription;
        this.newsTeaser = newsTeaser;
        this.newsImageSource = newsImageSource;
        this.creationDTM = creationDTM;
        this.updationDTM = updationDTM;
        this.isActive = isActive;
        this.newsCategory = newsCategory;
        this.newsSubCategory = newsSubCategory;
    }

    public long getNewsId() {
        return newsId;
    }

    public void setNewsId(long newsId) {
        this.newsId = newsId;
    }

    public String getNewsHeading() {
        return newsHeading;
    }

    public void setNewsHeading(String newsHeading) {
        this.newsHeading = newsHeading;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsDescription() {
        return newsDescription;
    }

    public void setNewsDescription(String newsDescription) {
        this.newsDescription = newsDescription;
    }

    public String getNewsTeaser() {
        return newsTeaser;
    }

    public void setNewsTeaser(String newsTeaser) {
        this.newsTeaser = newsTeaser;
    }

    public String getNewsImageSource() {
        return newsImageSource;
    }

    public void setNewsImageSource(String newsImageSource) {
        this.newsImageSource = newsImageSource;
    }

    public Date getCreationDTM() {
        return creationDTM;
    }

    public void setCreationDTM(Date creationDTM) {
        this.creationDTM = creationDTM;
    }

    public Date getUpdationDTM() {
        return updationDTM;
    }

    public void setUpdationDTM(Date updationDTM) {
        this.updationDTM = updationDTM;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public NewsCategory getNewsCategory() {
        return newsCategory;
    }

    public void setNewsCategory(NewsCategory newsCategory) {
        this.newsCategory = newsCategory;
    }

    public Set<Image> getImage() {
        return image;
    }

    public void setImage(Set<Image> image) {
        this.image = image;
    }

}
