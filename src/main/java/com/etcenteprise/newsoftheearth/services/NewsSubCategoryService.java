package com.etcenteprise.newsoftheearth.services;

import com.etcenteprise.newsoftheearth.entities.NewsSubCategory;

import java.util.Set;

public interface NewsSubCategoryService {

    Set<NewsSubCategory> findAllSubCategory();

    NewsSubCategory findBySubCategoryId(int subCategoryId);

    boolean saveNewsSubCategory(NewsSubCategory newsSubCategory);

    boolean deleteById(int id);

    NewsSubCategory findByCategoryName(String subCategoryName);
}
