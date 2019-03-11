package com.qihe.componetdemo;

import android.util.Log;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchProject implements Runnable {
    private CountDownLatch mCountDownLatch;

    public CountDownLatchProject(int number) {
        this.mCountDownLatch = new CountDownLatch(number);
    }

    public void complete(String name) {
        Log.e("tag", "CountDownLatchProject: " + name + "完成...............");
        mCountDownLatch.countDown();
        Log.e("tag", name + "CountDownLatchProject： 还有: " + mCountDownLatch.getCount() + " 人未完成");
    }

    @Override
    public void run() {
        Log.e("tag", "CountDownLatchProject： 项目开始..................");
        try {
            mCountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e("tag", "CountDownLatchProject： 项目上线.....................");
    }
}
