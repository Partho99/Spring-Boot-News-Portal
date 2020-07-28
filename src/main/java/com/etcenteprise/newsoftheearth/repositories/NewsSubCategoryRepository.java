package com.etcenteprise.newsoftheearth.repositories;

import com.etcenteprise.newsoftheearth.entities.NewsSubCategory;

import java.util.List;

public interface NewsSubCategoryRepository extends abc{

    List<NewsSubCategory> findAllSubCategory();

    NewsSubCategory findBySubCategoryId(int subCategoryId);

    List<NewsSubCategory> findSubCategoryByCategoryId(int categoryId);

    boolean saveNewsSubCategory(NewsSubCategory newsSubCategory);

    boolean deleteById(int id);

    NewsSubCategory findBySubCategoryName(String subCategoryName);

    default int simpleAddition() {
        return 100;
    }

    interface newMethodAdded {
        List<NewsSubCategory> findNewsSubCategoriesByCategoryName(String categoryName);
    }

}

interface  abc{
    int fbc();
}


