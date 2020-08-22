package com.etcenteprise.newsoftheearth.repositories;

import com.etcenteprise.newsoftheearth.entities.NewsImage;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class NewsImageRepositoryImpl implements NewsImageRepository {

    @PersistenceContext
    private EntityManager entityManager;

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
        entityManager.persist(image);
    }

    @Override
    public void deleteById() {

    }

    @Override
    public List<NewsImage> findByNews(long newsId) {
        TypedQuery<NewsImage> newsImageQuery = entityManager.createQuery("from NewsImage where news.newsId=:newsId", NewsImage.class);
        newsImageQuery.setParameter("newsId", newsId);
        List<NewsImage> newsImages = newsImageQuery.getResultList();
        return newsImages;
    }
}
