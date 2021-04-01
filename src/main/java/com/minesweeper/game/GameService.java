package com.minesweeper.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class GameService implements GameRepository {
    private static final String TABLE_NAME = "Games";

    private final RedisTemplate<String, Object> redisTemplate;

    private HashOperations<String, Long, Game> hashOperations;

    @Autowired
    public GameService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void initializeHashOperations() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public Game createGame(Game game) {
        return null;
    }

    @Override
    public Game updateGame(Game game) {
        return null;
    }

    @Override
    public Game getGame(String gameName) {
        return null;
    }
}
