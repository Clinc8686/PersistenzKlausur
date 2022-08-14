package Intents;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import de.clinc8686.persistenzklausur.R;

public class ExplicitIntentReceiver extends AppCompatActivity {
    TextView erste, zweite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit_intent_receiver);

        erste =findViewById(R.id.erste);
        zweite = findViewById(R.id.zweite);

        Intent receivedIntent = getIntent();
        if (receivedIntent != null) {
            String receivedText = receivedIntent.getStringExtra(Intent.EXTRA_TEXT);
             if (receivedIntent.hasExtra(Intent.EXTRA_TEXT))
                erste.setText(receivedIntent.getStringExtra(Intent.EXTRA_TEXT));
             if (receivedIntent.hasExtra("secret"))
                 zweite.setText(receivedIntent.getStringExtra("secret"));
        }

    }

    public void beenden(View view) {
        finish();
    }

    public void beendenmr(View view) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("antwort", "Eine Antwort vom Intent");
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}