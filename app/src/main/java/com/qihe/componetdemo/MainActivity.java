package com.qihe.componetdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;
import java.util.concurrent.CyclicBarrier;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        CountDownLatchProject project = new CountDownLatchProject(10);
//        new Thread(project).start();
//
//
//        for (int i = 0; i < 10; i++) {
//            CountDownLatchdevalper count = new CountDownLatchdevalper(project);
//            new Thread(count).start();
//        }

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                Log.e("tag", "执行完，做额外的操作,然后接着后续的操作。" + Thread.currentThread().getName());
            }
        });
        for (int i = 0; i < 3; i++) {
            new Thread(new Statistic(cyclicBarrier)).start();
        }


        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("brute", 0);
        hashMap.get("brute");
    }
}
