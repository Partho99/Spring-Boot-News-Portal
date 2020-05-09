package com.etcenteprise.newsoftheearth.services;

import com.etcenteprise.newsoftheearth.entities.Image;
import com.etcenteprise.newsoftheearth.repositories.NewsImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsImageServicesImpl implements NewsImageServices {
    @Autowired
    private NewsImageRepository newsImageRepository;

    @Override
    public List<Image> findAllImage() {
        return null;
    }

    @Override
    public Image findById(long id) {
        return null;
    }

    @Override
    public void saveImage(Image image) {
        newsImageRepository.saveImage(image);
    }

    @Override
    public void deleteById() {

    }
}
