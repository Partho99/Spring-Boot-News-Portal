package com.etcenteprise.newsoftheearth.services;

import com.etcenteprise.newsoftheearth.entities.News;
import com.etcenteprise.newsoftheearth.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class NewsServicesImpl implements NewsServices {


    @Autowired
    private NewsRepository newsRepository;

    @Override
    public List<News> findAllNews() {
        return newsRepository.findAllNews();
    }

    @Override
    public News findById(long id) {
        News newsById = newsRepository.findById(id);
        return newsById;
    }

    @Override
    public long saveNews(News news) {
        news.setCreationDTM(new java.util.Date());
        news.setUpdationDTM(new java.util.Date());
        news.setActive(true);
        newsRepository.saveNews(news);

        return news.getNewsId();
    }

    @Override
    public void deleteById(long id) {
        newsRepository.deleteById(id);
    }
}
