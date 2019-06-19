package rsmouki.example.com.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
import rsmouki.example.com.utils.RepoList;
import rsmouki.example.com.utils.ServiceGenerator;

public class TrendingReposActivity extends Fragment {

    private static final String TAG="TrendingReposActivity";
    private RecyclerView recyclerView;
    private List<Repo> itemsData  = new ArrayList<>();


    public TrendingReposActivity() {
        // Required empty public constructor
    }

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

        loadData();
        Log.d(TAG, "DATA__:"+itemsData.toString());

        /**
         * you can always add some default audio files here
         */
        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // 3. create an adapter
        MyAdapter mAdapter = new MyAdapter(itemsData);
        // 4. set adapter
        recyclerView.setAdapter(mAdapter);
        // 5. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        return view;
    }

    void loadData() {
        downloadFirstPage();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // Actions to do after 10 seconds

            }
        }, 10000);
    }




    void downloadFirstPage(){
        ApiService downloadService = ServiceGenerator.createService(ApiService.class);
        final Call<JsonPageModel> call = downloadService.downloadFirstPage();
        Log.d(TAG, "call_is_been_done:" + call);
        call.enqueue(new Callback<JsonPageModel>() {
            @Override
            public void onResponse(Call<JsonPageModel> call, Response<JsonPageModel> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "server_contacted_and_has_files");
                    JsonPageModel body = response.body();
                    Log.d(TAG, response.body() +":::"+ body.getItems().getGitRepo());
                    List<RepoDTO> onePage = body.getItems().getGitRepo();
                    if(onePage == null){
                        Log.d(TAG, "no_files_from_the_server");
                        return;
                    }
                    for(RepoDTO i : onePage){
                        ImageView ownerAv = null;
                        ownerAv.setImageBitmap(getImageFromServer(i.getOwner().getAvatar_url()));
                        if(ownerAv == null){
                            ownerAv.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.github_logo));
                        }
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
            public void onFailure (Call call, Throwable t){
                if (t instanceof SocketTimeoutException) {
                    Log.d(TAG, "Socket_Time_out._Please_try_again.");
                }
            }

        });
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
                        ImageView ownerAv = null;
                        ownerAv.setImageBitmap(getImageFromServer(i.getOwner().getAvatar_url()));
                        if(ownerAv == null){
                            ownerAv.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.github_logo));
                        }
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