package com.etcenteprise.newsoftheearth.repositories;

import com.etcenteprise.newsoftheearth.entities.NewsSubCategory;

import java.util.List;

public interface NewsSubCategoryRepository {

    List<NewsSubCategory> findAllSubCategory();

    NewsSubCategory findBySubCategoryId(int subCategoryId);

    boolean saveNewsSubCategory(NewsSubCategory newsSubCategory);

    boolean deleteById(int id);

    NewsSubCategory findByCategoryName(String subCategoryName);
}
