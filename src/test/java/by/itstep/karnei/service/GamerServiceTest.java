package by.itstep.karnei.service;

import by.itstep.karnei.model.Gamer;
import by.itstep.karnei.model.Games;
import org.junit.Test;

import java.util.ArrayList;

import static by.itstep.karnei.service.GamerService.*;
import static org.junit.Assert.*;

public class GamerServiceTest {

    @Test
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            systemGamerCheck(gamers);
        }
        System.out.println(gamers.toString());

        ArrayList<Games> gameAllGamer = gameEveryOnePlay();
        System.out.println("Games that all gamers play : "+gameAllGamer);
    }
}