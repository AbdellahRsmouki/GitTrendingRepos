package rsmouki.example.com.utils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {


    @GET("/repositories?q=created:>2017-10-22&sort=stars&order=desc")
    Call<ResponseBody> downloadFirstPage();

    @GET("/repositories?q=created:>2017-10-22&sort=stars&order=desc&page={id}")
    Call<JsonPageModel> downloadFileWithPageNumber(@Path("id") int id);

}