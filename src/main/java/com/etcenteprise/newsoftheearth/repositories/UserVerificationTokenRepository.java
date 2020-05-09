package com.etcenteprise.newsoftheearth.repositories;

import com.etcenteprise.newsoftheearth.entities.User;
import com.etcenteprise.newsoftheearth.entities.UserVerificationToken;

public interface UserVerificationTokenRepository {
    void saveToken(User user, String token);

    UserVerificationToken findById();

    String constructToken();

    UserVerificationToken findByUserId(User id);


}
