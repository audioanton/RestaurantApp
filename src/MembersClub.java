import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Scanner;

public class MembersClub {
    //Singleton
    private static MembersClub instance;

    private List<Member> members;
    private Offer currentOffer;

    private MembersClub() {}

    public static MembersClub getInstance() {
        if (instance == null)
            instance = new MembersClub();
        return instance;
    }

    public void communicateToAllMembers(String message) {}

    public void createNewMember(String name, String phonenumber) {

        members.add(new Member(name, phonenumber));
    }

    public void createNewOffer() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the title of the offer:");
        String title = scanner.nextLine().trim();

        System.out.println("Enter the description of the offer:");
        String description = scanner.nextLine().trim();
//Kontrollerar om ett erbjudande redan finns
        if (currentOffer == null) {
            currentOffer = new Offer(title, description);
            System.out.println("New offer created!");
        } else {
            currentOffer.setTitle(title);
            currentOffer.setDescription(description);
            System.out.println("Offer updated!");
        }

        currentOffer.getDetails();

        // Spara erbjudandet till fil
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("offer.ser"))) {
            oos.writeObject(currentOffer);
            System.out.println("Offer saved to file!");
        } catch (IOException e) {
            System.out.println("Failed to save offer to file.");
            e.printStackTrace();
        }
    }}
