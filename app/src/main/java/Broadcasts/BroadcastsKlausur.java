package Broadcasts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import de.clinc8686.persistenzklausur.BuildConfig;
import de.clinc8686.persistenzklausur.R;

public class BroadcastsKlausur extends AppCompatActivity {
    private BroadcastReceiverKlausur mReceiver = new BroadcastReceiverKlausur();
    private static final String ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcasts_klausur);

        //System Broadcast
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_HEADSET_PLUG);

        // Register the receiver using the activity context.
        this.registerReceiver(mReceiver, filter);



        //Custom Broadcast
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, new IntentFilter(ACTION_CUSTOM_BROADCAST));

    }

    @Override
    protected void onDestroy() {
        // Unregister the sytsem broadcast.
        this.unregisterReceiver(mReceiver);


        //unregister the custom broadcast
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);

        super.onDestroy();
    }

    //for custom broadcast
    public void sendCustomBroadcast(View view) {
        Intent customBroadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);
    }
}