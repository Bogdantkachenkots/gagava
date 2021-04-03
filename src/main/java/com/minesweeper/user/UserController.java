package com.minesweeper.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Tag(name = "User", description = "The user controller of the API")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class.getSimpleName());

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    @Operation(summary = "Create a new user", description = "Create a new user with the given name", responses = {
            @ApiResponse(responseCode = "200", description = "user created"),
            @ApiResponse(responseCode = "400", description = "user can not be null or empty")
    })
    public User createUser(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "User to be created",
            required = true, content = @Content(schema = @Schema(implementation = User.class)))
                           @RequestBody User user) throws Exception {
        logger.info("create user");
        if (user == null || user.getUsername().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user can not be null or empty");
        }
        return userService.createUser(user);
    }

}
