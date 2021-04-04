package com.minesweeper.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Objects;

@Service
public class UserService implements UserRepository {
    private final Logger logger = LoggerFactory.getLogger(UserService.class.getSimpleName());

    private static final String TABLE_NAME = "Users";

    @Autowired
    private RedisTemplate redisTemplate;

    private HashOperations<String, String, User> hashOperations;

    @PostConstruct
    private void initializeHashOperations() {
        hashOperations = redisTemplate.opsForHash();
    }


    @Override
    public User createUser(User user) throws Exception {

        if (Objects.nonNull(hashOperations.get(TABLE_NAME, user.getUsername()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user already exists");
        }

        hashOperations.put(TABLE_NAME, user.getUsername(), user);
        user.setCreatedAt(Calendar.getInstance());
        logger.info("user successfully created");

        return user;
    }

    @Override
    public User getUser(String username) {
        User user = hashOperations.get(TABLE_NAME, username);
        if (Objects.isNull(user)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user do not exists");
        }

        return user;
    }
}
