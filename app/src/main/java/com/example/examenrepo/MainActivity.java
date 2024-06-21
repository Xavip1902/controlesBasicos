package com.example.examenrepo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText editTextMarca, editTextModelo, editTextAnio, editTextNumeroMotor, editTextNumeroChasis;
    private Button buttonRegistrar, buttonMostrar;
    private VehiculoDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextMarca = findViewById(R.id.editTextMarca);
        editTextModelo = findViewById(R.id.editTextModelo);
        editTextAnio = findViewById(R.id.editTextAnio);
        editTextNumeroMotor = findViewById(R.id.editTextNumeroMotor);
        editTextNumeroChasis = findViewById(R.id.editTextNumeroChasis);
        buttonMostrar = findViewById(R.id.buttonRegistrar);
        buttonRegistrar = findViewById(R.id.buttonRegistrar);

        dbHelper = new VehiculoDatabaseHelper(this);

        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marca = editTextMarca.getText().toString();
                String modelo = editTextModelo.getText().toString();
                String anio = editTextAnio.getText().toString();
                String numeroMotor = editTextNumeroMotor.getText().toString();
                String numeroChasis = editTextNumeroChasis.getText().toString();

                long result = dbHelper.agregarVehiculo(marca, modelo, anio, numeroMotor, numeroChasis);

                if (result != -1) {
                    Toast.makeText(MainActivity.this, "Vehículo registrado con éxito", Toast.LENGTH_SHORT).show();
                    editTextMarca.setText("");
                    editTextModelo.setText("");
                    editTextAnio.setText("");
                    editTextNumeroMotor.setText("");
                    editTextNumeroChasis.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Error al registrar el vehículo", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListaVehiculosActivity.class);
                startActivity(intent);
            }
        });


    }
}