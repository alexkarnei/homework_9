package by.itstep.karnei.gamerservice;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Gamer {
    private String nick;
    private Games games;
    private Set<Games> game;
    private int rating;

    public Gamer() {
    }

    public Games getGames() {
        return games;
    }

    public void setGames(Games games) {
        this.games = games;
    }

    public Gamer(String nick, Set<Games> game) {
        this.nick = nick;
        this.game = game;
    }

    public Gamer(String nick, Set<Games> game, int startRatingForOneGames) {
        this.nick = nick;
        this.game = game;
        this.rating = startRatingForOneGames;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Set<Games> getGame() {
        return game;
    }

    public void setGame(Set<Games> game) {
        this.game = game;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Gamer{" +
                "nick='" + nick + '\'' +
                ", games=" + games +
                ", game=" + game +
                ", rating=" + rating +
                '}';
    }
}





