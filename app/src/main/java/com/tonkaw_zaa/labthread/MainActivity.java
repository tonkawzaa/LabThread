package com.tonkaw_zaa.labthread;

import android.os.Handler;
import android.os.HandlerThread;
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

    HandlerThread backgroundHandlerThread;
    Handler backgroundHandler;
    Handler mainHandler;
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
        /*
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
        */

        //Thread Method 4:HandlerThread
        backgroundHandlerThread = new HandlerThread("BackgroundHandlerThread");
        backgroundHandlerThread.start();

        backgroundHandler = new Handler(backgroundHandlerThread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //Rin with background
                Message msgMain = new Message();
                msgMain.arg1 = msg.arg1 +1;
                mainHandler.sendMessage(msgMain);
            }
        };
        mainHandler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                // Run with Main Thread
                tvCounter.setText(msg.arg1 + "");
                if (msg.arg1 < 100){
                    Message msgBack = new Message();
                    msgBack.arg1 = msg.arg1;
                    backgroundHandler.sendMessageDelayed(msgBack, 1000);
                }
        }
        };

        Message msgBack = new Message();
        msgBack.arg1 = 0 ; // Start count at 0
        backgroundHandler.sendMessageDelayed(msgBack, 1000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //thread.interrupt();
        backgroundHandlerThread.quit();
    }
}
