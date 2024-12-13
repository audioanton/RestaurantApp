import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MembersClub implements Serializable{
    //Singleton
    private static MembersClub instance;

    private List<Member> members;
    private Offer currentOffer;

    public MembersClub() {
        //if (members == null) {
            //members = new ArrayList<>();
        //}
    }

    public MembersClub readResolve() {
        return instance;
    }

    public static MembersClub getInstance() {
        if (instance == null)
            instance = new MembersClub();
        return instance;
    }

    public void communicateToAllMembers(String message) {}

    public void createNewMember(String name, String phonenumber) {
        if (members == null) {
            members = new ArrayList<>();
        }
        members.add(new Member(name, phonenumber));
    }

    public void createNewOffer() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the title of the offer:");
        String title = scanner.nextLine().trim();

        System.out.println("Enter the description of the offer:");
        String description = scanner.nextLine().trim();
        if (currentOffer == null) {
            currentOffer = new Offer(title, description);
            System.out.println("New offer created!");
        } else {
            currentOffer.setTitle(title);
            currentOffer.setDescription(description);
            System.out.println("Offer updated!");
        }
    }

    public void printOfferDetails() {
        if (currentOffer != null) {
            currentOffer.getDetails();
        }
        System.out.println("There is no current offer");
    }

    public List<Member> getMembers()
    {
        return members;
    }
}
