package com.example.ej_barradeherramientas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ResultadoActivity extends AppCompatActivity {

    Toolbar toolbar;

    public void onBackClick(View view) {
        // cerrar la actividad
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.opc) {
            Intent intent = new Intent(this, AyudaActivity.class);
            startActivity(intent);
            return true;
        }
        if (item.getItemId() == R.id.opc1) {
            Toast.makeText(this, "ChinoDEV", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int receivedResistorValue = getIntent().getIntExtra("resistorValue", -1);
        int receivedTolerance = getIntent().getIntExtra("tolerance", -1);

        int errorMargin = (int) ((receivedTolerance / 100.0) * receivedResistorValue);

        int maxValue = receivedResistorValue + errorMargin;
        int minValue = receivedResistorValue - errorMargin;

        String prefix = " Ohms";
        double adjustedValue = receivedResistorValue;
        double adjustedMax = maxValue;
        double adjustedMin = minValue;

        if (receivedResistorValue >= 1_000_000) {
            prefix = " MΩ";
            adjustedValue = receivedResistorValue / 1_000_000.0;
            adjustedMax = maxValue / 1_000_000.0;
            adjustedMin = minValue / 1_000_000.0;
        } else if (receivedResistorValue >= 1_000) {
            prefix = " KΩ";
            adjustedValue = receivedResistorValue / 1_000.0;
            adjustedMax = maxValue / 1_000.0;
            adjustedMin = minValue / 1_000.0;
        }

        TextView resultTextView = findViewById(R.id.text);

        resultTextView.setText("Resistencia: " + adjustedValue + prefix +
                "\nTolerancia: ±" + String.valueOf(receivedTolerance) + "%" +
                "\nValor Máximo: " + adjustedMax + prefix +
                "\nValor Mínimo: " + adjustedMin + prefix);
    }


}