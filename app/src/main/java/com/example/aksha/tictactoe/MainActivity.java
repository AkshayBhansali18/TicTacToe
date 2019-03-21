package com.example.aksha.tictactoe;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button submit;
    EditText playerone_editText, playertwo_editText;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference users = db.collection("Users");
    String username;
    int totalscore;
    int rank;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        submit = (Button) findViewById(R.id.submit);
        playerone_editText = (EditText) findViewById(R.id.playerone_editText);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = playerone_editText.getText().toString();
                if (TextUtils.isEmpty(playerone_editText.getText().toString())) {
                    playerone_editText.setError("Please Enter a UserName");
                } else {
                    final int rank = 0;
                    final int totalscore = 0;
                    UserInfo info = new UserInfo(username, rank, totalscore);
                    users.whereEqualTo("username", username).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            String data = "";
                            for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
                                UserInfo userInfo = queryDocumentSnapshot.toObject(UserInfo.class);
                                String username = userInfo.getUsername();
                                int rank = userInfo.getRank();
                                int totalscore = userInfo.getTotalscore();
                                data += username + rank + totalscore + "\n";
                            }
                            if (data.equals("")) {
                                UserInfo userInfo = new UserInfo(username, rank, totalscore);
                                users.add(userInfo).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentReference> task) {
                                        if (task.isSuccessful())
                                            Toast.makeText(MainActivity.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                                        else
                                            Toast.makeText(MainActivity.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else
                                Toast.makeText(MainActivity.this, "User exists", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, Playertwo_login.class);
                            intent.putExtra("username_playerone", username);
                            startActivity(intent);
                        }

                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("Main Activity", e.toString());
                            Toast.makeText(MainActivity.this, "An Error Occurred", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
