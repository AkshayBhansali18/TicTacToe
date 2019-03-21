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

public class Playertwo_login extends AppCompatActivity {
    Button button_submit;
    EditText playertwo_username;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference users = db.collection("Users");
    String username;
     String playerone_username;
    int totalscore;
    int rank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playertwo_login);
        button_submit=(Button)findViewById(R.id.button_submit);
        playertwo_username=(EditText)findViewById(R.id.playertwo_username);
        FirebaseApp.initializeApp(this);
        Intent intent1=getIntent();
         playerone_username=intent1.getStringExtra("username_playerone");
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final int rank = 0;
                final int totalscore = 0;
                final String p2_username = playertwo_username.getText().toString();
                if (playerone_username.equals(p2_username) || TextUtils.isEmpty(playertwo_username.getText().toString())) {
                    playertwo_username.setError("Please Use Valid Username");
                } else {
                    UserInfo info = new UserInfo(p2_username, rank, totalscore);
                    users.whereEqualTo("username", p2_username).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            String data = "";
                            for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
                                UserInfo userInfo = queryDocumentSnapshot.toObject(UserInfo.class);
                                String username = userInfo.getUsername();
                                int rank = userInfo.getRank();
                                int totalscore = userInfo.getTotalscore();
                                data += p2_username + rank + totalscore + "\n";
                            }
                            if (data.equals("")) {
                                UserInfo userInfo = new UserInfo(p2_username, rank, totalscore);
                                users.add(userInfo).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentReference> task) {
                                        if (task.isSuccessful())
                                            Toast.makeText(Playertwo_login.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                                        else
                                            Toast.makeText(Playertwo_login.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else
                                Toast.makeText(Playertwo_login.this, "User exists", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Playertwo_login.this, Main2Activity.class);
                            Intent intent1 = getIntent();
                            String playerone_username = intent1.getStringExtra("username_playerone");
                            intent.putExtra("username_playerone", playerone_username);
                            intent.putExtra("username_playertwo", p2_username);
                            startActivity(intent);
                        }

                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("Main Activity", e.toString());
                            Toast.makeText(Playertwo_login.this, "An Error Occurred", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}

