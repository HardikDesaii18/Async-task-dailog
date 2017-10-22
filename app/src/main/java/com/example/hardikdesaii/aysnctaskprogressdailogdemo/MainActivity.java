package com.example.hardikdesaii.aysnctaskprogressdailogdemo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button b1;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    b1=(Button)findViewById(R.id.button1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask task=new AsyncTask() {
                    @Override
                    protected void onPreExecute() {
                         pd=new ProgressDialog(MainActivity.this);
                        pd.setTitle("ProgressDailog started");
                        pd.setMessage("This is message");
                        pd.show();
                    }

                    @Override
                    protected Object doInBackground(Object[] params) {
                        for(int i=0;i<5;i++)
                        {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {

                            }
                        }
                        return true;
                    }
                    @Override
                    protected void onPostExecute(Object o) {
                        if(pd != null && pd.isShowing())
                        {
                            pd.dismiss();
                        }
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
                        builder.setContentTitle("Async Task completed");
                        builder.setContentText("This is demo for push notifications");
                        builder.setTicker("This is ticker");
                        builder.setSmallIcon(android.R.drawable.star_on);
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
                        builder.setContentIntent(pi);
                        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        nm.notify(123, builder.build());
                    }
                };
                task.execute();
            }
        });
    }
}
