import javax.xml.crypto.Data;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

    public void bookTable() {}

    public boolean checkAvailableTable(int guests, LocalDate date) {return false;}

    public void runProgram() {
        loadData();
        setUserName();
        while (true) {
            saveAndExit();


            //loadData()
            //displayMenu()
            //get input - do things
            //display menu()
        }
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
