package com.grupoadec.pm1ejercicio1_3.Configuracion;

public class Transacciones {
    // database Name
    public static final String NameDatabase = "PersonasDB";

    // tabla personas
    public static final String tablapersonas = "personas";

    // campos de la tabla personas
    public static final String id = "id";
    public static final String nombres = "nombres";
    public static final String apellidos = "apellidos";
    public static final String edad = "edad";
    public static final String correo = "correo";
    public static final String direccion = "direccion";

    // Transacciones DDL (Data Definition Language)
    // tabla personas
    public static final String CreateTablePersonas = "CREATE TABLE personas (id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombres TEXT, apellidos TEXT, edad INTEGER, correo TEXT, direccion TEXT)";

    public static final String DropTablePersonas = "DROP TABLE IF EXISTS personas";


}
