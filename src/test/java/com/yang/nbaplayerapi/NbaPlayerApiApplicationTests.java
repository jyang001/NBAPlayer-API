package com.yang.nbaplayerapi;

import com.yang.nbaplayerapi.controller.PlayerController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class NbaPlayerApiApplicationTests {

    @Autowired
    PlayerController playerController;

    @Test
    void contextLoads() throws Exception {
        assertThat(playerController).isNotNull();
    }

}