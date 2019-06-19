package rsmouki.example.com.Models;

import android.graphics.Bitmap;

public class Repo {

    private String name;
    private String description;
    private String ownerName;
    private int nbrStars;
    private Bitmap ownerImage;

    public Repo(String name, String description, String ownerName, int nbrStars, Bitmap ownerImage) {
        this.name = name;
        this.description = description;
        this.ownerName = ownerName;
        this.nbrStars = nbrStars;
        this.ownerImage = ownerImage;
    }

    public  Repo(){

    }

    public Bitmap getOwnerImage() {
        return ownerImage;
    }

    public void setOwnerImage(Bitmap ownerImage) {
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
