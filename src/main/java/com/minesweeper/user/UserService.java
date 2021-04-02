package com.minesweeper.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;

@Service
public class UserService implements UserRepository {
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
        return null;
    }

    @Override
    public User getUser(String username) {
        return null;
    }
}
