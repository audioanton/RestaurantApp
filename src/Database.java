import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Database implements Serializable {
    private List<Table> tables;
    private List<TableBooking> bookings;

    public Database() {
        tables = new ArrayList<>();
        bookings = new ArrayList<>();
        initTables();
    }

    public void initTables() {
        tables = new ArrayList<>();
        int id = 0;
        tables.add(new Table(4, id++));
        tables.add(new Table(4, id++));
        tables.add(new Table(4, id++));

        tables.add(new Table(6, id++));
        tables.add(new Table(6, id++));

        tables.add(new Table(10, id++));

        tables.add(new Table(2, id++));
        tables.add(new Table(2, id++));
        tables.add(new Table(2, id++));
    }

    public List<Table> getTables() {
        return tables;
    }

    public List<TableBooking> getBookings() {
        return bookings;
    }
}

