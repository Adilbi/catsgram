package com.practice.catsgram.service;
import com.practice.catsgram.exception.InvalidEmailException;
import com.practice.catsgram.exception.UserAlreadyExistException;
import com.practice.catsgram.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class UserService {
    private final Map<String,User> users = new HashMap<>();

    public Collection<User> findAll() {
        return users.values();
    }


    public User create(@RequestBody User user) {
        checkEmail(user);
        if (users.containsKey(user.getEmail())) {
            throw new UserAlreadyExistException("Пользователь с такой почтой уже существует");
        }
        users.put(user.getEmail(), user);
        return user;
    }
    public User update(@RequestBody User user) {
        checkEmail(user);
        users.put(user.getEmail(), user);
        return user;
    }


    private void checkEmail(User user) {
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new InvalidEmailException("Адрес электронной почты не может быть пустым.");
        }
    }
    public User findUserByEmail (String  email) {
        return users.get(email);
    }
}
