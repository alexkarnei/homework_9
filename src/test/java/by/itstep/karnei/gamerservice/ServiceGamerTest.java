package by.itstep.karnei.gamerservice;

import by.itstep.karnei.gamerservice.exception.UserAlreadyExistException;
import by.itstep.karnei.gamerservice.exception.UserNotFoundException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ServiceGamerTest {

    @Test
    public void checkInGamerTest() throws UserAlreadyExistException {

        Set<Games> listGames = new HashSet<>
                (Arrays.asList(Games.WORLD_OF_TANKS,
                        Games.FOOTBALL_MANAGER,
                        Games.WORLD_OF_WARPLANES));

        ServiceGamer serviceGamer = new ServiceGamer();
        serviceGamer.checkInGamer(new Gamer("Alex", listGames));

        ServiceGamer serviceGamer1 = new ServiceGamer();
        serviceGamer1.checkInGamer(new Gamer("Alex", listGames, 0));
    }

    @Test(expected = UserAlreadyExistException.class)
    public void checkInSecondGamerTest() throws UserAlreadyExistException {

        Set<Games> list = new HashSet<>
                (Arrays.asList(Games.WORLD_OF_TANKS,
                        Games.WORLD_OF_WAR_CRAFT,
                        Games.FOOTBALL_MANAGER));

        Set<Games> list1 = new HashSet<>
                (Arrays.asList(Games.WORLD_OF_WAR_CRAFT,
                        Games.FOOTBALL_MANAGER,
                        Games.WORLD_OF_WARSHIP));

        ServiceGamer serviceGamer = new ServiceGamer();

        serviceGamer.checkInGamer(new Gamer("Alex", list));
        serviceGamer.checkInGamer(new Gamer("Vova", list1));
        serviceGamer.checkInGamer(new Gamer("Vova", list));
    }

    @Test
    public void addRatingInGamTest() throws UserNotFoundException, UserAlreadyExistException {

        Set<Games> list = new HashSet<>
                (Arrays.asList(Games.WORLD_OF_TANKS,
                        Games.FOOTBALL_MANAGER));

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
        Set<Games> list = new HashSet<>
                (Arrays.asList(Games.WORLD_OF_TANKS,
                        Games.FOOTBALL_MANAGER));

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

        Set<Games> list = new HashSet<>
                (Arrays.asList(Games.WORLD_OF_TANKS,
                        Games.FOOTBALL_MANAGER));

        ServiceGamer serviceGamer = new ServiceGamer();

        serviceGamer.checkInGamer(new Gamer("Vova", list, 0));


        serviceGamer.addRatingInGame(new Gamer("Alex", list, 0), Games.WORLD_OF_TANKS);
    }


    @Test
    public void returnRatingInGame() throws UserNotFoundException, UserAlreadyExistException {

        Set<Games> list = new HashSet<>
                (Arrays.asList(Games.WORLD_OF_TANKS,
                        Games.WORLD_OF_WAR_CRAFT, Games.FOOTBALL_MANAGER));

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

        Set<Games> list = new HashSet<>
                (Arrays.asList(Games.WORLD_OF_TANKS,
                        Games.WORLD_OF_WAR_CRAFT,
                        Games.FOOTBALL_MANAGER));

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

        Set<Games> list = new HashSet<>
                (Arrays.asList(Games.WORLD_OF_TANKS,
                        Games.WORLD_OF_WAR_CRAFT,
                        Games.FOOTBALL_MANAGER));

        ServiceGamer serviceGamer = new ServiceGamer();

        serviceGamer.checkInGamer(new Gamer("Alex", list, 0));
        serviceGamer.checkInGamer(new Gamer("Alex", list, 0));

        Assert.assertEquals(0, serviceGamer.returnRatingInGame("Alex", Games.WORLD_OF_TANKS));
        Assert.assertEquals(0, serviceGamer.returnRatingInGame("Alex", Games.FOOTBALL_MANAGER));
    }

    @Test
    public void returnSetGamesWhichPlayAllGamersTest() throws UserAlreadyExistException {

        Set<Games> list = new HashSet<>
                (Arrays.asList(Games.WORLD_OF_TANKS,
                        Games.WORLD_OF_WAR_CRAFT,
                        Games.FOOTBALL_MANAGER));

        Set<Games> list2 = new HashSet<>
                (Arrays.asList(Games.WORLD_OF_TANKS,
                        Games.WORLD_OF_WAR_CRAFT,
                        Games.WORLD_OF_WARSHIP,
                        Games.FOOTBALL_MANAGER));

        Set<Games> list3 = new HashSet<>
                (Arrays.asList(Games.WORLD_OF_WAR_CRAFT,
                        Games.FOOTBALL_MANAGER));

        ServiceGamer serviceGamer = new ServiceGamer();

        serviceGamer.checkInGamer(new Gamer("Alex", list));
        serviceGamer.checkInGamer(new Gamer("Bob", list2));
        serviceGamer.checkInGamer(new Gamer("Juli", list3));

        Set<Games> gamesGamer = serviceGamer.returnSetGamesWhichPlayAllGamers();

        Assert.assertEquals(2, gamesGamer.size());

        Assert.assertNotNull(gamesGamer);
    }

    @Test(expected = UserAlreadyExistException.class)
    public void returnSetGamesWhichPlayAllGamersDuplicateGamerTest() throws UserAlreadyExistException {

        Set<Games> list = new HashSet<>
                (Arrays.asList(Games.WORLD_OF_TANKS,
                        Games.WORLD_OF_WAR_CRAFT,
                        Games.FOOTBALL_MANAGER,
                        Games.SUPER_MARIO));

        Set<Games> list2 = new HashSet<>
                (Arrays.asList(Games.WORLD_OF_TANKS,
                        Games.WORLD_OF_WAR_CRAFT,
                        Games.WORLD_OF_WARSHIP,
                        Games.FOOTBALL_MANAGER,
                        Games.SUPER_MARIO));

        Set<Games> list3 = new HashSet<>
                (Arrays.asList(Games.WORLD_OF_WAR_CRAFT,
                        Games.FOOTBALL_MANAGER,
                        Games.SUPER_MARIO));

        ServiceGamer serviceGamer = new ServiceGamer();

        serviceGamer.checkInGamer(new Gamer("Alex", list));
        serviceGamer.checkInGamer(new Gamer("Bob", list2));
        serviceGamer.checkInGamer(new Gamer("Juli", list3));
        serviceGamer.checkInGamer(new Gamer("Alex", list2));
        serviceGamer.checkInGamer(new Gamer("Bob", list));

        Set<Games> gamesGamer = serviceGamer.returnSetGamesWhichPlayAllGamers();
        System.out.println(Arrays.toString(gamesGamer.toArray()));
        Assert.assertEquals(2, gamesGamer.size());

        Assert.assertNotNull(gamesGamer);
    }

    @Test
    public void getBestPlayersInGameTest() throws UserAlreadyExistException {

        Set<Games> list = new HashSet<>
                (Arrays.asList(Games.WORLD_OF_TANKS,
                        Games.FOOTBALL_MANAGER,
                        Games.SUPER_MARIO));

        ServiceGamer serviceGamer = new ServiceGamer();

        serviceGamer.checkInGamer(new Gamer("Alex", list, 2));
        serviceGamer.checkInGamer(new Gamer("Bob", list, 10));
        serviceGamer.checkInGamer(new Gamer("Juli", list, 8));
        serviceGamer.checkInGamer(new Gamer("Julia", list, 9));
        serviceGamer.checkInGamer(new Gamer("Bobby", list, 12));
        serviceGamer.checkInGamer(new Gamer("John", list, 15));
        serviceGamer.checkInGamer(new Gamer("Victor", list, 62));
        serviceGamer.checkInGamer(new Gamer("Gena", list, 4));
        serviceGamer.checkInGamer(new Gamer("Vitaliy", list, 3));
        serviceGamer.checkInGamer(new Gamer("Vova", list, 9));
        serviceGamer.checkInGamer(new Gamer("Vanya", list, 8));
        serviceGamer.checkInGamer(new Gamer("Gamer", list, 3));
        serviceGamer.checkInGamer(new Gamer("Gamer_198", list, 24));
        serviceGamer.checkInGamer(new Gamer("Vasya_2005", list, 14));

        serviceGamer.getBestGamersInGame(Games.WORLD_OF_TANKS);
    }

    @Test
    public void getBestPlayersInAllGamesTest() throws UserAlreadyExistException {

        Set<Games> list = new HashSet<>
                (Arrays.asList(Games.WORLD_OF_TANKS,
                        Games.FOOTBALL_MANAGER));

        ServiceGamer serviceGamer = new ServiceGamer();

        serviceGamer.checkInGamer(new Gamer("Alex", list, 2));
        serviceGamer.checkInGamer(new Gamer("Bob", list, 10));
        serviceGamer.checkInGamer(new Gamer("Juli", list, 8));
        serviceGamer.checkInGamer(new Gamer("Julia", list, 9));
        serviceGamer.checkInGamer(new Gamer("Bobby", list, 12));
        serviceGamer.checkInGamer(new Gamer("John", list, 15));
        Gamer victor = new Gamer("Victor", list, 62);
        serviceGamer.checkInGamer(victor);
        serviceGamer.checkInGamer(new Gamer("Gena", list, 4));
        serviceGamer.checkInGamer(new Gamer("Vitaliy", list, 3));
        serviceGamer.checkInGamer(new Gamer("Vova", list, 9));
        serviceGamer.checkInGamer(new Gamer("Vanya", list, 8));
        serviceGamer.checkInGamer(new Gamer("Gamer", list, 3));
        serviceGamer.checkInGamer(new Gamer("Gamer_198", list, 24));
        serviceGamer.checkInGamer(new Gamer("Vasya_2005", list, 14));


        Assert.assertEquals(124,victor.getRating()*list.size());
        serviceGamer.getBestGamersInAllGame();
    }
}
