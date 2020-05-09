package com.etcenteprise.newsoftheearth.services;

import com.etcenteprise.newsoftheearth.entities.Views;
import com.etcenteprise.newsoftheearth.repositories.ViewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewsServicesImpl implements ViewsServices {


    @Autowired
    private ViewsRepository viewsRepository;

    @Override
    public List<Views> getPopularNewsByViews() {
        return viewsRepository.getPopularNewsByViews();
    }
}
