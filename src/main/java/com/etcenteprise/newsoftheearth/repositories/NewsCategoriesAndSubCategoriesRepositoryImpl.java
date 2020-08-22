package com.etcenteprise.newsoftheearth.repositories;

import com.etcenteprise.newsoftheearth.entities.News;
import com.etcenteprise.newsoftheearth.entities.NewsCategoriesAndSubCategories;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class NewsCategoriesAndSubCategoriesRepositoryImpl implements NewsCategoriesAndSubCategoriesRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Session currentSession() {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession;
    }

    @Override
    public List<NewsCategoriesAndSubCategories> findAllNewsCategory() {
        Query<NewsCategoriesAndSubCategories> query = currentSession().createQuery("from NewsCategoriesAndSubCategories where parentId = null", NewsCategoriesAndSubCategories.class);
        List<NewsCategoriesAndSubCategories> newsCategoryList = query.getResultList();
        return newsCategoryList;
    }

    @Override
    public List<NewsCategoriesAndSubCategories> findAllNewsCategoriesAndSubCategories() {
        Query<NewsCategoriesAndSubCategories> query = currentSession().createQuery("from NewsCategoriesAndSubCategories", NewsCategoriesAndSubCategories.class);
        List<NewsCategoriesAndSubCategories> newsCategoryList = query.getResultList();
        return newsCategoryList;
    }

    @Override
    public NewsCategoriesAndSubCategories findById(long id) {
        return null;
    }

    @Override
    public void saveNewsCategory(NewsCategoriesAndSubCategories newsCategoriesAndSubCategories) {
        entityManager.persist(newsCategoriesAndSubCategories);
    }

    @Override
    public void deleteById(long id) {
    }

    @Override
    public List<NewsCategoriesAndSubCategories> showCategoriesInMenuBar() {
        return null;
    }

    @Override
    public List<News> findByCategoryName(String categoryName) {
        String HQL = " from NewsCategoriesAndSubCategories";
        Query<NewsCategoriesAndSubCategories> query = currentSession().createQuery(HQL, NewsCategoriesAndSubCategories.class);
        List<NewsCategoriesAndSubCategories> newsCategoryList = query.getResultList();
        ArrayList<News> newsByCategoryName = new ArrayList<>();
        for (NewsCategoriesAndSubCategories newsCategory : newsCategoryList) {
            if (newsCategory.getCategoryName().equalsIgnoreCase(categoryName)) {
                newsByCategoryName.addAll(newsCategory.getNewsList());
            }
        }
        return newsByCategoryName;
    }

    @Override
    public List<NewsCategoriesAndSubCategories> findAllSubCategory() {
        javax.persistence.Query allSubCategory = entityManager.createQuery("from NewsCategoriesAndSubCategories", NewsCategoriesAndSubCategories.class);
        List<NewsCategoriesAndSubCategories> subCategories = allSubCategory.getResultList();
        return subCategories;
    }

    @Override
    public NewsCategoriesAndSubCategories findBySubCategoryId(int subCategoryId) {
        javax.persistence.Query subcategoryById = entityManager.createQuery("from NewsCategoriesAndSubCategories where parentId=:subCategoryId", NewsCategoriesAndSubCategories.class);
        subcategoryById.setParameter("subCategoryId", subCategoryId);
        NewsCategoriesAndSubCategories subCategory = (NewsCategoriesAndSubCategories) subcategoryById.getSingleResult();
        return subCategory;
    }

    @Override
    public List<NewsCategoriesAndSubCategories> findSubCategoryByCategoryId(int categoryId) {
        javax.persistence.Query subCategoryByCategoryId = entityManager.createQuery("from NewsCategoriesAndSubCategories where parentId=:categoryId", NewsCategoriesAndSubCategories.class);
        subCategoryByCategoryId.setParameter("categoryId", categoryId);
        List<NewsCategoriesAndSubCategories> subCategories = subCategoryByCategoryId.getResultList();
        return subCategories;
    }

    @Override
    public boolean saveNewsSubCategory(NewsCategoriesAndSubCategories newsSubCategory) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public NewsCategoriesAndSubCategories findBySubCategoryName(String subCategoryName) {
        javax.persistence.Query findSubCategoryByName = entityManager.createQuery("from NewsCategoriesAndSubCategories where CategoryName=:subCategoryName");
        findSubCategoryByName.setParameter("subCategoryName", subCategoryName);
        NewsCategoriesAndSubCategories subCategory = (NewsCategoriesAndSubCategories) findSubCategoryByName.getSingleResult();
        return subCategory;
    }

    @Override
    public List<NewsCategoriesAndSubCategories> findNewsSubCategoriesByCategoryName(String categoryName) {
        javax.persistence.Query newsCategoryName = entityManager.createQuery("from NewsCategoriesAndSubCategories where CategoryName=:categoryName");
        newsCategoryName.setParameter("categoryName", categoryName);
        NewsCategoriesAndSubCategories category = (NewsCategoriesAndSubCategories) newsCategoryName.getSingleResult();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<NewsCategoriesAndSubCategories> query = builder.createQuery(NewsCategoriesAndSubCategories.class);
        Root<NewsCategoriesAndSubCategories> queryRoot = query.from(NewsCategoriesAndSubCategories.class);
        query.select(queryRoot);
        Path<Integer> parentId = queryRoot.get("parentId");
        query.where(builder.and(builder.equal(parentId, category.getCategoryId())));
        TypedQuery<NewsCategoriesAndSubCategories> subCategories = entityManager.createQuery(query);
        List<NewsCategoriesAndSubCategories> subCategoryList = subCategories.getResultList();
        return subCategoryList;
    }


    @Override
    public List<NewsCategoriesAndSubCategories> findSubCategoryChildBySubCategoryName(String subCategoryChild) {
        javax.persistence.Query newsSubCategoryChildName = entityManager.createQuery("from NewsCategoriesAndSubCategories where CategoryName=:subCategoryChild");
        newsSubCategoryChildName.setParameter("subCategoryChild", subCategoryChild);
        NewsCategoriesAndSubCategories subCategoryChildren = (NewsCategoriesAndSubCategories) newsSubCategoryChildName.getSingleResult();
        int c = subCategoryChildren.getCategoryId();
        javax.persistence.Query subCategories = entityManager.createQuery("from NewsCategoriesAndSubCategories where parentId=:subCategoryId");
        subCategories.setParameter("subCategoryId", ((int) c));
        List<NewsCategoriesAndSubCategories> subCategoryList = subCategories.getResultList();
        return subCategoryList;
    }
}
