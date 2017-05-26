package com.tonkaw_zaa.labthread.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Tonkaw_Zaa on 5/26/2017.
 */

public class CounterIntentService extends IntentService {
    public CounterIntentService(){
        super("");
    }
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public CounterIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        // Rin in Background Thread
        for (int i = 0 ; i<100 ; i++){
            Log.d("IntentService", "i = "+ i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
