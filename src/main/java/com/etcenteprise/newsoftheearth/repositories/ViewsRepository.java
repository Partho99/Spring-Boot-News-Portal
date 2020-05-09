package com.etcenteprise.newsoftheearth.repositories;

import com.etcenteprise.newsoftheearth.entities.Views;

import java.util.List;

public interface ViewsRepository {
    List<Views> getPopularNewsByViews();
}
