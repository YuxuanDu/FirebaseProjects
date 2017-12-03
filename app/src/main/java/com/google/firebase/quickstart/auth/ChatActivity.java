package com.google.firebase.quickstart.auth;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ChatActivity extends AppCompatActivity {

    private ChatAdapter adapter;
    private List<ChatMessage> chatMessageList;
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    ListView messgaeList;
    Activity activity;
    Button sendBtn;
    String userid;
    EditText messageBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        activity = this;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        userid = preferences.getString("userid", "");
        messgaeList = findViewById(R.id.list_of_messages);
        chatMessageList = new ArrayList<>();
        myRef = database.getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                chatMessageList.clear();
                for (DataSnapshot messageSnapshot : dataSnapshot.child("message").getChildren()) {
                    ChatMessage message = messageSnapshot.getValue(ChatMessage.class);
                    chatMessageList.add(message);
                    if(adapter == null) {
                        adapter = new ChatAdapter(activity, chatMessageList);
                        messgaeList.setAdapter(adapter);
                    }
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        messageBody = findViewById(R.id.input);
        sendBtn = findViewById(R.id.btn_send);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String nickname = dataSnapshot.child("user").child(userid).child("nickname").getValue(String.class);
                        ChatMessage newMessage = new ChatMessage(messageBody.getText().toString(), nickname);
                        myRef.child("message").push().setValue(newMessage);
                        messageBody.setText("");
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
    }

}
