package com.magdaproject.myapplication.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.magdaproject.myapplication.R;
import com.magdaproject.myapplication.databinding.PlayerListBinding;
import com.magdaproject.myapplication.helpers.PlayerListAdapter;
import com.magdaproject.myapplication.models.Player;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PlayerListActivity extends AppCompatActivity {

    private PlayerListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = PlayerListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SharedPreferences settings = getSharedPreferences("Playerpreference", MODE_PRIVATE);
        int counter = settings.getInt("counter", 0);
        List<Player> playerList = new ArrayList<Player>();
        for (int i = 1; i <= counter; i++) {
            String playerString = settings.getString("Player" + counter, "");
            Player player = new Gson().fromJson(playerString, Player.class);
            playerList.add(player);
        }
        RecyclerView.Adapter adapter = new PlayerListAdapter(playerList);
        binding.recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.player_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.main_page:
                Intent mainPageIntent = new Intent(this, MainActivity.class);
                startActivity(mainPageIntent);
                return true;
            case R.id.new_player:
                Intent newPlayerIntent = new Intent(this, NewPlayerActivity.class);
                startActivity(newPlayerIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
