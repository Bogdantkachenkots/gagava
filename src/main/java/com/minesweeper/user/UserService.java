package com.minesweeper.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class UserService implements UserRepository {
    private static final String TABLE_NAME = "Users";

    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");

    private final RedisTemplate<String, Object> redisTemplate;

    private HashOperations<String, String, User> hashOperations;

    @Autowired
    public UserService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

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
