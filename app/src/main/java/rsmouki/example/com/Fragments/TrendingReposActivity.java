package rsmouki.example.com.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import rsmouki.example.com.Adapters.MyAdapter;
import rsmouki.example.com.Models.Repo;
import rsmouki.example.com.githubrepos.R;

public class TrendingReposActivity extends Fragment {

    private  static final String TAG="TrendingReposActivity";
    RecyclerView recyclerView;
    List<Repo> itemsData  = new ArrayList<>();


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

        itemsData = loadData();
        Log.d(TAG, itemsData.toString());

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

    private List<Repo> loadData() {
        List<Repo> repositories = null ;

        return repositories;
    }

}