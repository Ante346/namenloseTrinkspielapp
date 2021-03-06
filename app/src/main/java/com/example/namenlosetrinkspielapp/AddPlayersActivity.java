package com.example.namenlosetrinkspielapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AddPlayersActivity extends AppCompatActivity {

    private Spinner mspin;
    private Context context = this;
    private Button startGamebtn;
    private RecyclerView list;
    private ArrayList<Player> playerList = new ArrayList<>();
    private int playerNumber = 1;

    private PlayerListArrayAdapter adapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);

        setupPlayerNumber();

        setupUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupUI();
    }

    private void setupPlayerNumber(){
        playerList.clear();
        for(int i=1;i<=playerNumber;i++){
            playerList.add(new Player(i));
        }
    }

    private void setupUI() {
        startGamebtn = findViewById(R.id.startGameButton);

        startGamebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("players",getNames());
                startActivity(intent);
            }
        });

        mspin=(Spinner) findViewById(R.id.spinner);
        Integer[] items = new Integer[]{1,2,3,4,5,6,7,8};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, items);
        mspin.setAdapter(adapter);

        mspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                playerNumber = position+1;
                setupPlayerNumber();
                setUpList();
                //System.out.println(playerNumber);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        list = findViewById(R.id.list_view);

        setUpList();
    }


    private String[] getNames() {

        View v;
        EditText et;

        int listLength = list.getChildCount();
        String[] valueOfEditText = new String[listLength];
        for (int i = 0; i < listLength; i++)
        {
            v = list.getChildAt(i);
            et = (EditText) v.findViewById(R.id.playerNameField);
            valueOfEditText[i] = et.getText().toString();
        }

        return valueOfEditText;
    }

    private void setUpList() {
        LinearLayoutManager layoutManager= new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView = (RecyclerView) findViewById(R.id.list_view);
        mRecyclerView.setLayoutManager(layoutManager);


        adapter = new PlayerListArrayAdapter(this,playerList);

        mRecyclerView.setAdapter(adapter);
    }
}
