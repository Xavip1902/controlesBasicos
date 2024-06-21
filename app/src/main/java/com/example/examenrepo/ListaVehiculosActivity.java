package com.example.examenrepo;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListaVehiculosActivity extends AppCompatActivity {

    private ListView listViewVehicles;
    private VehiculoDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_vehiculos);

        listViewVehicles = findViewById(R.id.listViewVehicles);
        dbHelper = new VehiculoDatabaseHelper(this);

        loadVehiculoList();
    }

    private void loadVehiculoList(){
        ArrayList<String> vehiculoList = new ArrayList<>();
        Cursor cursor = dbHelper.obtenerTodosLosVehiculos();

        if (cursor.moveToFirst()){
            do {
                String vehiculo = "Marca: " + cursor.getString(1) +
                                  ", Modelo: " + cursor.getString(2) +
                                  ", Año: " + cursor.getString(3)+
                        ", Número de Motor: " + cursor.getString(4) +
                        ", Número de Chasis: " + cursor.getString(5);
                vehiculoList.add(vehiculo);
            } while (cursor.moveToNext());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, vehiculoList);
        listViewVehicles.setAdapter(adapter);

    }
}