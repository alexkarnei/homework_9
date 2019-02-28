package by.itstep.karnei.gamerservice;

import by.itstep.karnei.gamerservice.Exception.UserAlreadyExistException;
import by.itstep.karnei.gamerservice.Exception.UserNotFoundException;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class ServiceGamerTest {

    @Test
    public void checkInGamerTest() throws UserAlreadyExistException {
        Set<Games> listGames = new HashSet<>();
        listGames.add(Games.WORLD_OF_TANKS);
        listGames.add(Games.FOOTBALL_MANAGER);
        listGames.add(Games.WORLD_OF_WARPLANES);
        listGames.add(Games.WORLD_OF_TANKS);
        Gamer gamer = new Gamer("Alex", listGames);

        ServiceGamer serviceGamer = new ServiceGamer();

        serviceGamer.checkInGamer(gamer);

        Gamer gamer1 = new Gamer("Alex", listGames, 0);
        ServiceGamer serviceGamer1 = new ServiceGamer();

        serviceGamer1.checkInGamer(gamer1);
    }

    @Test(expected = UserAlreadyExistException.class)
    public void checkInSecondGamerTest() throws UserAlreadyExistException {
        Set<Games> list = new HashSet<>();

        list.add(Games.WORLD_OF_TANKS);
        list.add(Games.WORLD_OF_WAR_CRAFT);
        list.add(Games.FOOTBALL_MANAGER);
        Gamer gamer = new Gamer("Alex", list);

        Set<Games> list1 = list;
        list1.remove(0);
        list1.add(Games.WORLD_OF_WARSHIP);
        Gamer gamer1 = new Gamer("Vova", list1);
        Gamer gamer2 = new Gamer("Vova", list);

        ServiceGamer serviceGamer = new ServiceGamer();

        serviceGamer.checkInGamer(gamer);
        serviceGamer.checkInGamer(gamer1);
        serviceGamer.checkInGamer(gamer2);
    }

    @Test
    public void addRatingInGamTest() throws UserNotFoundException, UserAlreadyExistException {
        Set<Games> list = new HashSet<>();

        list.add(Games.WORLD_OF_TANKS);
        list.add(Games.FOOTBALL_MANAGER);

        Gamer gamer = new Gamer("Vova", list, 0);

        ServiceGamer serviceGamer = new ServiceGamer();

        serviceGamer.checkInGamer(gamer);

        serviceGamer.addRatingInGame(gamer, Games.WORLD_OF_TANKS);
        serviceGamer.addRatingInGame(gamer, Games.WORLD_OF_TANKS);
        serviceGamer.addRatingInGame(gamer, Games.WORLD_OF_TANKS);
        serviceGamer.addRatingInGame(gamer, Games.WORLD_OF_TANKS);

        Assert.assertEquals(4, gamer.getRating());

        serviceGamer.addRatingInGame(gamer, Games.FOOTBALL_MANAGER);
        serviceGamer.addRatingInGame(gamer, Games.FOOTBALL_MANAGER);

        Assert.assertEquals(2, gamer.getRating());
    }

    @Test(expected = UserAlreadyExistException.class)
    public void addRatingSecondInGameTest() throws UserNotFoundException, UserAlreadyExistException {
        Set<Games> list = new HashSet<>();

        list.add(Games.WORLD_OF_TANKS);
        list.add(Games.FOOTBALL_MANAGER);

        Gamer gamer = new Gamer("Vova", list, 0);
        Gamer gamer1 = new Gamer("Vova", list, 0);

        ServiceGamer serviceGamer = new ServiceGamer();

        serviceGamer.checkInGamer(gamer);
        serviceGamer.checkInGamer(gamer1);

        serviceGamer.addRatingInGame(gamer, Games.WORLD_OF_TANKS);
        serviceGamer.addRatingInGame(gamer, Games.WORLD_OF_TANKS);
        serviceGamer.addRatingInGame(gamer, Games.WORLD_OF_TANKS);
        serviceGamer.addRatingInGame(gamer, Games.WORLD_OF_TANKS);

        Assert.assertEquals(4, gamer.getRating());

        serviceGamer.addRatingInGame(gamer, Games.FOOTBALL_MANAGER);
        serviceGamer.addRatingInGame(gamer, Games.FOOTBALL_MANAGER);

        Assert.assertEquals(2, gamer.getRating());
    }

    @Test(expected = UserNotFoundException.class)
    public void addRatingNotFoundUserInGameTest() throws UserNotFoundException, UserAlreadyExistException {
        Set<Games> list = new HashSet<>();

        list.add(Games.WORLD_OF_TANKS);
        list.add(Games.FOOTBALL_MANAGER);

        Gamer gamer = new Gamer("Vova", list, 0);
        Gamer gamer1 = new Gamer("Alex", list, 0);

        ServiceGamer serviceGamer = new ServiceGamer();

        serviceGamer.checkInGamer(gamer);


        serviceGamer.addRatingInGame(gamer1, Games.WORLD_OF_TANKS);
    }


    @Test
    public void returnRatingInGame() throws UserNotFoundException, UserAlreadyExistException {
        Set<Games> list = new HashSet<>();

        list.add(Games.WORLD_OF_TANKS);
        list.add(Games.WORLD_OF_WAR_CRAFT);
        list.add(Games.FOOTBALL_MANAGER);

        Gamer gamer = new Gamer("Alex", list, 0);
        ServiceGamer serviceGamer = new ServiceGamer();

        serviceGamer.checkInGamer(gamer);

        serviceGamer.addRatingInGame(gamer, Games.WORLD_OF_TANKS);
        serviceGamer.addRatingInGame(gamer, Games.WORLD_OF_TANKS);
        serviceGamer.addRatingInGame(gamer, Games.FOOTBALL_MANAGER);

        Assert.assertEquals(2, serviceGamer.returnRatingInGame("Alex", Games.WORLD_OF_TANKS));
        Assert.assertEquals(1, serviceGamer.returnRatingInGame("Alex", Games.FOOTBALL_MANAGER));
    }

    @Test(expected = UserNotFoundException.class)
    public void returnRatingInGameNotFoundUser() throws UserNotFoundException, UserAlreadyExistException {
        Set<Games> list = new HashSet<>();

        list.add(Games.WORLD_OF_TANKS);
        list.add(Games.WORLD_OF_WAR_CRAFT);
        list.add(Games.FOOTBALL_MANAGER);

        Gamer gamer = new Gamer("Alex", list, 0);
        Gamer gamer1 = new Gamer("Julia", list, 0);
        Gamer gamer3 = new Gamer("Jon", list, 0);
        ServiceGamer serviceGamer = new ServiceGamer();

        serviceGamer.checkInGamer(gamer);

        serviceGamer.addRatingInGame(gamer, Games.WORLD_OF_TANKS);
        serviceGamer.addRatingInGame(gamer, Games.WORLD_OF_TANKS);
        serviceGamer.addRatingInGame(gamer, Games.FOOTBALL_MANAGER);

        Assert.assertEquals(2, serviceGamer.returnRatingInGame("Alex", Games.WORLD_OF_TANKS));
        Assert.assertEquals(1, serviceGamer.returnRatingInGame("Alex", Games.FOOTBALL_MANAGER));

        Assert.assertEquals(2, serviceGamer.returnRatingInGame("Jon", Games.WORLD_OF_TANKS));
        Assert.assertEquals(2, serviceGamer.returnRatingInGame("Julia", Games.WORLD_OF_TANKS));
    }

    @Test(expected = UserAlreadyExistException.class)
    public void returnRatingInGameDuplicateUser() throws UserNotFoundException, UserAlreadyExistException {
        Set<Games> list = new HashSet<>();

        list.add(Games.WORLD_OF_TANKS);
        list.add(Games.WORLD_OF_WAR_CRAFT);
        list.add(Games.FOOTBALL_MANAGER);

        Gamer gamer = new Gamer("Alex", list, 0);
        Gamer gamer1 = new Gamer("Alex", list, 0);

        ServiceGamer serviceGamer = new ServiceGamer();

        serviceGamer.checkInGamer(gamer);
        serviceGamer.checkInGamer(gamer1);

        Assert.assertEquals(0, serviceGamer.returnRatingInGame("Alex", Games.WORLD_OF_TANKS));
        Assert.assertEquals(0, serviceGamer.returnRatingInGame("Alex", Games.FOOTBALL_MANAGER));
    }
}
