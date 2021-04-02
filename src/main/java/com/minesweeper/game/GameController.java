package com.minesweeper.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/games")
    public void createGame(@RequestBody Game game) {
        // TODO create game
    }

    @PostMapping("/games/{gameId}/users/{userName}")
    public void startGame(@PathVariable("gameId") int gameId, @PathVariable("userName") int userName) {
        // TODO start game
    }

    @PostMapping("/games/{gameId}/users/{userName}/click")
    public void clickCell(@PathVariable("gameId") int gameId, @PathVariable("userName") int userName) {
        // TODO click cell
    }

}
