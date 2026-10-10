package com.practice.catsgram.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    private static final Logger log = LoggerFactory.getLogger(SimpleController.class);

    @GetMapping("/home")
    public String homePage() {
        log.debug("Получен запрос GET /home.");
        return "Котограм";
    }
}
