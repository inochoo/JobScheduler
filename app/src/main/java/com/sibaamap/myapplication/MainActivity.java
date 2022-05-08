package com.sibaamap.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final int JOB_ID = 123;
    private Button btnStartSchedule,btnCancelSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartSchedule = findViewById(R.id.btn_start_schedule);
        btnCancelSchedule = findViewById(R.id.btn_cancel_schedule);

        btnStartSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickStartSchedulejob();
            }
        });
        btnCancelSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCancelSchedulejob();
            }
        });

    }

    private void onClickCancelSchedulejob() {
        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        jobScheduler.cancel(JOB_ID);
    }

    private void onClickStartSchedulejob() {

        ComponentName componentName = new ComponentName(this,MyJobService.class);
        JobInfo jobInfo = new JobInfo.Builder(JOB_ID, componentName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)// sap nguon khoi dong lai van hoat dong bt
                .setPeriodic(15 * 60 * 1000)// chu ki hoat dong
                .build();

        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(jobInfo);


    }
}