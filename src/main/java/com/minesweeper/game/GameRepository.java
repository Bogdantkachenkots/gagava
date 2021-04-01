package com.minesweeper.game;

import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository {

    Game createGame(Game game);
    Game updateGame(Game game);
    Game getGame(String gameName);
}
