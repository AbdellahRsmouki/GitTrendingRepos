package rsmouki.example.com.utils;

import com.google.gson.annotations.SerializedName;

public class JsonPageModel {


    @SerializedName("items")
    private RepoList items;

    public RepoList getItems() {
        return items;
    }

    public void setItems(RepoList items) {
        this.items = items;
    }
}
