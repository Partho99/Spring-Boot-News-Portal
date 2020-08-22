package com.etcenteprise.newsoftheearth.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class UserImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String imagePath;
    private Date imageUpdatedAt;
    private Date imageCreatedAt;
    private Boolean activeStatus;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UserImage() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Date getImageUpdatedAt() {
        return imageUpdatedAt;
    }

    public void setImageUpdatedAt(Date imageUpdatedAt) {
        this.imageUpdatedAt = imageUpdatedAt;
    }

    public Date getImageCreatedAt() {
        return imageCreatedAt;
    }

    public void setImageCreatedAt(Date imageCreatedAt) {
        this.imageCreatedAt = imageCreatedAt;
    }

    public Boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
    }
}
