package com.etcenteprise.newsoftheearth.repositories;

import com.etcenteprise.newsoftheearth.entities.User;
import com.etcenteprise.newsoftheearth.entities.UserVerificationToken;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.util.Base64;

@Repository
@Transactional
public class UserVerificationTokenRepositoryImpl implements UserVerificationTokenRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    @Override
    public void saveToken(User user, String token) {
        UserVerificationToken userToken = new UserVerificationToken(token, user);
        entityManager.persist(userToken);
    }

    @Override
    public UserVerificationToken findById() {
        return null;
    }

    @Override
    public String constructToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    @Override
    public UserVerificationToken findByUserId(User id) {
        Query userVerificationToken = entityManager.createQuery("from UserVerificationToken where user=:id", UserVerificationToken.class);
        userVerificationToken.setParameter("id",id);
        UserVerificationToken verificationToken = (UserVerificationToken) userVerificationToken.getSingleResult();
        return verificationToken;

    }


}
