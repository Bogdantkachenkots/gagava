package com.minesweeper.game;

import com.minesweeper.exceptions.KaboomException;
import com.minesweeper.user.User;
import com.minesweeper.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@Tag(name = "Game", description = "The game controller of the API")
public class GameController {

    private final Logger logger = LoggerFactory.getLogger(GameController.class.getSimpleName());

    @Autowired
    private GameService gameService;

    @Autowired
    private UserService userService;

    @PostMapping("/games")
    @Operation(summary = "Create a new game", description = "Create a new game for the given user", responses = {
            @ApiResponse(responseCode = "200", description = "game created"),
            @ApiResponse(responseCode = "404", description = "user not found")
    })
    public ResponseEntity<Void> createGame(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Game to be created", required = true,
                    content = @Content(schema = @Schema(implementation = Game.class)))
            @RequestBody @Valid Game game) {

        logger.info("create a new game");
        if (Objects.isNull(userService.getUser(game.getUsername()))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
        }
        gameService.createGame(game);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/games/{gameId}/users/{userName}")
    @Operation(summary = "Start a game", description = "Start an already created game", responses = {
            @ApiResponse(responseCode = "200", description = "game started"),
            @ApiResponse(responseCode = "404", description = "user do not exists"),
            @ApiResponse(responseCode = "404", description = "game do not exists")
    })
    public ResponseEntity<Game> startGame(@Parameter(description = "Game's name to start", required = true)
                                          @PathVariable("gameId") String gameId,
                                          @Parameter(description = "User's name", required = true)
                                          @PathVariable("userName") String userName) {
        logger.info("starting game");
        User user = userService.getUser(userName);
        if (Objects.isNull(user)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user do not exists");
        }

        Game game = gameService.startGame(gameId);
        return new ResponseEntity<>(game, HttpStatus.OK);

    }

    @PostMapping("/games/{gameId}/users/{userName}/click")
    @Operation(summary = "Click a cell", description = "Start an already created game", responses = {
            @ApiResponse(responseCode = "200", description = "cell clicked"),
            @ApiResponse(responseCode = "404", description = "user do not exists"),
            @ApiResponse(responseCode = "404", description = "game do not exists")
    })
    public ResponseEntity<Void> clickCell(@Parameter(description = "Game's name") @PathVariable("gameId") String gameId,
                                          @Parameter(description = "User's name") @PathVariable("userName") String userName,
                                          @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Click event containing clicked cell position",
                                                  required = true, content = @Content(schema = @Schema(implementation = Click.class)))
                                          @RequestBody Click click) throws KaboomException {
        logger.info("click cell");
        Game game = gameService.getGame(gameId);
        if (Objects.isNull(game)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "game do no exists");
        }

        User user = userService.getUser(userName);
        if (Objects.isNull(user)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user do not exists");
        }

        gameService.click(game, click);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
