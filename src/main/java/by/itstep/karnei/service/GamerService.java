package by.itstep.karnei.service;

import by.itstep.karnei.model.Gamer;
import by.itstep.karnei.model.Games;
import by.itstep.karnei.model.RatingInGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GamerService {

    public static ArrayList<Gamer> gamers = new ArrayList<>();
    private static Games[] games = Games.values();
    public static HashMap<Gamer,RatingInGame> ratingInGame =new HashMap<>();
    public static int rating;
    public static boolean isWin;

    public static ArrayList<Gamer> systemGamerCheck(ArrayList<Gamer> gamers) {
        Gamer gamer = new Gamer(getNikName(), getGame());
        checkInGamer(gamer, gamers);
        return gamers;
    }

    private static void checkInGamer(Gamer gamer, ArrayList<Gamer> gamers) {
        if (checkNik(gamer, gamers))
            gamers.add(gamer);
    }

    private static boolean checkNik(Gamer gamer, ArrayList<Gamer> gamers) {
        for (Gamer gamer1 : gamers) {
            if (gamer.getNik().equals(gamer1.getNik())) {
                System.out.println("Nik is not empty !");
                return false;
            }
        }
        return true;
    }

    private static String getNikName() {
        System.out.println("Input nik : ");
        Scanner sc = new Scanner(System.in);
        String gameName = sc.next();
        return gameName;
    }

    private static ArrayList<Games> getGame() {

        ArrayList<Games> gamerGames = new ArrayList<>();
        for (int i = 0; i < games.length; i++) {
            Games element = games[i];
            System.out.println(" Play in " + games[i] + " 1 = Yes / 0 = No ?");
            Scanner scanner = new Scanner(System.in);
            String flag = scanner.next();
            if (flag.equals("1")) {
                gamerGames.add(element);
            }
        }
        gamerGames.trimToSize();
        return gamerGames;
    }

    public static ArrayList<Games> gameEveryonePlay() {
        ArrayList<Games> gamesAllGamer = new ArrayList<>();
        for (int i = 0; i < games.length; i++) {
            int count = 0;
            for (int j = 0; j < gamers.size(); j++) {
                if (gamers.get(j).getGameAll().contains(games[i])) {
                    gamesAllGamer.add(games[i]);
                    count++;
                }
                if (count != gamers.size()) {
                    gamesAllGamer.remove(games[i]);
                }
            }
        }
        return gamesAllGamer;
    }


    public static void printGame(ArrayList<Games> gameAllGamer) {
        if (gameAllGamer.isEmpty()) {
            System.out.println("No games played by everyone.");

        } else {
            System.out.println("Games that all gamers play : " + gameAllGamer);
        }
    }
}
