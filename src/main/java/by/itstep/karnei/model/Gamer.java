package by.itstep.karnei.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Gamer {
    private String nik;
    private ArrayList<Games> game;

    public Gamer() {
    }

    public Gamer(String nik, ArrayList<Games> game) {
        this.nik = nik;
        this.game = game;
    }

    @Override
    public String toString() {
        return "Gamer{" +
                "nik='" + nik + '\'' +
                ", game=" + game +
                '}' + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gamer)) return false;
        Gamer gamer = (Gamer) o;
        return getNik().equals(gamer.getNik());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNik());
    }

    public String getNik() {
        return nik;
    }

    public ArrayList<Games> getGameAll() {
        return game;
    }
}
