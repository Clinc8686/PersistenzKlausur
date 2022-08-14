package Services;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import de.clinc8686.persistenzklausur.R;

public class ServiceStarter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_starter);
    }

    public void startService(View view) {
        if (isMyServiceRunning()) {
            Intent i = new Intent(getApplicationContext(), ExampleServiceKlausur.class);
            i.putExtra("key", "Value blabla");
            startService(i);
            Toast.makeText(this, "gestartet", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "läuft schon", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isMyServiceRunning() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (ExampleServiceKlausur.class.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public void stopService(View view) {
        Intent i = new Intent(getApplicationContext(), ExampleServiceKlausur.class);
        stopService(i);
        Toast.makeText(this, "gestoppt", Toast.LENGTH_SHORT).show();
    }

    public void startIntentService(View view) {
        Intent i = new Intent(this, IntentServiceKlausur.class);
        i.putExtra("key", "Value xxxxx");
        startService(i);  // For the service.
        //startActivity(i); // For the activity; ignore this for now.
    }

    public void stopIntentService(View view) {
        Intent i = new Intent(this, IntentServiceKlausur.class);
        stopService(i);
    }
}