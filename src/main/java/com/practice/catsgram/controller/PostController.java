package com.practice.catsgram.controller;

import com.practice.catsgram.exception.IncorrectParameterException;
import com.practice.catsgram.model.Post;
import com.practice.catsgram.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/posts")
    public List<Post> findAll( @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                               @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
                               @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {

        if (!(sort.equals("asc") || sort.equals("desc"))) {
            throw  new IncorrectParameterException("sort");
        }
        if (page < 0) {
            throw  new IncorrectParameterException("page");
        }
        if (size <= 0){
            throw  new IncorrectParameterException("size");
        }
        Integer from = page * size;
        return postService.findAll(size, from, sort);
    }
    @PostMapping("/posts")
    public Post create(@RequestBody Post post) {
        return postService.create(post);
    }

    @GetMapping("/posts/{postId}")
    public Post findById(@PathVariable int postId) {
        return postService.findById(postId);
    }
}
