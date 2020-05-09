package com.etcenteprise.newsoftheearth.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
public class NewsSubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subCategory;
    private String subCategoryName;
    private Date creationDTM;
    private Date updationDTM;
    private boolean isActive;


    @ManyToOne
    @JoinColumn(name = "newscategoryid")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private  NewsCategory newsCategory;

    public NewsSubCategory(){

    }


    public NewsSubCategory(String subCategoryName, Date creationDTM, Date updationDTM, boolean isActive, NewsCategory newsCategory) {
        this.subCategoryName = subCategoryName;
        this.creationDTM = creationDTM;
        this.updationDTM = updationDTM;
        this.isActive = isActive;
        this.newsCategory = newsCategory;
    }

    public int getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(int subCategory) {
        this.subCategory = subCategory;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
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
}
