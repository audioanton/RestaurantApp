import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Restaurant {
    private Database database;
    private String userName;
    private Scanner scanner;

    public Restaurant() {
        scanner = new Scanner (System.in);
        database = new Database();
    }

    public void validateBookingDate(LocalDate date) {
        if (date.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Selected date has already passed");
        else if (date.getDayOfWeek().name().equals("MONDAY"))
            throw new IllegalArgumentException("The restaurant is closed Mondays.");
        else if (ChronoUnit.DAYS.between(LocalDate.now(), date) > 30)
            throw new IllegalArgumentException("A table can be booked maximum 30 days in advance.");
        }

    public void cancelBooking() {
        System.out.println("Please enter bookingId");
        int bookingId = scanner.nextInt();
        scanner.nextLine();
        Collections.sort(database.getBookings());
        int index = Collections.binarySearch(database.getBookings(), new TableBooking(bookingId), Comparator.comparing(TableBooking::getBookingId));
        if (index >= 0) {
            database.getBookings().remove(index);
            System.out.println("Booking " + bookingId + " cancelled.");
        }
        else
            System.out.println("Booking id " + bookingId + " not found.");
    }

    public void bookTable() {
        System.out.println(database.getBookings().size());
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

        database.getBookings().add(new TableBooking(tableId, guests, firstName, phoneNumber, userName, date, database.getNextBookingId()));
        System.out.println("Table booked.");
    }

    public int checkTableAvailability(int guests, LocalDate date) {
        System.out.println(database.getTables().size());
        for (Table table : database.getTables()) {
            if (table.getChairs() < guests)
                continue;
            boolean available = true;
            for (TableBooking booking : database.getBookings()) {
                if (booking.getDate().equals(date) && booking.getTableId() == table.getTableID()) {
                    available = false;
                    break;
                }
            }
            if (available)
                return table.getTableID();
        }
        return -1;
    }

    public void initTables() {
        if(database.getTables().isEmpty()) {
            TableFactory factory = new TableFactory();
            List<Table> tables = new ArrayList<>();

            tables.add(factory.getTable(1));
            tables.add(factory.getTable(1));
            tables.add(factory.getTable(1));

            tables.add(factory.getTable(2));
            tables.add(factory.getTable(2));
            tables.add(factory.getTable(2));

            tables.add(factory.getTable(3));
            tables.add(factory.getTable(3));
            tables.add(factory.getTable(3));

            Collections.sort(tables);
            database.getTables().addAll(tables);
        }
    }

    public void runProgram() {
        loadData();
        initTables();
        setUserName();
        while (true) {
            System.out.println();
            displayMenu();
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1" -> {
                    bookTable();
                }
                case "2" -> database.getMembersClub().createNewOffer();
                case "3" -> {
                    database.getMembersClub().printOfferDetails();
                }
                case "4" -> {
                    addMember();
                }
                case "5" -> {
                    getBookingOverview();
                }
                case "6" -> {
                    cancelBooking();
                }
                case "7" -> {
                    saveAndExit();
                }
            }
            System.out.print("\nPress enter to return to the main menu.");
            scanner.nextLine();
        }
    }

    private void displayMenu() {
        System.out.println("---Restaurant manager© main menu---");
        System.out.println("Please enter your choice below:");
        System.out.println("1: Book table");
        System.out.println("2: Create or update an offer");
        System.out.println("3: Show offer details");
        System.out.println("4: Add new member to the members club");
        System.out.println("5: Show bookings");
        System.out.println("6: Cancel booking");
        System.out.println("7: Save and exit program");
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

    public void addMember()
    {
        String memberName = "";
        String memberPhoneNumber="";
        while (memberName.isEmpty() || memberPhoneNumber.isEmpty()) {
            if (memberName.isEmpty()) {
                System.out.println("Enter member name: ");
                memberName = scanner.nextLine().trim();
            }else
            {
                System.out.println("Enter member phone number: ");
                memberPhoneNumber = scanner.nextLine().trim();
            }
        }
        database.getMembersClub().createNewMember(memberName,memberPhoneNumber);
    }

    public void getBookingOverview() {
        System.out.println("For what period?");
        System.out.println("1: Today");
        System.out.println("2: Next 7 days");
        System.out.println("3: Next 30 days");
        int userInput = scanner.nextInt();
        scanner.nextLine();

        if (userInput == 1) {
            System.out.println(dateFormatter(LocalDate.now()));
            printBookingsThisDate(LocalDate.now());
        } else if (userInput == 2) {
            for (int i = 0; i < 7; i++) {
                LocalDate requestedDate = LocalDate.now().plusDays(i);
                if (!requestedDate.getDayOfWeek().name().equals("MONDAY")) {
                    System.out.println(dateFormatter(requestedDate));
                    printBookingsThisDate(requestedDate);
                    System.out.println("————————————————————————————————————————————");
                }
            }
        } else if (userInput == 3) {
            for (int i = 0; i < 30; i++) {
                LocalDate requestedDate = LocalDate.now().plusDays(i);
                if (!requestedDate.getDayOfWeek().name().equals("MONDAY")) {
                    System.out.println(dateFormatter(requestedDate));
                    printBookingsThisDate(requestedDate);
                    System.out.println("————————————————————————————————————————————");
                }
            }
        }
    }

    public void printBookingsThisDate(LocalDate requestedDate) {
        for (Table table : database.getTables()) {
            boolean tableHasBooking = false;

            for (TableBooking booking : database.getBookings()) {
                if (booking.getTableId() == table.getTableID() &&
                        booking.getDate().equals(requestedDate)) {
                    System.out.println(booking.printBooking());
                    tableHasBooking = true;
                    break;
                }
            }
            if (!tableHasBooking) {
                System.out.println("Table " + table.getTableID() + ":");
            }
        }
    }

    public String dateFormatter(LocalDate inputDate) {
        return inputDate.format(DateTimeFormatter.ofPattern("EEEE, MMMM d"));
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
