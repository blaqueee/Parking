package com;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.*;

public class Car implements Comparable<Car> {
    private String number;
    private State state;

    public Car(String number, State state) {
        this.number = number;
        this.state = state;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Car that = (Car) obj;

        return Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public int compareTo(Car o) {
        return number.compareTo(o.number);
    }

    @Override
    public String toString() {
        return "Number: " + number + " -> Place: " + state;
    }

    public void changeState(LocalDateTime now, Parking parking) {
        state.changeState(this, parking, now);
    }

    public void setEndState() {
        state = State.ON_ROUTE;
    }

    public static String generateCarNumber() {
        StringBuilder sb = new StringBuilder();

        sb.append(generateNumberOfCity()).append("KG_");
        sb.append(generateNumber());
        sb.append(generateLetters());

        return String.valueOf(sb);
    }

    private static String generateNumberOfCity() {
        DecimalFormat df = new DecimalFormat("00");
        int number = new Random().nextInt(9) + 1;

        return df.format(number) + "_";
    }

    private static String generateNumber() {
        DecimalFormat df = new DecimalFormat("000");
        int number = new Random().nextInt(1000);

        return df.format(number) + "_";
    }

    private static String generateLetters() {
        StringBuilder sb = new StringBuilder();
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < 3; i++) {
            sb.append(letters.charAt(new Random().nextInt(letters.length())));
        }

        return String.valueOf(sb);
    }
}