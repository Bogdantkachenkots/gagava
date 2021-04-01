package com.minesweeper.user;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Test
    public void createUser() {
        User user = new User();
        user.setUsername("Mati");

        Assert.assertEquals("Mati", user.getUsername());
    }
}
