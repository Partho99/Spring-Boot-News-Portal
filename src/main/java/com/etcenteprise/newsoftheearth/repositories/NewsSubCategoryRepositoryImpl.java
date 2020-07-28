package com.etcenteprise.newsoftheearth.repositories;

import com.etcenteprise.newsoftheearth.entities.NewsCategory;
import com.etcenteprise.newsoftheearth.entities.NewsSubCategory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class NewsSubCategoryRepositoryImpl implements NewsSubCategoryRepository, NewsSubCategoryRepository.newMethodAdded{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<NewsSubCategory> findAllSubCategory() {
        Query allSubCategory = entityManager.createQuery("from NewsSubCategory", NewsSubCategory.class);
        List<NewsSubCategory> subCategories = allSubCategory.getResultList();
        return subCategories;
    }

    @Override
    public NewsSubCategory findBySubCategoryId(int subCategoryId) {
        Query subcategoryById = entityManager.createQuery("from NewsSubCategory where subCategoryId=:subCategoryId", NewsSubCategory.class);
        subcategoryById.setParameter("subCategoryId", subCategoryId);
        NewsSubCategory subCategory = (NewsSubCategory) subcategoryById.getSingleResult();
        return subCategory;
    }

    @Override
    public List<NewsSubCategory> findSubCategoryByCategoryId(int categoryId) {
        Query subCategoryByCategoryId = entityManager.createQuery("from NewsSubCategory where newsCategory.CategoryId=:categoryId", NewsSubCategory.class);
        subCategoryByCategoryId.setParameter("categoryId", categoryId);
        List<NewsSubCategory> subCategories = subCategoryByCategoryId.getResultList();
        return subCategories;
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
    public NewsSubCategory findBySubCategoryName(String subCategoryName) {
        Query findSubCategoryByName = entityManager.createQuery("from NewsSubCategory where subCategoryName=:subCategoryName");
        findSubCategoryByName.setParameter("subCategoryName", subCategoryName);
        NewsSubCategory subCategory = (NewsSubCategory) findSubCategoryByName.getSingleResult();
        return subCategory;
    }

    @Override
    public List<NewsSubCategory> findNewsSubCategoriesByCategoryName(String categoryName) {
        Query newsCategoryName = entityManager.createQuery("from NewsCategory where CategoryName=:categoryName");
        newsCategoryName.setParameter("categoryName", categoryName);
        NewsCategory category = (NewsCategory) newsCategoryName.getSingleResult();
        int c = category.getCategoryId();
        Query subCategories = entityManager.createQuery("from NewsSubCategory where newsCategory.CategoryId=:categoryId");
        subCategories.setParameter("categoryId", c);
        List<NewsSubCategory> subCategoryList = subCategories.getResultList();
        System.out.println("LOGGER:    " + c);
        for (NewsSubCategory s : subCategoryList) {
            System.out.println(s.getSubCategoryName());
        }
        return subCategoryList;
    }

    @Override
    public int fbc() {
        return 120;
    }
}
