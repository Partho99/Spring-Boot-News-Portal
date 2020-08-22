package com.etcenteprise.newsoftheearth.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class NewsCategoriesAndSubCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CategoryId;
    private String CategoryName;
    private Date creationDTM;
    private Date updationDTM;
    private boolean isActive;
    @JsonIgnore
    @OneToMany(mappedBy = "categoriesAndSubCategories")
    private List<News> newsList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private NewsCategoriesAndSubCategories parentId;

    public NewsCategoriesAndSubCategories() {

    }

    public NewsCategoriesAndSubCategories(String categoryName, Date creationDTM, Date updationDTM, boolean isActive) {
        CategoryName = categoryName;
        this.creationDTM = creationDTM;
        this.updationDTM = updationDTM;
        this.isActive = isActive;
    }

    public NewsCategoriesAndSubCategories(String categoryName, Date creationDTM, Date updationDTM, boolean isActive, List<News> newsList) {
        CategoryName = categoryName;
        this.creationDTM = creationDTM;
        this.updationDTM = updationDTM;
        this.isActive = isActive;
        this.newsList = newsList;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
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

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    public NewsCategoriesAndSubCategories getParentId() {
        return parentId;
    }

    public void setParentId(NewsCategoriesAndSubCategories parentId) {
        this.parentId = parentId;
    }
}
