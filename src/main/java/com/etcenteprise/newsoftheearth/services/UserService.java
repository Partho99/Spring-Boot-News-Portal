package com.etcenteprise.newsoftheearth.services;

import com.etcenteprise.newsoftheearth.entities.User;

import java.util.Optional;

public interface UserService {
    void save(User user);

    Optional<User> findByUsername(String username);

    User findByEmail(String email);

    boolean verifyingUser(User id, String token);
}
