package by.itstep.karnei.model;

import java.util.Arrays;

public class Gamer {
    public String nik;
    Games [] game;

    public Gamer() {
    }

    public Gamer(String nik, Games... game) {
        this.nik = nik;
        this.game = game;
    }

    @Override
    public String toString() {
        return "Gamer{" +
                "nik='" + nik + '\'' +
                ", game=" + Arrays.toString(game) +
                '}';
    }
}
