package com.example.myapplication_tarea2;
// conversor de longitud, peso y temperatura

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    EditText editTextNombre;
    TextView textViewSaludo, textViewEntrada;
    Button btnLongitud, btnPeso, btnTemp, btnReiniciar;
    StringBuilder entrada = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNombre = findViewById(R.id.editTextNombre);
        textViewSaludo = findViewById(R.id.textViewSaludo);
        textViewEntrada = findViewById(R.id.textViewEntrada);

        btnLongitud = findViewById(R.id.btnLongitud);
        btnPeso = findViewById(R.id.btnPeso);
        btnTemp = findViewById(R.id.btnTemp);
        btnReiniciar = findViewById(R.id.btnReiniciar);

        // Deshabilitar botones hasta que el usuario escriba su nombre
        disableConversionButtons();

        // Listener para habilitar botones cuando se escribe el nombre
        editTextNombre.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                String nombre = editTextNombre.getText().toString().trim();
                if (!nombre.isEmpty()) {
                    textViewSaludo.setText("Hola, " + nombre);
                    enableConversionButtons();
                } else {
                    textViewSaludo.setText("Hola, Usuario");
                    disableConversionButtons();
                }
            }
        });

        // Teclado numerico dinamico (0-9)
        int[] botonesNumericos = {R.id.btn0,R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4,
                R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8,R.id.btn9};

        View.OnClickListener numberClickListener = v -> {
            Button b = (Button) v;
            entrada.append(b.getText().toString());
            textViewEntrada.setText(entrada.toString());
        };

        for (int id : botonesNumericos) {
            findViewById(id).setOnClickListener(numberClickListener);
        }

        // Reiniciar
        btnReiniciar.setOnClickListener(v -> {
            entrada.setLength(0);
            textViewEntrada.setText("0");
        });

        // Conversion Longitud (metros <-> pies)
        btnLongitud.setOnClickListener(v -> {
            if (entrada.length() > 0) {
                double valor = Double.parseDouble(entrada.toString());
                double resultado = valor * 3.28084; // metros a pies
                textViewEntrada.setText(valor + " m = " + resultado + " ft");
            }
        });

        // Conversion Peso (kg <-> libras)
        btnPeso.setOnClickListener(v -> {
            if (entrada.length() > 0) {
                double valor = Double.parseDouble(entrada.toString());
                double resultado = valor * 2.20462; // kg a lb
                textViewEntrada.setText(valor + " kg = " + resultado + " lb");
            }
        });

        // Conversion Temperatura (°C <-> °F)
        btnTemp.setOnClickListener(v -> {
            if (entrada.length() > 0) {
                double valor = Double.parseDouble(entrada.toString());
                double resultado = (valor * 9/5) + 32; // C a F
                textViewEntrada.setText(valor + " °C = " + resultado + " °F");
            }
        });
    }

    private void disableConversionButtons() {
        btnLongitud.setEnabled(false);
        btnPeso.setEnabled(false);
        btnTemp.setEnabled(false);
    }

    private void enableConversionButtons() {
        btnLongitud.setEnabled(true);
        btnPeso.setEnabled(true);
        btnTemp.setEnabled(true);
    }
}