package de.clinc8686.persistenzklausur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
                SharedPreferences prefs = getSharedPreferences("Kundendaten", Context.MODE_PRIVATE);
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

    public void sqlschreiben(View view) {
        SQLiteDatabase db = new DatabaseHelperKlausur(this).getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put(DatabaseHelperKlausur.TITLE_NAME, name.getText().toString());
        val.put(DatabaseHelperKlausur.DESCR_NAME, kdnr.getText().toString());
        db.insert(DatabaseHelperKlausur.TABLE_NAME, null, val);
        db.close();
    }

    public void sqllesen(View view) {
        SQLiteDatabase db = new DatabaseHelperKlausur(this).getReadableDatabase();
        Cursor cur = db.query(DatabaseHelperKlausur.TABLE_NAME, null, null, null, null, null, DatabaseHelperKlausur.TITLE_NAME, null);
        StringBuilder text = new StringBuilder();

        int titleIdx = cur.getColumnIndex(DatabaseHelperKlausur.TITLE_NAME);
        int descrIdx = cur.getColumnIndex(DatabaseHelperKlausur.DESCR_NAME);

        if (cur.moveToFirst()) {
            do {
                text.append(cur.getString(titleIdx)).append(" ");
                text.append(cur.getString(descrIdx)).append(" ");
            } while (cur.moveToNext());
        }

        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}