import java.io.Serializable;

public class Offer  implements Serializable {
    private String title, description;

    public Offer(String title, String description) {
        this.title = title;
        this.description = description;
    }


    public String getDetails() {return null;}

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
