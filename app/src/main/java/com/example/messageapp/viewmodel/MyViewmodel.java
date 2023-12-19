package com.example.messageapp.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.messageapp.model.ChatGroup;
import com.example.messageapp.repository.Repository;

import java.util.List;

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

    public MutableLiveData<List<ChatGroup>> getGroupList(){
        return repository.getChatMutableLiveData();
    }

    public void createNewGroup(String groupName){
        repository.createNewChatGroup(groupName);
    }
}
