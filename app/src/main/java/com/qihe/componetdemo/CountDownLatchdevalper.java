package com.qihe.componetdemo;

import android.util.Log;

import java.util.concurrent.TimeUnit;

public class CountDownLatchdevalper implements Runnable {

    private CountDownLatchProject mCountDownLatchProject;

    public CountDownLatchdevalper(CountDownLatchProject count) {
        this.mCountDownLatchProject = count;
    }

    @Override
    public void run() {
        Log.e("tag", "CountDownLatchdevalper： " + Thread.currentThread().getName() + "开始了............");
        try {
            long duration = (long) (Math.random() * 20);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mCountDownLatchProject.complete(Thread.currentThread().getName());
    }
}
