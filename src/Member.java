import java.io.Serializable;

public class Member implements Serializable {
    private String name, phoneNumber;

    public Member(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }


}
