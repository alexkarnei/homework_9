package by.itstep.karnei.service;

import by.itstep.karnei.model.Gamer;
import by.itstep.karnei.model.Games;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Scanner;

import static by.itstep.karnei.service.GamerService.*;
import static org.junit.Assert.*;

public class GamerServiceTest {

    @Test
    public static void main(String[] args) {
        System.out.println("Number of players : ");
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()){
            System.out.println("Incorrectly number entered!");
            return;
        }
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            systemGamerCheck(gamers);
        }
        System.out.println(gamers.toString());

        ArrayList<Games> gameAllGamer = gameEveryonePlay();
        printGame(gameAllGamer);
    }
}