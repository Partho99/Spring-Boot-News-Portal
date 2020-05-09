package com.etcenteprise.newsoftheearth.repositories;

import com.etcenteprise.newsoftheearth.entities.News;

import java.util.List;


public interface NewsRepository {
    List<News> findAllNews();

    News findById(long id);

    void saveNews(News news);

    void deleteById(long id);

}
