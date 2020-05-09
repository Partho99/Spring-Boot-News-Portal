package com.etcenteprise.newsoftheearth.services;

import com.etcenteprise.newsoftheearth.entities.News;

import java.util.List;

public interface NewsServices {
    List<News> findAllNews();

    News findById(long id);

    long saveNews(News news);

    void deleteById(long id);

}
