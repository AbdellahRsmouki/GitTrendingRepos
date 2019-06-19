package rsmouki.example.com.Adapters;


import android.view.LayoutInflater;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import rsmouki.example.com.Models.Repo;
import rsmouki.example.com.githubrepos.R;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Repo> _itemsData;

    public MyAdapter(List<Repo> _itemsData) {
        this._itemsData = _itemsData;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repos_item_row, null);

        // create ViewHolder
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        // - get data from your itemsData at this position
        // - replace the contents of the view with that itemsData

        viewHolder.txtViewTitle.setText(_itemsData.get(position).getName());
        viewHolder.txtViewDesc.setText(_itemsData.get(position).getDescription());
        viewHolder.txtViewOwnerName.setText(_itemsData.get(position).getOwnerName());
        viewHolder.txtViewNbrStars.setText(Integer.toString((_itemsData.get(position).getNbrStars())));

    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtViewTitle;
        public TextView txtViewDesc;
        public TextView txtViewOwnerName;
        public TextView txtViewNbrStars;
        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txtViewTitle = itemLayoutView.findViewById(R.id.repo_name);
            txtViewDesc = itemLayoutView.findViewById(R.id.repo_desc);
            txtViewOwnerName = itemLayoutView.findViewById(R.id.repo_owner_name);
            txtViewNbrStars =  itemLayoutView.findViewById(R.id.number_of_stars);
        }
    }

    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return _itemsData.size();
    }

}


