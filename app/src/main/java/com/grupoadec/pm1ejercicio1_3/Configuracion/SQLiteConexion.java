package com.grupoadec.pm1ejercicio1_3.Configuracion;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class SQLiteConexion extends SQLiteOpenHelper {

    public SQLiteConexion(@Nullable Context context, @Nullable String dbname, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbname, factory, version);
    }

    public SQLiteConexion(@Nullable Context context, @Nullable String dbname, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, dbname, factory, version, errorHandler);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public SQLiteConexion(@Nullable Context context, @Nullable String dbname, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, dbname, version, openParams);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creamos la tabla en la base de datos
        db.execSQL(Transacciones.CreateTablePersonas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // eliminamos la tabla de la base de datos
        db.execSQL(Transacciones.DropTablePersonas);

        // creamos nuevamente la tabla
        onCreate(db);
    }
}
