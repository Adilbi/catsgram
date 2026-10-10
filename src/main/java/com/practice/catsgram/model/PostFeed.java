package com.practice.catsgram.model;

import com.practice.catsgram.service.PostService;
import lombok.RequiredArgsConstructor;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;


public class PostFeed {
    private String sort;
    private int size;
    private List<String> friends = new ArrayList<>();

    public PostFeed(String sort, int size) {
        this.sort = sort;
        this.size = size;
    }

    public PostFeed() {
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<String> getFriends() {
        return friends;
    }
    public void setFriends (List<String> friends) {
        this.friends = friends;
    }
}
