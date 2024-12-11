import java.io.*;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Restaurant {
    private Database database;
    private String userName;
    private Scanner scanner;

    public Restaurant() {
        scanner = new Scanner (System.in);
        database = new Database();
    }

    public boolean validateRestaurantOpen() {return false;}

    public void cancelBooking(int bookingId) {}

    public void bookTable() {
        LocalDate date;
        int guests;

            while (true) {
                try {
                    System.out.println("Which date do you want to book? (YYYY-MM-DD)");
                    String inputDate = scanner.nextLine();
                    date = LocalDate.parse(inputDate);
                    System.out.println("For how many guests?");
                    guests = scanner.nextInt();
                    scanner.nextLine();
                    if (checkTableAvailability(guests, date)) {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error in input, try again.");
                }
                System.out.println("Date not available. Try another date (YYYY-MM-DD)");
            }

        System.out.println("Table available. Enter first name of guest:");
        String firstName = scanner.nextLine().trim();
        System.out.println("Enter phone number of guest:");
        String phoneNumber = scanner.nextLine().trim();

        database.getBookings().add(new TableBooking(1, guests, firstName, phoneNumber, userName, date));
        System.out.println("Table booked.");
    }

    public boolean checkTableAvailability(int guests, LocalDate date) {
        return true;
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
                case "2" -> MembersClub.getInstance().createNewOffer();
                case "3" -> {
                    saveAndExit();
                }
            }
            //get input - do things
            //display menu()
        }
    }

    private void displayMenu() {
        System.out.println("Welcome to the tablebooker©!");
        System.out.println("Please enter your choice below:");
        System.out.println("1: Book table");
        System.out.println("2: Create or update an offer");
        System.out.println("3: Save and exit program");
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
        System.out.println("Enter your name");
        userName = scanner.nextLine().trim();
        while (userName.equals("")) {
            System.out.println("Please enter your name");
            userName = scanner.nextLine().trim();
        }
        System.out.println(userName);
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
