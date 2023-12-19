package com.example.messageapp.repository;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.messageapp.model.ChatGroup;
import com.example.messageapp.views.GroupsActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    MutableLiveData<List<ChatGroup>> chatMutableLiveData;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public Repository() {
        this.chatMutableLiveData = new MutableLiveData<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void firebaseAnonymousAuth(Context context) {

        FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    Intent i = new Intent(context, GroupsActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }

            }
        });
    }

    public String getCurrentId() {
        return FirebaseAuth.getInstance().getUid();
    }

    public void signOut() {

        FirebaseAuth.getInstance().signOut();
    }

    public MutableLiveData<List<ChatGroup>> getChatMutableLiveData() {
        List<ChatGroup> chatGroupList = new ArrayList<>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    ChatGroup group = new ChatGroup(dataSnapshot.getKey());

                    chatGroupList.add(group);

                }

                chatMutableLiveData.postValue(chatGroupList);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return chatMutableLiveData;
    }

    public void createNewChatGroup(String groupname){
        databaseReference.child(groupname).setValue(groupname);
    }
}
