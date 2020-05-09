package com.etcenteprise.newsoftheearth.entities;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
public class UserVerificationToken {

    private static final int EXPIRATION_TIME = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String token;

    private Date expiryTime;


    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public UserVerificationToken() {
        super();
    }

    public UserVerificationToken(final String token) {
        this.token = token;
        this.expiryTime = calculateExpiryTime(EXPIRATION_TIME);
    }

    public UserVerificationToken(final String token, final User user) {
        this.token = token;
        this.user = user;
        this.expiryTime = calculateExpiryTime(EXPIRATION_TIME);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private Date calculateExpiryTime(final int inMinutes) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, inMinutes);
        return new Date(calendar.getTime().getTime());
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + ((expiryTime == null) ? 0 : expiryTime.hashCode());
        result = prime * result + ((token == null) ? 0 : token.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }


}
