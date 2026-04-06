package com.api.utils;

import com.api.models.Booking;
import com.api.models.BookingDates;
import java.time.LocalDate;
import java.util.Random;

public class DataGenerator {
    private static Random random = new Random();

    public static Booking generateRandomBooking() {
        int randomNum = random.nextInt(10000);

        BookingDates dates = new BookingDates();
        dates.setCheckin(LocalDate.now().toString());
        dates.setCheckout(LocalDate.now().plusDays(7).toString());

        Booking booking = new Booking();
        booking.setFirstname("TestUser" + randomNum);
        booking.setLastname("Smith" + randomNum);
        booking.setTotalprice(random.nextInt(500) + 50);
        booking.setDepositpaid(random.nextBoolean());
        booking.setBookingdates(dates);
        booking.setAdditionalneeds(getRandomNeed());

        return booking;
    }

    private static String getRandomNeed(){
        String[] needs = {"Breakfast", "Lunch", "Dinner", "Parking"};
        return needs[random.nextInt(needs.length)];
    }
}
