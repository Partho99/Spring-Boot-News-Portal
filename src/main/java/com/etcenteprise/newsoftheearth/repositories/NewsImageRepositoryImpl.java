package com.etcenteprise.newsoftheearth.repositories;

import com.etcenteprise.newsoftheearth.entities.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class NewsImageRepositoryImpl implements NewsImageRepository {

    @Autowired
    private EntityManager entityManager;

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
        entityManager.persist(image);
    }

    @Override
    public void deleteById() {

    }
}
