import java.io.Serializable;
import java.util.ArrayList;
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
        membersClub = new MembersClub();
    }

    public int getNextBookingId() {
        return ++sumBookings;
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

