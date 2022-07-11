package com;

import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<Car> cars = new TreeSet<>();
        Parking parking = new Parking();

        initializeCars(cars);

        LocalDateTime ldt = LocalDateTime.now();

        for (LocalDateTime i = ldt; i.isBefore(ldt.plusDays(30)); i = i.plusMinutes(5)) {
            for (Car car : cars) {
                car.changeState(i, parking);
            }
            if (i.plusMinutes(5).isEqual(ldt.plusDays(30))) {
                cars.forEach(Car::setEndState);
                parking.setEndTime(cars, i);
            }
        }
        parking.countTotalPrices(cars);

        doAction(parking, cars);
    }

    private static void initializeCars(Set<Car> cars) {
        while (cars.size() != 200) {
            cars.add(new Car(Car.generateCarNumber(), State.ON_ROUTE));
        }
    }

    private static void doAction(Parking parking, Set<Car> cars) {
        while (true) {
            switch (askAction()) {
                case 1:
                    parking.printParkingStory(cars);
                    break;
                case 2:
                    parking.printChecksForPayment();
                    break;
                case 3:
                    return;
            }
        }
    }

    private static int askAction() {
        Scanner sc = new Scanner(System.in);
        System.out.println("""
                1 <- Print all parking stories
                2 <- Print all checks for payment
                3 <- Exit program""");
        System.out.print("Enter your choice: ");
        while (true) {
            try {
                String str = sc.nextLine().replaceAll("\\+s", "");
                return checkAction(str);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static int checkAction(String str) {
        if (str.equals(""))
            throw new RuntimeException("Choice can't be empty!");
        int choice = Integer.parseInt(str);
        if (choice < 1 || choice > 3)
            throw new RuntimeException("Can't find this action!");
        return choice;
    }
}