package com.etcenteprise.newsoftheearth.services;

import com.etcenteprise.newsoftheearth.entities.NewsImage;
import com.etcenteprise.newsoftheearth.repositories.NewsImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class NewsImageServicesImpl implements NewsImageServices {
    @Autowired
    private NewsImageRepository newsImageRepository;

    @Override
    public List<NewsImage> findAllImage() {
        return null;
    }

    @Override
    public NewsImage findById(long id) {
        return null;
    }

    @Override
    public void saveImage(NewsImage image) {
        newsImageRepository.saveImage(image);
    }

    @Override
    public void deleteById() {

    }

    @Override
    public List<NewsImage> findByNews(long newsId) {
        return newsImageRepository.findByNews(newsId);
    }
}
