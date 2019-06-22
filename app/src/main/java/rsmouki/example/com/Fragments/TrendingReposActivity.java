package rsmouki.example.com.Fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rsmouki.example.com.Adapters.MyAdapter;
import rsmouki.example.com.Models.Repo;
import rsmouki.example.com.githubrepos.R;
import rsmouki.example.com.utils.ApiService;
import rsmouki.example.com.utils.JsonPageModel;
import rsmouki.example.com.utils.RepoDTO;
import rsmouki.example.com.utils.ServiceGenerator;

public class TrendingReposActivity extends Fragment {

    private static final String TAG="TrendingReposActivity";
    private RecyclerView recyclerView;

    MyAdapter mAdapter;

    private List<Repo> itemsData  = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trending_repos, container, false);

        Log.d(TAG, " inside_oncreateView");


        // 1. get a reference to recyclerView
        recyclerView =  view.findViewById(R.id.my_recycler_view);
        // this is data fr  o recycler view
        /**
         * you need to add audio files here.
         */
        /*Repo r;
        Bitmap abatar = BitmapFactory.decodeResource(view.getResources(),
                R.drawable.github_logo);
        r = new Repo("GithubREPO1","short decriptoin","abdellah",
                1000, abatar);
        itemsData.add(r);
        r = new Repo("GithubREPO2","long decriptoin","rsmouki",
                1200, abatar);
        itemsData.add(r);
        r = new Repo("GithubREPO3","medium decriptoin","hassan",
                1500, abatar);
        itemsData.add(r);*/

        new DownloadReposDataTask(itemsData).execute();

        Log.d(TAG, "loadData");


        /**
         * you can always add some default audio files here
         */
        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // 3. create an adapter
        mAdapter = new MyAdapter(itemsData);
        // 4. set adapter
        recyclerView.setAdapter(mAdapter);
        // 5. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        return view;
    }

    private class DownloadReposDataTask extends AsyncTask<Void, Void,List<Repo> > {

        List<Repo> caller;

        public DownloadReposDataTask(List<Repo> caller) {
            this.caller = caller;
        }

        @Override
        protected void onPostExecute(List<Repo> itList) {
            //progressBar.setVisibility(View.GONE);     // To Hide ProgressBar
            onBackgroundTaskCompleted(itList);
            Log.d(TAG, "__DATA__Post:"+itList.toString());
        }

        @Override
        protected List<Repo> doInBackground(Void... voids) {
            // TODO: Implement your own logic here.
            List<Repo> items = new ArrayList<Repo>();
            items = downloadFirstPage();
            Log.d(TAG, "__DATA__Back:"+items.toString());
            return items;
        }
    }

    public void onBackgroundTaskCompleted(List<Repo> r){
        itemsData = r;
        mAdapter.notifyDataSetChanged();

    }

    List<Repo> downloadFirstPage() {
        List<Repo> itDATA = new ArrayList<Repo>();
        ApiService downloadService = ServiceGenerator.createService(ApiService.class);
        final Call<ResponseBody> call = downloadService.downloadFirstPage();
        Log.d(TAG, "call_is_been_done:" + call);
        Response<ResponseBody> response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response.isSuccessful()) {
            try {
                //get the json array
                JSONArray jsonArray = new JSONArray(response.body().string());
                Log.d(TAG, "JSON_DATA_ARRAY_:" + jsonArray);
                if (jsonArray != null) {
                    Repo r;
                    Bitmap ownerAv = null;
                    //iterate your json array
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        Log.d(TAG, "REPO_DATA__:" + object);
                        ownerAv = getImageFromServer(object.getJSONObject("owner").getString("avatar_url"));
                        if (ownerAv == null) {
                            ownerAv = BitmapFactory.decodeResource(getResources(),
                                    R.drawable.github_logo);
                        }
                        if (checkstargazers_countIfExist(object)) {
                            r = new Repo(
                                    object.getString("name"),
                                    object.getString("description"),
                                    object.getJSONObject("owner").getString("login"),
                                    object.getInt("stargazers_count"),
                                    ownerAv
                            );
                            itDATA.add(r);
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                Log.d(TAG, "Exception_while_fetching_JSONFile");
            }
        } else {
            Log.d(TAG, "server_contact_failed");
        }
        return itDATA;
    }

    private boolean checkstargazers_countIfExist(JSONObject obj) {

        if (obj.has("stargazers_count")) {
            return true;
        }
        return false;

    }

    void downloadOtherPages(int id){
        ApiService downloadService = ServiceGenerator.createService(ApiService.class);
        final Call<JsonPageModel> call = downloadService.downloadFileWithPageNumber(id);
        Log.d(TAG, "call is been done:" + call);
        call.enqueue(new Callback<JsonPageModel>() {
            @Override
            public void onResponse(Call<JsonPageModel> call, Response<JsonPageModel> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "server_contacted_and_has_files");
                    JsonPageModel body = response.body();
                    Log.d(TAG, response.body() +":::"+ body.getItems().getGitRepo());
                    List<RepoDTO> onePage = body.getItems().getGitRepo();
                    if(onePage == null){
                        Log.d(TAG, "no files from the server");
                        return;
                    }
                    for(RepoDTO i : onePage){
                        Bitmap ownerAv = null;
                        ownerAv = getImageFromServer(i.getOwner().getAvatar_url()); //setImageBitmap(
                        /*if(ownerAv == null){
                            ownerAv.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.github_logo));
                        }*/
                        Repo r = new Repo(i.getName(),i.getDescription(),i.getOwner().getLogin(),i.getStargazers_count(), ownerAv);
                        //AudioFile j = new AudioFile(i.getId(),i.getaName(),i.getAtype(),i.getAlangue());
                        // Log.d(TAG, "file from server : "+ j.toString());
                        itemsData.add(r);
                    }
                } else {
                    Log.d(TAG, "server_contact_failed");
                }
            }

            @Override
            public void onFailure(Call<JsonPageModel> call, Throwable t) {
                Log.e(TAG, "error_tata : "+t);
            }
        });
    }

    private Bitmap getImageFromServer(String avatar_url) {


        Bitmap bitmap = null;
        try {
            URL imageURL = new URL(avatar_url);
            bitmap = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }

}