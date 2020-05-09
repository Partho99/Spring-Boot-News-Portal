package com.etcenteprise.newsoftheearth.repositories;

import com.etcenteprise.newsoftheearth.entities.News;
import com.etcenteprise.newsoftheearth.entities.NewsCategory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class NewsCategoryRepositoryImpl implements NewsCategoryRepository {

    @Autowired
    private EntityManager entityManager;


    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Session currentSession() {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession;
    }

    @Override
    public List<NewsCategory> findAllNewsCategory() {
        Query<NewsCategory> query = currentSession().createQuery("from NewsCategory", NewsCategory.class);
        List<NewsCategory> newsCategoryList = query.getResultList();
        return newsCategoryList;
    }

    @Override
    public NewsCategory findById(long id) {
        return null;
    }

    @Override
    public void saveNews(NewsCategory news) {
    }

    @Override
    public void deleteById(long id) {
    }

    @Override
    public List<NewsCategory> showCategoriesInMenuBar() {
        return null;
    }

    @Override
    public List<News> findByCategoryName(String categoryName) {
        String HQL = " from NewsCategory";
        Query<NewsCategory> query = currentSession().createQuery(HQL, NewsCategory.class);
        List<NewsCategory> newsCategoryList = query.getResultList();
        ArrayList<News> newsByCategoryName = new ArrayList<>();
        for (NewsCategory newsCategory : newsCategoryList) {
            if (newsCategory.getCategoryName().equalsIgnoreCase(categoryName)) {
                newsByCategoryName.addAll(newsCategory.getNewsList());
            }
        }
        return newsByCategoryName;
    }
}
