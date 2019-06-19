package rsmouki.example.com.utils;

import com.google.gson.annotations.SerializedName;

public class RepoDTO {

    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("owner")
    private OwnerModel owner;
    @SerializedName("stargazers_count")
    private int stargazers_count;


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

    public OwnerModel getOwner() {
        return owner;
    }

    public void setOwner(OwnerModel owner) {
        this.owner = owner;
    }

    public int getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(int stargazers_count) {
        this.stargazers_count = stargazers_count;
    }
}
