package com.etcenteprise.newsoftheearth.repositories;

import com.etcenteprise.newsoftheearth.entities.Image;

import java.util.List;

public interface NewsImageRepository {
    List<Image> findAllImage();
    Image findById(long id);
    void saveImage(Image image);
    void deleteById();
}
