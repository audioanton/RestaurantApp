import java.time.LocalDate;
import java.util.List;

public class Restaurant {
    private List<Table> tables;
    private List<TableBooking> bookings;

    public boolean validateRestaurantOpen() {return false;}

    public void cancelBooking(int bookingId) {}

    public void bookTable() {}

    public boolean checkAvailableTable(int guests, LocalDate date) {return false;}

    public void runProgram() {
        while (true) {
            //if exit - saveAndExit()
            //setUserName()
            //loadData()
            //displayMenu()
            //get input - do things
            //display menu()
        }
    }
}
