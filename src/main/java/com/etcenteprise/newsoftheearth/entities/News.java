package com.etcenteprise.newsoftheearth.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
    private Date creationDTM;
    private Date updationDTM;
    private boolean isActive;
    @OneToMany(mappedBy = "news", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<NewsImage> image;
    @JsonIgnore
    @OneToOne(mappedBy = "news", cascade = CascadeType.REMOVE)
    private Views views;
    @JsonIgnore
    @OneToMany(mappedBy = "news", cascade = CascadeType.REMOVE)
    private List<NewsVoting> newsVoting;
    @JsonIgnore
    @OneToMany(mappedBy = "news", cascade = CascadeType.REMOVE)
    private List<NewsComments> newsComments;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "categories_and_sub_categories_id")
        private NewsCategoriesAndSubCategories categoriesAndSubCategories;

    public News() {
    }

    public News(Long newsId) {
        this.newsId = newsId;
    }

    public News(String newsHeading, String newsTitle, String newsDescription, String newsTeaser, Date creationDTM, Date updationDTM, boolean isActive) {
        this.newsHeading = newsHeading;
        this.newsTitle = newsTitle;
        this.newsDescription = newsDescription;
        this.newsTeaser = newsTeaser;
        this.creationDTM = creationDTM;
        this.updationDTM = updationDTM;
        this.isActive = isActive;
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

    public Set<NewsImage> getImage() {
        return image;
    }

    public void setImage(Set<NewsImage> image) {
        this.image = image;
    }

    public List<NewsVoting> getNewsVoting() {
        return newsVoting;
    }

    public void setNewsVoting(List<NewsVoting> newsVoting) {
        this.newsVoting = newsVoting;
    }

    public List<NewsComments> getNewsComments() {
        return newsComments;
    }

    public void setNewsComments(List<NewsComments> newsComments) {
        this.newsComments = newsComments;
    }

    public NewsCategoriesAndSubCategories getCategoriesAndSubCategories() {
        return categoriesAndSubCategories;
    }

    public void setCategoriesAndSubCategories(NewsCategoriesAndSubCategories categoriesAndSubCategories) {
        this.categoriesAndSubCategories = categoriesAndSubCategories;
    }

    @Override
    public String toString() {
        return "News{" +
                "newsId=" + newsId +
                ", newsHeading='" + newsHeading + '\'' +
                ", newsTitle='" + newsTitle + '\'' +
                ", newsDescription='" + newsDescription + '\'' +
                ", newsTeaser='" + newsTeaser + '\'' +
                ", creationDTM=" + creationDTM +
                ", updationDTM=" + updationDTM +
                ", isActive=" + isActive +
                ", image=" + image +
                ", views=" + views +
                ", newsVoting=" + newsVoting +
                ", newsComments=" + newsComments +
                ", categoriesAndSubCategories=" + categoriesAndSubCategories +
                '}';
    }
}
