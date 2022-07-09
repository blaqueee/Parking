package com;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Set<Car> cars = new TreeSet<>();

        while (cars.size() != 200){
            cars.add(new Car(Car.generateCarNumber(), State.ON_ROUTE));
        }

        cars.forEach(Car::changeState);
        cars.forEach(System.out::println);

        System.out.println(cars.size());
    }
}