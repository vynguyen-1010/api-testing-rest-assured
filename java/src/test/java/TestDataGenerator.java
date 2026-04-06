import com.api.models.Booking;
import com.api.utils.DataGenerator;

public class TestDataGenerator {
    public static void main(String[] args) {
        Booking booking = DataGenerator.generateRandomBooking();

        System.out.println("Firstname: " + booking.getFirstname());
        System.out.println("Lastname: " + booking.getLastname());
        System.out.println("Totalprice: " + booking.getTotalprice());
        System.out.println("Checkin: " + booking.getBookingdates().getCheckin());
        System.out.println("Checkout: " + booking.getBookingdates().getCheckout());
    }
}