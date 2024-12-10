import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Database implements Serializable {
    List<Table> tables;
    List<TableBooking> bookings;

    public Database() {
        tables = new ArrayList<>();
        bookings = new ArrayList<>();

        tables.add(new Table(4, 1));
        tables.add(new Table(6, 2));
        bookings.add(new TableBooking(1, 3, "Viktor", "0702222222", "userName", LocalDate.now()));
    }

    public List<Table> getTables() {
        return tables;
    }

    public List<TableBooking> getBookings() {
        return bookings;
    }
}

