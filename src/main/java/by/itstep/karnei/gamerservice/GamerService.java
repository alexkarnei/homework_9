package by.itstep.karnei.gamerservice;

import by.itstep.karnei.gamerservice.Exception.UserAlreadyExistException;
import by.itstep.karnei.gamerservice.Exception.UserNotFoundException;

import java.util.Set;

public interface GamerService {

    void checkInGamer(Gamer gamer) throws UserAlreadyExistException;

    void addRatingInGame(Gamer gamer, Games game) throws UserNotFoundException, UserAlreadyExistException;

    int returnRatingInGame(String nick, Games game) throws UserNotFoundException;

    Set<Games> returnSetGamesWhichPlayAllGamers();
}
