package com;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class Parking {
    private ArrayList<Car> places = new ArrayList<>();
    private Map<String, ArrayList<ParkingInfo>> note = new HashMap<>();
    private ArrayList<Payment> cheques = new ArrayList<>();

    public Parking() {
        for (int i = 0; i < 20; i++) {
            places.add(null);
        }
    }

    public ArrayList<Car> getPlaces() {
        return places;
    }

    public void setPlaces(ArrayList<Car> places) {
        this.places = places;
    }

    public Map<String, ArrayList<ParkingInfo>> getNote() {
        return note;
    }

    public void setNote(Map<String, ArrayList<ParkingInfo>> note) {
        this.note = note;
    }

    public void printParkingStory(Set<Car> cars) {
        for (Car car : cars) {
            if (note.containsKey(car.getNumber())) {
                var info = note.get(car.getNumber());
                System.out.println("\n+---------------------------------------------------+\n" +
                        "|                   " + car.getNumber() + "                   |\n" +
                        "+---------------------------------------------------+");
                System.out.println("|  #  |      ENTER TIME      |        END TIME      |");
                System.out.println("+-----+----------------------+----------------------+");
                for (int i = 0; i < info.size(); i++) {
                    System.out.printf("| %3s |", new DecimalFormat("000").format(i + 1));
                    info.get(i).printInfo();
                }
            }
        }
    }

    public void printChecksForPayment() {
        DecimalFormat df = new DecimalFormat("00");
        System.out.println("===> FOR PAYMENT <===");
        System.out.println("+-----+---------------+---------+\n" +
                "|  #  |     NUMBER    |   PAY   |\n" +
                "+-----+---------------+---------+");
        for (int i = 0; i < cheques.size(); i++) {
            String format = String.format("%d.%s$", cheques.get(i).getForPay() / 100,
                    df.format(cheques.get(i).getForPay() % 100));
            System.out.printf("| %-3d | %-13s | %-7s |%n",
                    i + 1, cheques.get(i).getNumber(), format);
            System.out.println("+-----+---------------+---------+");
        }
    }

    public void setEndTime(Set<Car> cars, LocalDateTime endTime) {
        ArrayList<ParkingInfo> info = new ArrayList<>();

        for (Car car : cars) {
            if (note.containsKey(car.getNumber())) {
                info = note.get(car.getNumber());
                info.get(info.size() - 1).setEndTime(endTime);
            }
        }
    }

    public void countTotalPrices(Set<Car> cars) {
        ArrayList<ParkingInfo> timeInfo;
        for (Car car : cars) {
            if (note.containsKey(car.getNumber())) {
                timeInfo = note.get(car.getNumber());
                cheques.add(new Payment(car.getNumber(), countPriceForCar(timeInfo)));
            }
        }
    }

    private int countPriceForCar(ArrayList<ParkingInfo> timeInfo) {
        int payment = 0;
        for (ParkingInfo pi : timeInfo) {
            LocalDateTime start = pi.getEnterTime();
            LocalDateTime end = pi.getEndTime();

            payment += getPaidTime(start, end);
        }
        return payment;
    }

    private int getPaidTime(LocalDateTime start, LocalDateTime end) {
        LocalTime morning = LocalTime.of(9, 0);
        LocalTime night = LocalTime.of(21, 0);
        long minutes = 0;

        for (LocalDateTime i = start; i.isBefore(end); i = i.plusMinutes(5)) {
            if (i.toLocalTime().isAfter(morning) && i.toLocalTime().isBefore(night)) {
                minutes += 5;
            }
        }
        return (int) getPriceForDuration(minutes);
    }

    private int getPriceForDuration(long minutes) {
        if (minutes > 30)
            return (int) ((minutes - 30) / 5) * 10;
        else return 0;
    }
}
