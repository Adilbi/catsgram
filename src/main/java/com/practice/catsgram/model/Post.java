package com.practice.catsgram.model;
import java.time.Instant;
public class Post {

    private final String author; // автор
    private final Instant creationDate = Instant.now(); // дата создания
    private String description; // описание
    private String photoUrl; // url-адрес фотографии
    private int postId;
    public Post(String author, String description, String photoUrl) {
        this.author = author;
        this.description = description;
        this.photoUrl = photoUrl;
    }

    public String getAuthor() {
        return author;
    }
    public Instant getCreationDate() {
        return  creationDate;
    }
    public String getDescription() {
        return description;
    }
    public String getPhotoUrl() {
        return photoUrl;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}