package com.minesweeper.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/users")
    public void createUser(@RequestBody User user) {
        if (user == null || user.getUsername().isEmpty()) {

        }
        // TODO User creation
    }

}
