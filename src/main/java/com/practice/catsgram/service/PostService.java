package com.practice.catsgram.service;
import com.practice.catsgram.exception.PostNotFoundException;
import com.practice.catsgram.exception.UserNotFoundException;
import com.practice.catsgram.model.Post;
import com.practice.catsgram.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final List<Post> posts = new ArrayList<>();
    private final UserService userService;
    private  int nextId=1;

    public PostService(UserService userService) {
        this.userService = userService;
    }

    public List<Post> findAll(Integer size, Integer from, String sort) {

        return posts.stream()
                .sorted((p0, p1) -> {

                    int comparison = p0.getCreationDate().compareTo(p1.getCreationDate());  // прямой порядок сортировки
                    if ("desc".equals(sort)) {  // обратный порядок сортировки
                        comparison *= -1;
                    }
                    return comparison;
                })
                .skip(from)
                .limit(size)
                .collect(Collectors.toList());
    }

    public Post create(Post post) {
        User author = userService.findUserByEmail(post.getAuthor());
        if (author==null) {
        throw new UserNotFoundException("Пользователь " + post.getAuthor()+ " не найден");
        }
        post.setPostId(nextId++);
        posts.add(post);
        return post;
    }

    public Post findById(int postId) {
        return posts.stream()
                .filter(x -> x.getPostId() == postId)
                .findFirst()
                .orElseThrow(() -> new PostNotFoundException(String.format("Пост № %d не найден", postId)));
    }
    public List<Post> listPosts(String userId, LocalDate fromDate, LocalDate toDate) {
        return posts.stream()
                .filter(p -> p.getAuthor().equals(userId))
                .filter(p -> {
                    LocalDate date = p.getCreationDate().atZone(ZoneId.systemDefault()).toLocalDate();
                    return !date.isBefore(fromDate) && !date.isAfter(toDate);
                })
                .toList();
    }
    public List<Post> findPostsByAuthors(List<String> friends, String sort, int size) {
        return posts.stream()
                .filter(p -> friends.contains(p.getAuthor()))
                .sorted((p0, p1) -> {
                    int comparison = p0.getCreationDate().compareTo(p1.getCreationDate());
                    if ("desc".equals(sort)) {
                        comparison *= -1;
                    }
                    return comparison;
                })
                .limit(size)
                .toList();
    }
}
