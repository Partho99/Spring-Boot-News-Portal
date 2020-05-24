package com.etcenteprise.newsoftheearth.services;

import com.etcenteprise.newsoftheearth.entities.News;
import com.etcenteprise.newsoftheearth.entities.NewsCategory;
import com.etcenteprise.newsoftheearth.repositories.NewsCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class NewsCategoryServicesImpl implements NewsCategoryServices {

    @Autowired
    private NewsCategoryRepository newsCategoryRepository;

    @Override
    public List<NewsCategory> findAllNewsCategory() {
        return newsCategoryRepository.findAllNewsCategory();
    }

    @Override
    public NewsCategory findById(long id) {
        return null;
    }

    @Override
    public void saveNewsCategory(NewsCategory newsCategory) {
        newsCategoryRepository.saveNewsCategory(newsCategory);
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
        return newsCategoryRepository.findByCategoryName(categoryName);
    }
}
