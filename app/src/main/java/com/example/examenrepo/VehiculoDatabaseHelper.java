package com.example.examenrepo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VehiculoDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "XavierPerez.db";
    private static final int VERSION_BASE_DATOS = 1;
    private static final String NOMBRE_TABLA = "vehiculos";
    private static final String COLUMNA_ID = "id";
    private static final String COLUMNA_MARCA = "marca";
    private static final String COLUMNA_MODELO = "modelo";
    private static final String COLUMNA_ANIO = "ano";
    private static final String COLUMNA_NUMERO_MOTOR = "numero_motor";
    private static final String COLUMNA_NUMERO_CHASIS = "numero_chasis";


    public VehiculoDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION_BASE_DATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREAR_TABLA_VEHICULOS = "Create Table " + NOMBRE_TABLA + "("
                + COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMNA_MARCA + "TEXT,"
                +COLUMNA_MODELO + "TEXT,"
                +COLUMNA_ANIO + "TEXT,"
                +COLUMNA_NUMERO_MOTOR+ "TEXT,"
                +COLUMNA_NUMERO_CHASIS+ "TEXT" + ")";
        db.execSQL(CREAR_TABLA_VEHICULOS);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + NOMBRE_TABLA);
        onCreate(db);
    }

    public long agregarVehiculo(String marca, String modelo, String anio, String numeroMotor, String numeroChasis){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMNA_MARCA, marca);
        values.put(COLUMNA_MODELO, modelo);
        values.put(COLUMNA_ANIO, anio);
        values.put(COLUMNA_NUMERO_MOTOR, numeroMotor);
        values.put(COLUMNA_NUMERO_CHASIS, numeroChasis);
        db.insert(NOMBRE_TABLA, null,values);
        long result = db.insert(NOMBRE_TABLA, null, values);
        db.close();
        return result;
    }

    public Cursor obtenerTodosLosVehiculos(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + NOMBRE_TABLA, null);
    }
}
