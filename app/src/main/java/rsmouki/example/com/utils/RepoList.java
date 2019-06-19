package rsmouki.example.com.utils;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RepoList {

    @SerializedName("items")
    List<RepoDTO> gitRepo;

    public List<RepoDTO> getGitRepo() {
        return gitRepo;
    }

    public void setGitRepo(List<RepoDTO> gitRepo) {
        this.gitRepo = gitRepo;
    }
}
