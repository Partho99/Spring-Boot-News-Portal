package com.etcenteprise.newsoftheearth.repositories;

import com.etcenteprise.newsoftheearth.entities.NewsSubCategory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class NewsSubCategoryRepositoryImpl implements NewsSubCategoryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<NewsSubCategory> findAllSubCategory() {
        Query allSubCategory = entityManager.createQuery("from NewsSubCategory",NewsSubCategory.class);
        List<NewsSubCategory> subCategories =  allSubCategory.getResultList();
        return subCategories;
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
