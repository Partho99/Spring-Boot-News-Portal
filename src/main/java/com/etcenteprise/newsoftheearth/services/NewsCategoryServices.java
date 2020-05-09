package com.etcenteprise.newsoftheearth.services;

import com.etcenteprise.newsoftheearth.entities.News;
import com.etcenteprise.newsoftheearth.entities.NewsCategory;

import java.util.List;

public interface NewsCategoryServices {
    List<NewsCategory> findAllNewsCategory();
    NewsCategory findById(long id);
    void saveNews(NewsCategory news);
    void deleteById(long id);
    List<NewsCategory> showCategoriesInMenuBar();
    List<News> findByCategoryName(String categoryName);

}
