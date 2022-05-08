package com.sibaamap.myapplication;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

public class MyJobService extends JobService {
    public static final String TAG = MyJobService.class.getName();
    private boolean jobCancelled;

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.e(TAG,"Job started");
        doBackGroundWork(params);
        return true;
    }

    private void doBackGroundWork(JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                for(int i = 0;i<19;i++){
                    if(jobCancelled){
                        return;
                    }

                    Log.e(TAG,"run"+i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Log.e(TAG,"Job finished");
                jobFinished(params,false);



            }
        }).start();

    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.e(TAG,"Job stopped");
        jobCancelled = true;
        return true;
    }
}
