package com.etcenteprise.newsoftheearth.repositories;

import com.etcenteprise.newsoftheearth.entities.Views;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository
public class ViewsRepositoryImpl implements ViewsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    //Query<Views> query = currentSession.createQuery(HQL, Views.class).setParameter("number",Long.valueOf(20));
    //from Views v inner join fetch v.news where v.viewsCount > :number
    @Transactional
    @Override
    public List<Views> getPopularNewsByViews() {
        Session currentSession = entityManager.unwrap(Session.class);
        String HQL = "from Views";
        Query<Views> query = currentSession.createQuery(HQL, Views.class);
        List<Views> newsList = query.getResultList();
        ArrayList<Views> popularNews = new ArrayList<>();
        for (Views model : newsList) {
            if (model.getViewsCount() > 10)
                popularNews.add(model);
        }
        return popularNews;
    }
}
