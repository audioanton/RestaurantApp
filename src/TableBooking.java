import java.io.Serializable;
import java.time.LocalDate;

public class TableBooking implements Serializable {
    private int tableId, guests;
    private String guestName, guestPhoneNumber, bookedBy;
    private LocalDate date;

    public TableBooking(int tableId) {
        this.tableId = tableId;
    }

    public TableBooking(int tableId,
                        int guests,
                        String guestName,
                        String guestPhoneNumber,
                        String bookedBy,
                        LocalDate date) {
        this.tableId = tableId;
        this.guests = guests;
        this.guestName = guestName;
        this.guestPhoneNumber = guestPhoneNumber;
        this.bookedBy = bookedBy;
        this.date = date;
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
        return "Table " + tableId + ": " + "\t" + guestName + ", " + guests + " guests, " + guestPhoneNumber;
    }
}
