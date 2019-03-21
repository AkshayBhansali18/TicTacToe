package com.example.aksha.tictactoe;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class Leaderboard_activity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference users = db.collection("Users");
    ArrayList<UserInfo> info=new ArrayList<>();
ArrayList<String> details=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard_activity);
        final ListView listView=(ListView)findViewById(R.id.listView);
        users.orderBy("totalscore", Query.Direction.DESCENDING).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                int i=1;
                for(QueryDocumentSnapshot queryDocumentSnapshot:queryDocumentSnapshots)
                {
                    UserInfo userInfo=queryDocumentSnapshot.toObject(UserInfo.class);
                    String name=userInfo.getUsername();
                    details.add("Username: "+name+"\n"+"Totalscore: "+userInfo.getTotalscore()+"\n"+"Rank: "+i);
                    i=i+1;
                }
                ArrayAdapter<String> adapter=new ArrayAdapter<>(Leaderboard_activity.this,android.R.layout.simple_list_item_1,details);
                listView.setAdapter(adapter);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Leaderboard_activity.this, "An Error Occurred", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
