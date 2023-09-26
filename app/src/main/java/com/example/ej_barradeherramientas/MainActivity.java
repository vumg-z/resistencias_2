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


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    private int[] bandColors = new int[4];  // Array to store colors
    private int currentBand = -1;  // To keep track of the selected band

    public void onButtonClickBlack(View view) {
        if (currentBand >= 0) {
            bandColors[currentBand] = 0;  // Black
        }
    }

    public void onButtonClickBrown(View view) {
        if (currentBand >= 0) {
            bandColors[currentBand] = 1;  // Brown
        }
    }

    public void onButtonClickRed(View view) {
        if (currentBand >= 0) {
            bandColors[currentBand] = 2;  // Red
        }
    }

    public void onButtonClickOrange(View view) {
        if (currentBand >= 0) {
            bandColors[currentBand] = 3;  // Orange
        }
    }

    public void onButtonClickYellow(View view) {
        if (currentBand >= 0) {
            bandColors[currentBand] = 4;  // Yellow
        }
    }

    public void onButtonClickGreen(View view) {
        if (currentBand >= 0) {
            bandColors[currentBand] = 5;  // Green
        }
    }

    public void onButtonClickBlue(View view) {
        if (currentBand >= 0) {
            bandColors[currentBand] = 6;  // Blue
        }
    }

    public void onButtonClickViolet(View view) {
        if (currentBand >= 0) {
            bandColors[currentBand] = 7;  // Violet
        }
    }

    public void onButtonClickGray(View view) {
        if (currentBand >= 0) {
            bandColors[currentBand] = 8;  // Gray
        }
    }

    public void onButtonClickWhite(View view) {
        if (currentBand >= 0) {
            bandColors[currentBand] = 9;  // White
        }
    }

    public void onButtonClickDorado(View view) {
        bandColors[currentBand] = 10;  // Gold
    }

    public void onButtonClickPlateado(View view) {
        bandColors[currentBand] = 11;  // Silver
    }



    // Selector de Bandas

    public void onButtonClickBand1(View view) {
        currentBand = 0;
        updateTextView("Banda 1 Seleccionada");
    }

    public void onButtonClickBand2(View view) {
        currentBand = 1;
        updateTextView("Banda 2 Seleccionada");
    }

    public void onButtonClickBand3(View view) {
        currentBand = 2;
        updateTextView("Banda 3 Seleccionada");
    }

    public void onButtonClickBand4(View view) {
        currentBand = 3;
        updateTextView("Banda 4 Seleccionada");


    }

    private void updateTextView(String text) {
        TextView textView = findViewById(R.id.text);
        textView.setText(text);
    }


    public int[] calculateResistorValue() {
        int significantValue = 10 * bandColors[0] + bandColors[1];

        int multiplier;
        switch(bandColors[2]) { // Third band (multiplier)
            case 0: // Black
                multiplier = (int) Math.pow(10, 0);
                break;
            case 1: // Brown
                multiplier = (int) Math.pow(10, 1);
                break;
            case 2: // Red
                multiplier = (int) Math.pow(10, 2);
                break;
            case 3: // Orange
                multiplier = (int) Math.pow(10, 3);
                break;
            case 4: // Yellow
                multiplier = (int) Math.pow(10, 4);
                break;
            case 5: // Green
                multiplier = (int) Math.pow(10, 5);
                break;
            case 6: // Blue
                multiplier = (int) Math.pow(10, 6);
                break;
            case 7: // Violet
                multiplier = (int) Math.pow(10, 7);
                break;
            case 8: // Gray
                multiplier = (int) Math.pow(10, 8);
                break;
            case 9: // White
                multiplier = (int) Math.pow(10, 9);
                break;
            case 10: // Gold
                multiplier = (int) Math.pow(10, -1);
                break;
            case 11: // Silver
                multiplier = (int) Math.pow(10, -2);
                break;
            default:
                multiplier = 1;
        }

        int resistanceValue = significantValue * multiplier;

        int tolerance = 0;
        switch(bandColors[3]) {
            case 10: // Gold
                tolerance = 5;
                break;
            case 11: // Silver
                tolerance = 10;
                break;
            default:
                tolerance = 0;
        }

        return new int[] { resistanceValue, tolerance };
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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


    public void onButtonClickGO(View view) {
        int[] result = calculateResistorValue();
        int resistorValue = result[0];
        int tolerance = result[1];

        Intent intent = new Intent(this, ResultadoActivity.class);
        intent.putExtra("resistorValue", resistorValue);
        intent.putExtra("tolerance", tolerance);
        startActivity(intent);
    }


}