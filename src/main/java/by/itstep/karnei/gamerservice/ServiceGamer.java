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
        Games games = player.getGames();
        if (players.get(nick) != null) {
            throw new UserAlreadyExistException();
        }
        players.put(nick, game);
        Map<Games, Integer> keyGame = rating.get(games);
        for (Games oneGame : game) {
            games = oneGame;
            if (keyGame == null) {
                keyGame = new HashMap<>();
                keyGame.put(games, player.getRating());
            } else {
                keyGame.put(games, player.getRating());
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
        if (gamer.getNick() == null) {
            throw new UserNotFoundException();
        } else if (gamer.getGames()==null) {
            gamer.setRating(gamer.getRating() + 1);
        } else throw new UserNotFoundException();

    }

    public int returnRatingInGame(String nick, Games game) throws UserNotFoundException {
        if (rating.get(nick) == null || rating.get(nick).get(game) == null) {
            throw new UserNotFoundException();
        }
        else{

        }
        return rating.get(nick).get(game);
    }
}

