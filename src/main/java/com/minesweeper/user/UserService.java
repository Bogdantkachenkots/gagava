package com.minesweeper.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Service
public class UserService implements UserRepository {
    private final Logger logger = LoggerFactory.getLogger(UserService.class.getSimpleName());

    private static final String TABLE_NAME = "Users";

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");

    @Autowired
    private RedisTemplate redisTemplate;

    private HashOperations<String, String, User> hashOperations;

    @PostConstruct
    private void initializeHashOperations() {
        hashOperations = redisTemplate.opsForHash();
    }


    @Override
    public User createUser(User user) {

        if (hashOperations.putIfAbsent(TABLE_NAME, user.getUsername(), user)) {
            user.setCreatedAt(Calendar.getInstance());
            logger.info("user successfully created");
        }
        return user;
    }

    @Override
    public User getUser(String username) {
        return null;
    }
}
