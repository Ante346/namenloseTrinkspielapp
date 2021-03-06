package com.example.namenlosetrinkspielapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    private void goToPlayerActivity(){
        Intent intent = new Intent(this,AddPlayersActivity.class);
        startActivity(intent);
    }

    private void goToTaskActivity(){
        Intent intent = new Intent(this,AddTaskActivity.class);
        startActivity(intent);
    }

    public void startAddPlayerIntent(android.view.View view){
        goToPlayerActivity();
    }

    public void startAddTaskIntent(android.view.View view){
        goToTaskActivity();
    }


}
