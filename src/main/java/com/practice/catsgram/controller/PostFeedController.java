package com.practice.catsgram.controller;
import com.practice.catsgram.model.Post;
import com.practice.catsgram.model.PostFeed;
import com.practice.catsgram.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostFeedController {
    private final PostService postService;
    private final ObjectMapper objectMapper;

    @PostMapping("/feed/friends")
    public List <Post> findFriendsFeed (@RequestBody String body) {
        String inner = objectMapper.readValue(body, String.class);
        PostFeed feed = objectMapper.readValue(inner, PostFeed.class);
        return postService.findPostsByAuthors(feed.getFriends(), feed.getSort(), feed.getSize());
    }
}
