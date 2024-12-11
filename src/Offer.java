public class Offer {
    private String title, description;

    public Offer(String title, String description) {
        this.title = title;
        this.description = description;
    }


    public void getDetails() {
            System.out.println(title + "\n" + description);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
