package com.minesweeper.user;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    User createUser(User user) throws Exception;
    User getUser(String username);
}
