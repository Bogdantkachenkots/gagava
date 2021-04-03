package com.minesweeper.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class.getSimpleName());


    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        logger.info("create user entrypoint");
        if (user == null || user.getUsername().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user can not be null or empty");
        }
        return userService.createUser(user);
    }

}
