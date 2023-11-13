package com.web;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Test22 {
    public static void main(String[] args) {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(10);
        calculateDaysBetweenDates(startDate, endDate);
    }

    public static void calculateDaysBetweenDates(LocalDate startDate, LocalDate endDate) {
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        System.out.println("Days between " + startDate + " and " + endDate + " = " + daysBetween);
    }
}
