package com.etcenteprise.newsoftheearth.repositories;

import com.etcenteprise.newsoftheearth.entities.News;
import com.etcenteprise.newsoftheearth.entities.Users;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public class NewsRepositoryImpl implements NewsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<News> findAllNews() {
        TypedQuery<News> newsList = entityManager.createQuery("from News where isActive=true", News.class);
        List<News> news = newsList.getResultList();
        return news;
    }

    @Override
    public News findById(long id) {
        Query news = entityManager.createQuery("from News where newsId=:id and isActive=true", News.class);
        news.setParameter("id", id);
        News n = (News) news.getSingleResult();
        return n;
    }

    @Override
    public void saveNews(News news) {
        entityManager.persist(news);
    }

    @Override
    public void deleteById(long id) {
        News news = entityManager.find(News.class, id);
        if (news != null) {
            entityManager.remove(news);
        } else {
            System.out.println("news is not exists...!");
        }
    }

    @Override
    public List<Users> showAllUsers() {
        TypedQuery<Users> newsList = entityManager.createQuery("from users", Users.class);
        List<Users> users = newsList.getResultList();
        return users;
    }
}
