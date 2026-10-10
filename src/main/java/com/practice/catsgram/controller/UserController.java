package com.practice.catsgram.controller;

import com.practice.catsgram.exception.InvalidEmailException;
import com.practice.catsgram.exception.UserAlreadyExistException;
import com.practice.catsgram.model.Post;
import com.practice.catsgram.model.User;
import com.practice.catsgram.service.PostService;
import com.practice.catsgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final PostService postService;

    @GetMapping
    public Collection<User> findAll() {
        return userService.findAll();
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @PutMapping
    public User update(@RequestBody User user) {
        return userService.update(user);
    }

    @GetMapping("/{email}")
    public User findUserByEmail(@PathVariable String email) {
        return userService.findUserByEmail(email);
    }

    @GetMapping("/{userId}/posts/list")
    public List<Post> listPosts(
            @PathVariable String userId,
            @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate from,
            @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate to
    ) {
        return postService.listPosts(userId, from, to);
    }
}
