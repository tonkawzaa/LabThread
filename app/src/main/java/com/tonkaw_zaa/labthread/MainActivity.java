package com.tonkaw_zaa.labthread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int counter;
    TextView tvCounter;
    Handler handler;

    Thread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCounter = (TextView) findViewById(R.id.tvCounter);

        // Thread Method 1 : Thread
        /*
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Run in background
                for (int i = 0 ; i<100; i++)
                {
                    counter++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        return;
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // UI Thread a.f.a Main Thread
                            tvCounter.setText(counter+ "");
                        }
                    });

                }
            }
        });
        thread.start();
        */

        // Thread Method 2 : Thread with Handler
        /*
        handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                // Run in Main Thread
                tvCounter.setText(msg.arg1+ "");
            }
        };
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Run in background
                for (int i = 0 ; i<100; i++)
                {
                    counter++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        return;
                    }

                    Message msg = new Message();
                    msg.arg1 = counter;
                    handler.sendMessage(msg);

                }
            }
        });

        thread.start();
        */
        // Thread Method 3 : Handler Only
        handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                counter ++;
                tvCounter.setText(counter + "");
                if(counter < 100){
                    sendEmptyMessageDelayed(0, 1000);
                }
            }
        };
        handler.sendEmptyMessageDelayed(0,1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //thread.interrupt();
    }
}
