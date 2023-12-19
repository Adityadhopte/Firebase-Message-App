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
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Repository {

  MutableLiveData<List<ChatGroup>> chatMutableLiveData;

  FirebaseDatabase firebaseDatabase;
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
}
