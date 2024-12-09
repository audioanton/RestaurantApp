import java.util.List;

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

    public void createNewOffer() {}
}
