package by.itstep.karnei.gamerservice;

import by.itstep.karnei.gamerservice.exception.UserAlreadyExistException;
import by.itstep.karnei.gamerservice.exception.UserNotFoundException;

import java.util.*;
import java.util.function.Consumer;

public class ServiceGamer implements GamerService {

    private HashMap<String, Set<Games>> players = new HashMap<>();
    private HashMap<String, Map<Games, Integer>> rating = new HashMap<>();

    @Override
    public void checkInGamer(Gamer player) throws UserAlreadyExistException {
        String nick = player.getNick();
        Set<Games> game = player.getGame();
        if (players.get(nick) != null) {
            throw new UserAlreadyExistException();
        }
        players.put(nick, game);
        int ratingInGame = player.getRating();
        addInMapGamerWithRatingInGame(player, nick, game, ratingInGame);
    }


    private void addInMapGamerWithRatingInGame(Gamer player, String nick, Set<Games> game, int ratingInGame) {
        Map<Games, Integer> keyGame = rating.get(player.getNick());
        for (Games games : game) {
            if (keyGame == null) {
                keyGame = new HashMap<>();
                keyGame.put(games, ratingInGame);
            } else {
                keyGame.put(games, ratingInGame);
            }
        }
        rating.put(nick, keyGame);
    }

    @Override
    public String toString() {
        return "ServiceGamer{" +
                "players=" + players +
                ", rating=" + rating +
                '}';
    }

    @Override
    public void addRatingInGame(Gamer gamer, Games game) throws UserNotFoundException {
        if (rating.get(gamer.getNick()) == null) {
            throw new UserNotFoundException();
        } else if (rating.get(gamer.getNick()).get(game) != null) {
            int ratingInGame = rating.get(gamer.getNick()).get(game) + 1;
            Set<Games> gameGamer = gamer.getGame();
            addMapAfterAddRatingGamerInGame(gamer, game, ratingInGame, gameGamer);
            gamer.setRating(ratingInGame);
        } else throw new UserNotFoundException();
    }

    private void addMapAfterAddRatingGamerInGame(Gamer gamer, Games game, int ratingInGame, Set<Games> gameGamer) {
        Map<Games, Integer> keyGame = rating.get(gamer.getNick());
        for (Games games : gameGamer) {
            if (game.equals(games)) {
                if (keyGame == null) {
                    keyGame = new HashMap<>();
                    keyGame.put(game, ratingInGame);
                } else {
                    keyGame.put(game, ratingInGame);
                }
            }
        }
        rating.put(gamer.getNick(), keyGame);
    }

    public int returnRatingInGame(String nick, Games game) throws UserNotFoundException {
        if (rating.get(nick) == null || rating.get(nick).get(game) == null) {
            throw new UserNotFoundException();
        }
        return rating.get(nick).get(game);
    }

    @Override
    public Set<Games> returnSetGamesWhichPlayAllGamers() {
        Set<Games> setGames = new HashSet<>();
        Games[] games = Games.values();

        Collection<Set<Games>> gamesAllGamers = players.values();
        for (Games game : games) {
            int count = 0;
            for (Set<Games> set : gamesAllGamers) {
                if (set.contains(game)) {
                    count++;
                }
            }
            if (count == gamesAllGamers.size()) {
                setGames.add(game);
            }
        }
        return setGames;
    }

    @Override
    public void getBestGamersInGame(Games game) {
        Map<String, Integer> ratingAll = new HashMap<>();
        for (Map.Entry<String, Map<Games, Integer>> mapEntry : rating.entrySet()) {
            String nick = mapEntry.getKey();
            int ratingInGame = mapEntry.getValue().get(game);
            ratingAll.put(nick, ratingInGame);
        }
        System.out.println(" 10 best players in game " + game+ ":");
        final int[] count = {0};
        ratingAll.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(getEntryConsumer(count));
    }

    private Consumer<Map.Entry<String, Integer>> getEntryConsumer(int[] count) {
        return s -> {
            if (count[0] < 10)
                System.out.println(s);
            count[0]++;
        };
    }

    @Override
    public void getBestGamersInAllGame() {
        Map<String, Integer> ratingAll = new HashMap<>();
        for (Map.Entry<String, Map<Games, Integer>> mapEntry : rating.entrySet()) {

            String nick = mapEntry.getKey();
            int ratingInGame = mapEntry.getValue().values().stream().mapToInt(value -> value).sum();
            ratingAll.put(nick, ratingInGame);
        }
        System.out.println(" 10 best players in all games :");
        final int[] count = {0};
        ratingAll.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(getEntryConsumer(count));

    }
}

