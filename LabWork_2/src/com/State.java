package com;

import java.util.Random;

public enum State {
    ON_PARKING {
        @Override
        public void changeState(Car car) {
            int chance = new Random().nextInt(100) + 1;
            if (chance <= 3)
                car.setState(ON_ROUTE);
        }
    },
    ON_ROUTE {
        @Override
        public void changeState(Car car) {
            int chance = new Random().nextInt(100) + 1;
            if (chance <= 3)
                car.setState(ON_PARKING);
        }
    };

    public abstract void changeState(Car car);
}
