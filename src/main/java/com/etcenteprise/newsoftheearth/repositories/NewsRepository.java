package com.etcenteprise.newsoftheearth.repositories;

import com.etcenteprise.newsoftheearth.entities.News;
import com.etcenteprise.newsoftheearth.entities.Users;

import java.util.List;


public interface NewsRepository {
    List<News> findAllNews();

    News findById(long id);

    void saveNews(News news);

    void deleteById(long id);

    List<Users> showAllUsers();

}
