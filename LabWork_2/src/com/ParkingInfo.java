package com;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ParkingInfo {
    private LocalDateTime enterTime;
    private LocalDateTime endTime;

    public ParkingInfo(LocalDateTime enterTime) {
        this.enterTime = enterTime;
    }

    public LocalDateTime getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(LocalDateTime enterTime) {
        this.enterTime = enterTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void printInfo() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd MMM, yyyy | HH:mm", new Locale("en"));
        String enter = String.valueOf(df.format(enterTime));
        String end;
        if (endTime != null)
            end = String.valueOf(df.format(endTime));
        else
            end = "NOT GONE YET";
        System.out.printf(" %-20s | %-20s |%n", enter, end);
        System.out.println("+-----+----------------------+----------------------+");
    }
}