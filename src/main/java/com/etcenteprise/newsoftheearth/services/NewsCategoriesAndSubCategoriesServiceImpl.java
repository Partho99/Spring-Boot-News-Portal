package com.etcenteprise.newsoftheearth.services;

import com.etcenteprise.newsoftheearth.entities.News;
import com.etcenteprise.newsoftheearth.entities.NewsCategoriesAndSubCategories;

import java.util.List;

public class NewsCategoriesAndSubCategoriesServiceImpl implements NewsCategoriesAndSubCategoriesService {

    @Override
    public List<NewsCategoriesAndSubCategories> findAllNewsCategory() {
        return null;
    }

    @Override
    public NewsCategoriesAndSubCategories findById(long id) {
        return null;
    }

    @Override
    public void saveNewsCategory(NewsCategoriesAndSubCategories newsCategoriesAndSubCategories) {

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
        return null;
    }

    @Override
    public List<NewsCategoriesAndSubCategories> findAllSubCategory() {
        return null;
    }

    @Override
    public List<NewsCategoriesAndSubCategories> findSubCategoryChildBySubCategoryName(String subCategoryChild) {
        return null;
    }

    @Override
    public NewsCategoriesAndSubCategories findBySubCategoryId(int subCategoryId) {
        return null;
    }

    @Override
    public List<NewsCategoriesAndSubCategories> findSubCategoryByCategoryId(int categoryId) {
        return null;
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
        return null;
    }

    @Override
    public List<NewsCategoriesAndSubCategories> findNewsSubCategoriesByCategoryName(String categoryName) {
        return null;
    }
}
