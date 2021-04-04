package com.minesweeper.game;

import com.minesweeper.exceptions.KaboomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Calendar;
import java.util.Objects;
import java.util.UUID;

import static com.minesweeper.game.GameDefault.*;
import static com.minesweeper.game.Status.NEW;

@Service
public class GameService implements GameRepository {
    private static final String TABLE_NAME = "Games";

    @Autowired
    private RedisTemplate redisTemplate;

    private HashOperations<String, String, Game> hashOperations;


    @PostConstruct
    private void initializeHashOperations() {
        hashOperations = redisTemplate.opsForHash();
    }

    public Game startGame(String gameName) {
        Game game = hashOperations.get(TABLE_NAME, gameName);

        if (Objects.isNull(game)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "game do not exists");
        }

        game.setStatus(Status.IN_PROGRESS.getValue());
        game.setStartedAt(Calendar.getInstance());
        hashOperations.put(TABLE_NAME, game.getName(), game);
        return game;
    }

    public Game click(Game game, Click click) throws KaboomException {
        switch (click.getClickType()) {
            case "normal":
                game.clickCell(click.getColumn(), click.getRow());
                break;
            case "flag":
            case "questionMark":
                game.flagOrMarkCell(click.getColumn(), click.getRow(), click.getClickType());
                break;

        }

        long timeSpentMillis = Calendar.getInstance().getTimeInMillis() - game.getStartedAt().getTimeInMillis();
        game.setTimeSpent(Duration.ofMillis(timeSpentMillis));

        hashOperations.put(TABLE_NAME, game.getName(), game);
        return game;

    }

    @Override
    public Game createGame(Game game) {

        if (Objects.nonNull(hashOperations.get(TABLE_NAME, game.getName())) && game.getStatus().equals("in_progress")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "game already exists");
        }

        if (game.getName().isEmpty()) {
            game.setName(UUID.randomUUID().toString());
        }

        if (game.getRows() == 0) {
            game.setRows(rows);
        }

        if (game.getColumns() == 0) {
            game.setColumns(columns);
        }

        if (game.getMines() == 0) {
            game.setMines(mines);
        }

        if (game.getMines() > maxMines) {
            game.setMines(maxMines);
        }

        if (game.getRows() > maxRows) {
            game.setRows(maxRows);
        }

        if (game.getColumns() > maxColumns) {
            game.setColumns(maxColumns);
        }

        if (game.getMines() > (game.getColumns() * game.getRows())) {
            game.setMines(game.getColumns() * game.getRows());
        }

        game.setStatus(NEW.getValue());
        game.setCreatedAt(Calendar.getInstance());

        hashOperations.put(TABLE_NAME, game.getName(), game);
        return game;
    }

    @Override
    public Game updateGame(Game game) {
        if (Objects.isNull(hashOperations.get(TABLE_NAME, game.getName()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "game do not exist");
        }

        hashOperations.put(TABLE_NAME, game.getName(), game);
        return game;
    }


    @Override
    public Game getGame(String gameName) {
        Game game = hashOperations.get(TABLE_NAME, gameName);
        if (Objects.isNull(game)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "game not found");
        }
        return game;
    }
}
