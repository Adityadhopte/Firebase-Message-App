package com.example.messageapp.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.messageapp.repository.Repository;

public class MyViewmodel extends AndroidViewModel {

    Repository repository;

    public MyViewmodel(@NonNull Application application) {
        super(application);
        this.repository = new  Repository();
    }

    public void signUpAnonymouseUser(){
        Context context = this.getApplication();
        repository.firebaseAnonymousAuth(context);

    }

    public String getCurrentUserId(){
        return  repository.getCurrentId();
    }

    public void signOutId(){
         repository.signOut();
    }
}
