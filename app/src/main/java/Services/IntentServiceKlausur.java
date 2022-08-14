package Services;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class IntentServiceKlausur extends IntentService {
    //muss implementiert sein
    public IntentServiceKlausur(String name) {
        super(name);
    }

    //muss implementiert sein
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String x = intent != null ? intent.getStringExtra("key") : "nix";
        Toast.makeText(this, x, Toast.LENGTH_SHORT).show();
    }
}
