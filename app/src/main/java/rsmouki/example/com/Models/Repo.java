package rsmouki.example.com.Models;

import android.widget.ImageView;

public class Repo {

    private String name;
    private String description;
    private String ownerName;
    private int nbrStars;
    private ImageView ownerImage;

    public Repo(String name, String description, String ownerName, int nbrStars, ImageView ownerImage) {
        this.name = name;
        this.description = description;
        this.ownerName = ownerName;
        this.nbrStars = nbrStars;
        this.ownerImage = ownerImage;
    }

    public  Repo(){

    }

    public ImageView getOwnerImage() {
        return ownerImage;
    }

    public void setOwnerImage(ImageView ownerImage) {
        this.ownerImage = ownerImage;
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

    public int getNbrStars() {
        return nbrStars;
    }

    public void setNbrStars(int nbrStars) {
        this.nbrStars = nbrStars;
    }
}
