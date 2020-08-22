package com.etcenteprise.newsoftheearth.services;

import com.etcenteprise.newsoftheearth.entities.NewsImage;

import java.util.List;

public interface NewsImageServices {
    List<NewsImage> findAllImage();

    NewsImage findById(long id);

    void saveImage(NewsImage image);

    void deleteById();

    List<NewsImage> findByNews(long newsId);
}
