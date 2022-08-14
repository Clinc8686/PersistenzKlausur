package Broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import de.clinc8686.persistenzklausur.BuildConfig;

public class BroadcastReceiverKlausur extends BroadcastReceiver {
    private static final String ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent System broadcast.
        String intentAction = intent.getAction();

        if (intentAction != null) {
            Log.e("xx", "xx");
            String toastMessage = "unknown intent action";
            switch (intentAction) {
                case Intent.ACTION_POWER_CONNECTED:
                    toastMessage = "Power connected!";
                    break;
                case Intent.ACTION_POWER_DISCONNECTED:
                    toastMessage = "Power disconnected!";
                    break;
                case ACTION_CUSTOM_BROADCAST:   //custom broadcast
                    toastMessage = "Custom Broadcast Received";
                    break;
                case Intent.ACTION_HEADSET_PLUG:
                    toastMessage = "Headset plugged in";
                    break;
            }
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
            // Display the toast.
        }
    }
}