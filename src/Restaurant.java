import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Restaurant {
    private Database database;
    private String userName;
    private Scanner scanner;

    public Restaurant() {
        scanner = new Scanner (System.in);
        database = new Database();
        database.initTables();
    }

    public void validateBookingDate(LocalDate date) {
        if (date.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Selected date has already passed");
        else if (date.getDayOfWeek().name().equals("MONDAY"))
            throw new IllegalArgumentException("The restaurant is closed Mondays.");
        else if (ChronoUnit.DAYS.between(LocalDate.now(), date) > 30)
            throw new IllegalArgumentException("A table can be booked maximum 30 days in advance.");
        }

    public void cancelBooking(int bookingId) {}

    public void bookTable() {
        LocalDate date;
        int guests;
        int tableId;

            while (true) {
                try {
                    System.out.println("Which date do you want to book? (YYYY-MM-DD)");
                    String inputDate = scanner.nextLine();
                    date = LocalDate.parse(inputDate);
                    validateBookingDate(date);
                    System.out.println("For how many guests?");
                    guests = scanner.nextInt();
                    scanner.nextLine();
                    tableId = checkTableAvailability(guests, date);
                    if (tableId > -1)
                        break;
                    else
                        System.out.println("No table available for " + guests + " people that day.");
                } catch (InputMismatchException e) {
                    System.out.println("Error in input, try again.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                } catch (DateTimeException e) {
                    System.out.println("Faulty date, try again.");
                }
            }

        System.out.println("Table available. Enter first name of guest:");
        String firstName = scanner.nextLine().trim();
        System.out.println("Enter phone number of guest:");
        String phoneNumber = scanner.nextLine().trim();

        database.getBookings().add(new TableBooking(tableId, guests, firstName, phoneNumber, userName, date));
        System.out.println("Table booked.");
    }

    public int checkTableAvailability(int guests, LocalDate date) {
        for (Table table : database.getTables()) {
            if (table.chairs() < guests)
                continue;
            boolean available = true;
            for (TableBooking booking : database.getBookings()) {
                if (booking.getDate().equals(date) && booking.getTableId() == table.tableId()) {
                    available = false;
                    break;
                }
            }
            if (available)
                return table.tableId();
        }
        return -1;
    }

    public void runProgram() {
        loadData();
        setUserName();
        while (true) {
            displayMenu();
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1" -> {
                    bookTable();
                }
                case "2" -> {
                    saveAndExit();
                }
            }
        }
    }

    private void displayMenu() {
        System.out.println("Welcome to the tablebooker©!");
        System.out.println("Please enter your choice below:");
        System.out.println("1: Book table");
        System.out.println("2: Save and exit program");
    }

    private void loadData() {
        File temp = new File("restaurant.ser");
        if (temp.exists()) {
            try (ObjectInputStream inputData = new ObjectInputStream(new FileInputStream(temp))) {
                Object object = inputData.readObject();
                if (object instanceof Database) {
                    database = (Database) object;
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void setUserName() {
        System.out.println("Enter your name:");
        userName = scanner.nextLine().trim();
        while (userName.equals("")) {
            System.out.println("Please enter your name:");
            userName = scanner.nextLine().trim();
        }
    }

    public void saveAndExit() {
        try (ObjectOutputStream outputToFile = new ObjectOutputStream(new FileOutputStream("restaurant.ser"))) {
            outputToFile.writeObject(database);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
