package com.qihe.componetdemo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MyViewModel extends ViewModel {
    private MutableLiveData<List<User>> users;

    public LiveData<List<User>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<>();
            loadUsers();
        }
        return users;
    }

    private void loadUsers() {
        new Thread() {
            @Override
            public void run() {
                List<User> lUsers = new ArrayList<>(10);

                for (int i = 0; i < 10; i++) {
                    lUsers.add(new User());
                }

                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                users.postValue(lUsers);


                for (int i = 0; i < 10; i++) {
                    lUsers.add(new User());
                }

                try {
                    sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                users.postValue(lUsers);

            }
        }.start();
    }
}
