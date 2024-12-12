import java.io.Serializable;
import java.time.LocalDate;

public class TableBooking implements Serializable, Comparable<TableBooking> {
    private int tableId, guests, bookingId;
    private String guestName, guestPhoneNumber, bookedBy;
    private LocalDate date;

    public TableBooking(int bookingId) {
        this.bookingId = bookingId;
    }

    public TableBooking(int tableId,
                        int guests,
                        String guestName,
                        String guestPhoneNumber,
                        String bookedBy,
                        LocalDate date,
                        int bookingId) {
        this.tableId = tableId;
        this.guests = guests;
        this.guestName = guestName;
        this.guestPhoneNumber = guestPhoneNumber;
        this.bookedBy = bookedBy;
        this.date = date;
        this.bookingId = bookingId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public int getTableId() {
        return tableId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void getDetails() {
        System.out.println(guestName);
        System.out.println(guestPhoneNumber);
        System.out.println(bookedBy);
    }

    public String printBooking() {
        return "Table " + tableId + ": " + "\t" + guestName + ", " + guests + " guests, " + "phone: " + guestPhoneNumber;
    }

    @Override
    public int compareTo(TableBooking o) {
        if (this.getTableId() < o.getTableId()) {
            return -1;
        } else
            return 1;
    }
}
