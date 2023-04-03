package com.magdaproject.myapplication.helpers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.magdaproject.myapplication.R;
import com.magdaproject.myapplication.models.Player;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.ViewHolder> {

    private List<Player> localDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView userName;
        private final TextView password;
        private final TextView name;
        private final TextView email;

        public ViewHolder(View view) {
            super(view);
            userName = view.findViewById(R.id.user_name);
            password = view.findViewById(R.id.pswd);
            name = view.findViewById(R.id.original_name);
            email = view.findViewById(R.id.e_mail);

        }
    }

    //Populate the adapter
    public PlayerListAdapter(List<Player> dataSet) {
        localDataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.player_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    // Fill the items
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.userName.setText(localDataSet.get(position).username);
        viewHolder.password.setText(localDataSet.get(position).password);
        viewHolder.name.setText(localDataSet.get(position).name);
        viewHolder.email.setText(localDataSet.get(position).email);
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}

