package by.itstep.karnei.gamerservice;

import by.itstep.karnei.gamerservice.Exception.UserAlreadyExistException;
import by.itstep.karnei.gamerservice.Exception.UserNotFoundException;

import java.util.*;

public class ServiceGamer implements GamerService {

    private Map<String, Set<Games>> players = new HashMap<>();
    private Map<String, Map<Games, Integer>> rating = new HashMap<>();

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
        Set<Games> gamesAllGamers = new HashSet<>();
        return gamesAllGamers;
    }
}

