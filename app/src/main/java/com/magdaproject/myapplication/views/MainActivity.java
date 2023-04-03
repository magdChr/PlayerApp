package com.magdaproject.myapplication.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.magdaproject.myapplication.R;
import com.magdaproject.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Set menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.new_player:
                Intent newPlayerIntent = new Intent(this, NewPlayerActivity.class);
                startActivity(newPlayerIntent);
                return true;
            case R.id.player_list:
                Intent playerListIntent = new Intent(this, PlayerListActivity.class);
                startActivity(playerListIntent);
                return true;
            case R.id.delete_players:
                SharedPreferences settings = getSharedPreferences("Playerpreference", MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.clear();
                editor.commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}