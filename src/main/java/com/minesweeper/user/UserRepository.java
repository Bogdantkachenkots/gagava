package com.minesweeper.user;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    User createUser(User user);
    User getUser(String username);
}
