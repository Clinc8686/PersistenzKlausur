package de.clinc8686.persistenzklausur;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText knr;
    EditText pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        knr = findViewById(R.id.kontonummer);
        pin = findViewById(R.id.pin);
    }


}