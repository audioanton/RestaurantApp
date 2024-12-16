import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MembersClub implements Serializable{

    private List<Member> members;
    private Offer currentOffer;

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
        } else {
            System.out.println("There is no current offer");
        }
    }
}
