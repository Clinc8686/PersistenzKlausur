package de.clinc8686.persistenzklausur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText kdnr;
    EditText pin;
    Button speichern;
    Button lesen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        kdnr = findViewById(R.id.kontonummer);
        pin = findViewById(R.id.pin);
        speichern = findViewById(R.id.speichern);
        lesen = findViewById(R.id.lesen);

        speichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = getSharedPreferences("Kundendaten", MODE_PRIVATE);
                SharedPreferences.Editor edit = prefs.edit();
                edit.putString("name", name.getText().toString());
                edit.putString("kundennummer", kdnr.getText().toString());
                if (pin.getText().length() > 0)
                    edit.putInt("pin", Integer.parseInt(pin.getText().toString()));
                else
                    edit.putInt("pin", 0);
                edit.apply();
            }
        });

        lesen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = getSharedPreferences("Kundendaten", MODE_PRIVATE);
                String name = prefs.getString("name", "keine Eingabe");
                String kdnr = prefs.getString("kundennummer", "keine Eingabe");
                int pin = prefs.getInt("pin", 0);
                String text = name + " " + kdnr + " " + pin;
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }

}