package com.qihe.componetdemo;

import android.util.Log;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class Statistic implements Runnable {
    private CyclicBarrier mCyclicBarrier;


    public Statistic(CyclicBarrier parties) {
        this.mCyclicBarrier = parties;
    }

    @Override
    public void run() {
        Log.e("tag", "mCyclicBarrier:  " + Thread.currentThread().getName() + "开始" + mCyclicBarrier.getNumberWaiting());
        try {
            long duration = (long) (Math.random() * 20);
            TimeUnit.SECONDS.sleep(duration);
            mCyclicBarrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e("tag", "mCyclicBarrier:  " + Thread.currentThread().getName() + "结束");
    }
}
