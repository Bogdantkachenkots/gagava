package com.minesweeper.user;

import com.minesweeper.game.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class UserService implements UserRepository {
    private static final String TABLE_NAME = "Users";

    private final RedisTemplate<String, Object> redisTemplate;

    private HashOperations<String, Long, Game> hashOperations;

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
