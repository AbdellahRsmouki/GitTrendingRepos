package rsmouki.example.com.Models;

public class Repo {

    private String name;
    private String description;
    private String ownerName;
    private String nbrStars;

    public Repo(String name, String description, String ownerName, String nbrStars) {
        this.name = name;
        this.description = description;
        this.ownerName = ownerName;
        this.nbrStars = nbrStars;
    }

    public  Repo(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getNbrStars() {
        return nbrStars;
    }

    public void setNbrStars(String nbrStars) {
        this.nbrStars = nbrStars;
    }
}
