package com.etcenteprise.newsoftheearth.services;

public interface UserSecurityService {
    String findLoggedInUsername();
    void autoLogin(String username, String password);
}
