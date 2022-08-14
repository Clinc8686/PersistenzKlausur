package Intents;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import de.clinc8686.persistenzklausur.R;

public class IntentKlausur extends AppCompatActivity {
    Button ExButton1, ExButton2, ImButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_klausur);

        ExButton1 = findViewById(R.id.ExIntent1);
        ExButton2 = findViewById(R.id.ExIntent2);
        ImButton = findViewById(R.id.ImIntent);

        ExButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent explicitIntent = new Intent(IntentKlausur.this, ExplicitIntentReceiver.class);
                explicitIntent.putExtra("secret", "secret");
                explicitIntent.putExtra(Intent.EXTRA_TEXT, "explizit intent ohne result");
                startActivity(explicitIntent);
            }
        });

        ExButton2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent explicitIntent = new Intent(IntentKlausur.this, ExplicitIntentReceiver.class);
               explicitIntent.putExtra("secret", "secret");
               explicitIntent.putExtra(Intent.EXTRA_TEXT, "explizit intent mit result");
               startActivityForResult(explicitIntent, RESULT_CANCELED);
           }
        });

        ImButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent sendIntent = new Intent();
               sendIntent.setAction(Intent.ACTION_SEND);    //Action Type
               sendIntent.putExtra(Intent.EXTRA_TEXT, "Dieser Text soll geteilt werden.");
               sendIntent.setType("text/plain");    //MIME Type (Data)

               //Prüfen ob Anwendung zum bearbeiten der Anfrage existiert
               if (sendIntent.resolveActivity(getPackageManager()) != null) {
                   startActivity(sendIntent);
               } else {
                   Toast.makeText(IntentKlausur.this, "Gibt keine Anwendung zum öffnen", Toast.LENGTH_SHORT).show();
               }
           }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String antwort = data.getStringExtra("antwort");
            Toast.makeText(IntentKlausur.this, antwort, Toast.LENGTH_SHORT).show();
        }
    }
}