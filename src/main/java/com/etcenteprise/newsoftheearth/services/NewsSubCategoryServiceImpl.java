package com.etcenteprise.newsoftheearth.services;

import com.etcenteprise.newsoftheearth.entities.NewsSubCategory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Set;
@Repository
@Transactional
public class NewsSubCategoryServiceImpl implements NewsSubCategoryService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Set<NewsSubCategory> findAllSubCategory() {

        return null;
    }

    @Override
    public NewsSubCategory findBySubCategoryId(int subCategoryId) {
        return null;
    }

    @Override
    public boolean saveNewsSubCategory(NewsSubCategory newsSubCategory) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public NewsSubCategory findByCategoryName(String subCategoryName) {
        return null;
    }
}
