package com.example.aksha.tictactoe;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import javax.annotation.Nullable;

public class Main2Activity extends AppCompatActivity {
    Button button, button2, button3, button4, button5, button6, button7, button8, button9,button_leaderboard;
    TextView textView_p1,textView_p2, textView_time,textView_p1score,textView_p2score;
    int p1_score=0,p2_score=0;
    String playerone_username;
    DocumentReference p1,p2;
    String playertwo_username;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference users = db.collection("Users");
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        FirebaseApp.initializeApp(this);
         button_leaderboard=(Button)findViewById(R.id.button_leaderboard);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        textView_p1= (TextView) findViewById(R.id.textView_p1);
        textView_p2=(TextView)findViewById(R.id.textView_p2);
        textView_time = (TextView) findViewById(R.id.textView_time);
        textView_p1score=(TextView)findViewById(R.id.textView_p1score);
        textView_p2score=(TextView)findViewById(R.id.textView_p2score);
        Intent intent=getIntent();
         playerone_username=intent.getStringExtra("username_playerone");
         playertwo_username=intent.getStringExtra("username_playertwo");
        textView_p1.setText("Player One"+"\n"+"Username "+playerone_username);
        textView_p2.setText("Player Two"+"\n"+"Username "+playertwo_username);
        textView_p1score.setText("Score "+Integer.toString(p1_score));
        textView_p2score.setText("Score "+Integer.toString(p2_score));
        users.whereEqualTo("username",playerone_username).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot queryDocumentSnapshot:queryDocumentSnapshots)
                {
                    UserInfo userInfo=queryDocumentSnapshot.toObject(UserInfo.class);
                    String id=queryDocumentSnapshot.getId();
                    p1=db.collection("Users").document(id);
                    p1.update("score",p1_score);

                }

            }
        });

        if(count%2==0)
        {
            new CountDownTimer(10,10000)
            {

                @Override
                public void onTick(long millisUntilFinished) {
                    textView_time.setText(Long.toString(millisUntilFinished/1000));

                }

                @Override
                public void onFinish() {
                    AlertDialog.Builder builder=new AlertDialog.Builder(Main2Activity.this);
                    builder.setTitle("Times up! Player One Wins");
                    builder.setIcon(R.drawable.ic_access_alarm_black_24dp);
                    builder.setPositiveButton("Start New Game!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            button.setText("");
                            button2.setText("");
                            button3.setText("");
                            button4.setText("");
                            button5.setText("");
                            button6.setText("");
                            button7.setText("");
                            button8.setText("");
                            button9.setText("");
                            count = 0;


                        }
                    });



                }
            }.start();
        }


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void buttonClick(View view) {

        count = count + 1;
        Button b = (Button) view;

        if (b.getText().toString().isEmpty() != true) {

        } else {
            int id = b.getId();
            if (count % 2 == 0) {

                switch (id) {
                    case R.id.button:
                        button.setText("X");
                        break;
                    case R.id.button2:
                        button2.setText("X");
                        break;
                    case R.id.button3:
                        button3.setText("X");
                        break;
                    case R.id.button4:
                        button4.setText("X");
                        break;
                    case R.id.button5:
                        button5.setText("X");
                        break;
                    case R.id.button6:
                        button6.setText("X");
                        break;
                    case R.id.button7:
                        button7.setText("X");
                        break;
                    case R.id.button8:
                        button8.setText("X");
                        break;
                    case R.id.button9:
                        button9.setText("X");
                        break;
                }
            } else {
                new CountDownTimer(0,10000)
                {

                    @Override
                    public void onTick(long millisUntilFinished) {
                        textView_time.setText(Long.toString(millisUntilFinished/1000));

                    }

                    @Override
                    public void onFinish() {
                        AlertDialog.Builder builder=new AlertDialog.Builder(Main2Activity.this);
                        builder.setTitle("Times up! Player Two Wins");
                        builder.setIcon(R.drawable.ic_access_alarm_black_24dp);
                        builder.setPositiveButton("Start New Game!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                button.setText("");
                                button2.setText("");
                                button3.setText("");
                                button4.setText("");
                                button5.setText("");
                                button6.setText("");
                                button7.setText("");
                                button8.setText("");
                                button9.setText("");
                                count = 0;


                            }
                        });



                    }
                }.start();
                switch (id) {
                    case R.id.button:
                        button.setText("O");
                        break;
                    case R.id.button2:
                        button2.setText("O");
                        break;
                    case R.id.button3:
                        button3.setText("O");
                        break;
                    case R.id.button4:
                        button4.setText("O");
                        break;
                    case R.id.button5:
                        button5.setText("O");
                        break;
                    case R.id.button6:
                        button6.setText("O");
                        break;
                    case R.id.button7:
                        button7.setText("O");
                        break;
                    case R.id.button8:
                        button8.setText("O");
                        break;
                    case R.id.button9:
                        button9.setText("O");
                        break;
                }
            }
        }

        String s1, s2, s3, s4, s5, s6, s7, s8, s9;
        s1 = button.getText().toString();
        s2 = button2.getText().toString();
        s3 = button3.getText().toString();
        s4 = button4.getText().toString();
        s5 = button5.getText().toString();
        s6 = button6.getText().toString();
        s7 = button7.getText().toString();
        s8 = button8.getText().toString();
        s9 = button9.getText().toString();
        if ((s1.equals("X") && s2.equals("X") && s3.equals("X")) || (s1.equals("X") && s4.equals("X") && s7.equals("X")) || (s1.equals("X") && s5.equals("X") && s9.equals("X")) || (s2.equals("X") && s5.equals("X") && s8.equals("X")) || (s3.equals("X") && s6.equals("X") && s9.equals("X")) || ((s4.equals("X") && s5.equals("X") && s6.equals("X"))) || (s7.equals("X") && s8.equals("X") && s9.equals("X"))) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);

            dialog.setTitle(playertwo_username+" Wins!");
            dialog.setIcon(R.drawable.trophy);
            dialog.setPositiveButton("Start new Game!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            p2_score+=20;
            textView_p1score.setText("Score "+p1_score);
            textView_p2score.setText("Score "+p2_score);

            dialog.show();
            button.setText("");
            button2.setText("");
            button3.setText("");
            button4.setText("");
            button5.setText("");
            button6.setText("");
            button7.setText("");
            button8.setText("");
            button9.setText("");
            count = 0;
            users.whereEqualTo("username",playertwo_username).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    for(QueryDocumentSnapshot queryDocumentSnapshot:queryDocumentSnapshots)
                    {
                        UserInfo userInfo=queryDocumentSnapshot.toObject(UserInfo.class);
                        String id=queryDocumentSnapshot.getId();
                        int scores=userInfo.getTotalscore();
                        scores=scores+20;
                        p2=db.collection("Users").document(id);
                        p2.update("totalscore",scores);

                    }

                }
            });

        } else if ((s1.equals("O") && s2.equals("O") && s3.equals("O")) || (s1.equals("O") && s4.equals("O") && s7.equals("O")) || (s1.equals("O") && s5.equals("O") && s9.equals("O")) || (s2.equals("O") && s5.equals("O") && s8.equals("O")) || (s3.equals("O") && s6.equals("O") && s9.equals("O")) || (s4.equals("O") && s5.equals("O") && s6.equals("O")) || (s7.equals("O") && s8.equals("O") && s9.equals("O"))) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle(playerone_username+" Wins!");

            dialog.setIcon(R.drawable.trophy);
            dialog.setPositiveButton("Start new Game!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    button.setText("");
                    button2.setText("");
                    button3.setText("");
                    button4.setText("");
                    button5.setText("");
                    button6.setText("");
                    button7.setText("");
                    button8.setText("");
                    button9.setText("");
                    count=0;

                }
            });
            p1_score+=20;
            users.whereEqualTo("username",playerone_username).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    for(QueryDocumentSnapshot queryDocumentSnapshot:queryDocumentSnapshots)
                    {
                        UserInfo userInfo=queryDocumentSnapshot.toObject(UserInfo.class);
                        String id=queryDocumentSnapshot.getId();
                        int scores=userInfo.getTotalscore();
                        p1=db.collection("Users").document(id);
                        p1.update("totalscore",scores+20);

                    }

                }
            });

            textView_p1score.setText("Score "+p1_score);
            textView_p2score.setText("Score "+p2_score);

            dialog.show();
            button.setText("");
            button2.setText("");
            button3.setText("");
            button4.setText("");
            button5.setText("");
            button6.setText("");
            button7.setText("");
            button8.setText("");
            button9.setText("");
            count = 0;

        } else if (!button.getText().toString().isEmpty() && !button2.getText().toString().isEmpty() && !button3.getText().toString().isEmpty() && !button4.getText().toString().isEmpty() && !button5.getText().toString().isEmpty() && !button6.getText().toString().isEmpty() && !button7.getText().toString().isEmpty() && !button8.getText().toString().isEmpty() && !button9.getText().toString().isEmpty()) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("It's a Draw!");
            dialog.setIcon(R.drawable.trophy);
            dialog.setPositiveButton("Start new Game!", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    button.setText("");
                    button2.setText("");
                    button3.setText("");
                    button4.setText("");
                    button5.setText("");
                    button6.setText("");
                    button7.setText("");
                    button8.setText("");
                    button9.setText("");
                    count=0;

                }
            });
            p1_score+=5;
            p2_score+=5;
            textView_p1score.setText("Score "+p1_score);
            users.whereEqualTo("username",playerone_username).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    for(QueryDocumentSnapshot queryDocumentSnapshot:queryDocumentSnapshots)
                    {
                        UserInfo userInfo=queryDocumentSnapshot.toObject(UserInfo.class);
                        String id=queryDocumentSnapshot.getId();
                        int scores=userInfo.getTotalscore();
                        p1=db.collection("Users").document(id);
                        p1.update("totalscore",scores+5);

                    }

                }
            });
            users.whereEqualTo("username",playertwo_username).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    for(QueryDocumentSnapshot queryDocumentSnapshot:queryDocumentSnapshots)
                    {
                        UserInfo userInfo=queryDocumentSnapshot.toObject(UserInfo.class);
                        String id=queryDocumentSnapshot.getId();
                        int scores=userInfo.getTotalscore();
                        scores=scores+5;
                        p2=db.collection("Users").document(id);
                        p2.update("totalscore",scores);

                    }

                }
            });
            textView_p2score.setText("Score "+p2_score);
            dialog.show();
            button.setText("");
            button2.setText("");
            button3.setText("");
            button4.setText("");
            button5.setText("");
            button6.setText("");
            button7.setText("");
            button8.setText("");
            button9.setText("");
            count=0;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.restart:
                button.setText("");
                button2.setText("");
                button3.setText("");
                button4.setText("");
                button5.setText("");
                button6.setText("");
                button7.setText("");
                button8.setText("");
                button9.setText("");
                count = 0;
                break;
            case R.id.leaderboard:
                Intent intent=new Intent(Main2Activity.this,Leaderboard_activity.class);
                startActivity(intent);
                break;

        }
        return true;

    }
    public void leaderActivity(View view)
    {
        Intent intent=new Intent(Main2Activity.this,Leaderboard_activity.class);
        startActivity(intent);
    }
}


