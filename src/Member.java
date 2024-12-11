public class Member {
    private String name, phoneNumber;

    public Member(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public void getDetails() {
        System.out.println(name);
        System.out.println(phoneNumber);}
}
