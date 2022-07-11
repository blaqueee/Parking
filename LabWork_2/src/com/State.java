package com;

import java.time.LocalDateTime;
import java.util.*;


public enum State {
    ON_PARKING {
        @Override
        public void changeState(Car car, Parking parking, LocalDateTime now) {
            int chance = new Random().nextInt(100) + 1;
            ArrayList<Car> places = parking.getPlaces();

            if (chance <= 3) {
                car.setState(ON_ROUTE);
                setEndTime(car, parking, now);
                for (int i = 0; i < places.size(); i++){
                    if (places.get(i) == car) {
                        places.set(i, null);
                        parking.setPlaces(places);
                        break;
                    }
                }
            }
        }
    },
    ON_ROUTE {
        @Override
        public void changeState(Car car, Parking parking, LocalDateTime now) {
            int chance = new Random().nextInt(100) + 1;
            ArrayList<Car> places = parking.getPlaces();

            if (chance <= 3) {
                for (int i = 0; i < places.size(); i++){
                    if (places.get(i) == null) {
                        car.setState(ON_PARKING);
                        enterData(car, parking, now);
                        places.set(i, car);
                        parking.setPlaces(places);
                        break;
                    }
                }
            }
        }
    };

    public abstract void changeState(Car car, Parking parking, LocalDateTime now);

    protected void setEndTime(Car car, Parking parking, LocalDateTime now) {
        var note = parking.getNote();
        var info = note.get(car.getNumber());

        var data = info.get(info.size() - 1);
        data.setEndTime(now);
        info.set(info.size() - 1, data);

        note.put(car.getNumber(), info);
        parking.setNote(note);
    }

    protected void enterData(Car car, Parking parking, LocalDateTime now) {
        Map<String, ArrayList<ParkingInfo>> note = parking.getNote();
        ArrayList<ParkingInfo> info;

        if (note.get(car.getNumber()) != null)
            info = note.get(car.getNumber());
        else
            info = new ArrayList<>();

        info.add(new ParkingInfo(now));

        note.put(car.getNumber(), info);
        parking.setNote(note);
    }
}