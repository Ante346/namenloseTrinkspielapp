package com.example.namenlosetrinkspielapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddTaskActivity extends AppCompatActivity {

    private static final String TAG = "database";
    EditText nameField;
    EditText descriptionField;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        nameField = findViewById(R.id.task_name_field);
        descriptionField = findViewById(R.id.task_description_field);
    }

    public void sendTask(android.view.View view){

        String name;
        String description;

        if(nameField.getText().toString().isEmpty()||descriptionField.getText().toString().isEmpty()){
            Context context = getApplicationContext();
            CharSequence text = "Du musst Werte eingeben!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            return;
        }
        else{
            name = nameField.getText().toString();
            description = descriptionField.getText().toString();

            Map<String, Object> Task = new HashMap<>();
            Task.put("name", name);
            Task.put("description", description);

            // Add a new document with a generated ID
            db.collection("Tasks")
                    .add(Task)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                            Context context = getApplicationContext();
                            CharSequence text = "erfolgreich hinzugefügt!";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();

                            nameField.setText("");
                            descriptionField.setText("");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                            Context context = getApplicationContext();
                            CharSequence text = "ups, da ist etwas schief gelaufen!";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                    });
        }

    }

}
