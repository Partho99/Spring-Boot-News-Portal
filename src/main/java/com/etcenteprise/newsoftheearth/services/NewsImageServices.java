package com.etcenteprise.newsoftheearth.services;

import com.etcenteprise.newsoftheearth.entities.Image;

import java.util.List;

public interface NewsImageServices {
    List<Image> findAllImage();
    Image findById(long id);
    void saveImage(Image image);
    void deleteById();
}
