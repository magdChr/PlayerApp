package com.magdaproject.myapplication.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.magdaproject.myapplication.R;
import com.magdaproject.myapplication.databinding.NewPlayerBinding;
import com.magdaproject.myapplication.models.Player;

import androidx.appcompat.app.AppCompatActivity;

public class NewPlayerActivity extends AppCompatActivity {

    private NewPlayerBinding binding;
    int counter;
    private String COUNTER = "counter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = NewPlayerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkIfValidAndSave();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.new_player_menu, menu);
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
            case R.id.player_list:
                Intent playerListIntent = new Intent(this, PlayerListActivity.class);
                startActivity(playerListIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void checkIfValidAndSave() {
        binding.missingFields.setVisibility(View.INVISIBLE);
        //Store in local variables the user inputs
        String username = binding.username.getText().toString();
        String password = binding.password.getText().toString();
        String name = binding.name.getText().toString();
        String email = binding.email.getText().toString();

        //Not allow continue if all fields are not filled
        if (isNullOrEmpty(username) || isNullOrEmpty(password) || isNullOrEmpty(name) || isNullOrEmpty(email)) {
            binding.missingFields.setVisibility(View.VISIBLE);
            binding.missingFields.setText(getResources().getString(R.string.invalid_info));
            return;
        }
        //Create SharedPreferences Object and retrieve editor
        SharedPreferences settings = getSharedPreferences("Playerpreference", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        //Create a Player object and fill attributes from editTexts
        Player player = new Player();
        player.setPswd(password);
        player.setUserName(username);
        player.setName(name);
        player.setEmail(email);

        //Create a json object to serialize
        Gson gsonObject = new Gson();
        String playerString = gsonObject.toJson(player);

        //Store and retrieve counter from SharedPreferences to keep global track
        counter = settings.getInt(COUNTER, 0) + 1;
        editor.putInt(COUNTER, counter);
        //Store new player in SharedPreferences
        editor.putString("Player" + counter, playerString);
        editor.commit();
        binding.missingFields.setVisibility(View.VISIBLE);
        binding.missingFields.setText(getResources().getString(R.string.success_msg, Integer.toString(counter)));
    }

    private Boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }
}
