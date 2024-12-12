import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Database implements Serializable {
    private int sumBookings;
    private List<Table> tables;
    private List<TableBooking> bookings;
    private MembersClub membersClub;

    public Database() {
        sumBookings = 0;
        tables = new ArrayList<>();
        bookings = new ArrayList<>();
        initTables();
        membersClub = MembersClub.getInstance();
    }

    public int getNextBookingId() {
        return ++sumBookings;
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

        Collections.sort(tables);
    }

    public List<Table> getTables() {
        return tables;
    }

    public List<TableBooking> getBookings() {
        return bookings;
    }

    public MembersClub getMembersClub()
    {
        return membersClub;
    }
}

