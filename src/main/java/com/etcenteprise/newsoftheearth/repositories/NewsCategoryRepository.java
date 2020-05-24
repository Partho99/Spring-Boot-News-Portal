package com.etcenteprise.newsoftheearth.repositories;

import com.etcenteprise.newsoftheearth.entities.News;
import com.etcenteprise.newsoftheearth.entities.NewsCategory;

import java.util.List;

public interface NewsCategoryRepository {

    List<NewsCategory> findAllNewsCategory();

    NewsCategory findById(long id);

    void saveNewsCategory(NewsCategory newsCategory);

    void deleteById(long id);

    List<NewsCategory> showCategoriesInMenuBar();

    List<News> findByCategoryName(String categoryName);
}
