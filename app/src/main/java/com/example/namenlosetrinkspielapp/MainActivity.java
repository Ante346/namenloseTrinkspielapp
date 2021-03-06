package com.example.namenlosetrinkspielapp;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private ArrayList<com.example.namenlosetrinkspielapp.Task> tasks = new ArrayList<>();
    private int taskCount = 0;
    private int playerCount = 0;

    private ArrayList<Player> players = new ArrayList<>();
    private MyAdapter adapter;
    private RecyclerView mRecyclerView;

    //UI
    TextView nameView;
    TextView descriptionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //test
        players.add(new Player("ante"));
        players.add(new Player("stef"));
        players.add(new Player("paul"));
        players.add(new Player("hamster"));
        players.add(new Player("hias"));
        players.add(new Player("Ott"));

        setUpUI();

        db = FirebaseFirestore.getInstance();

        getTasks();
    }

    private void setUpUI() {
        nameView = findViewById(R.id.name_field);
        descriptionView = findViewById(R.id.description_view);

        LinearLayoutManager layoutManager= new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(layoutManager);


        adapter = new MyAdapter(this,players);

        mRecyclerView.setAdapter(adapter);
    }

    private void getTasks() {
        Random random = new Random();

        db.collection("Tasks")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("Success", document.getId() + " => " + document.getData());
                                tasks.add(new com.example.namenlosetrinkspielapp.Task(random.nextInt(),document.getData().get("name").toString(),document.getData().get("description").toString()));
                            }
                            tasks.sort(com.example.namenlosetrinkspielapp.Task::compareTo);
                            getNewTask();
                        } else {
                            Log.d("Fail", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    private void getNewTask() {
        nameView.setText(tasks.get(taskCount).getName());
        descriptionView.setText(tasks.get(taskCount).getDescription());
        taskCount++;
    }

    public void nextCard(android.view.View view){

        //TODO Abfrage ob Karten übrig sind

        getNewTask();
    }

}