package rsmouki.example.com.utils;

import com.google.gson.annotations.SerializedName;

public class JsonPageModel {


    @SerializedName("items")
    private RepoList items;
    @SerializedName("total_count")
    private int total_count;
    @SerializedName("incomplete_results")
    private boolean incomplete_results;

    public RepoList getItems() {
        return items;
    }

    public void setItems(RepoList items) {
        this.items = items;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public boolean isIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }
}