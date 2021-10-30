package com.grupoadec.pm1ejercicio1_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.grupoadec.pm1ejercicio1_3.Configuracion.SQLiteConexion;
import com.grupoadec.pm1ejercicio1_3.Configuracion.Transacciones;
import com.grupoadec.pm1ejercicio1_3.Models.Persona;

import java.util.ArrayList;

public class ActivityConsultaPersonas extends AppCompatActivity {
    // declaracion de variables
    SQLiteConexion conexion;
    ListView lvconsultapersonas;
    final ArrayList<Persona> lista = new ArrayList<Persona>();
    ArrayList<String> ArregloPersonas;
    Persona list_personas = null;
    EditText editTextBuscarPersonaLista;
    ImageView imgreturnlp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_personas);
        // inicializamos variables
        conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        lvconsultapersonas = (ListView) findViewById(R.id.lvconsultapersonas);
        editTextBuscarPersonaLista = (EditText) findViewById(R.id.editTextBuscarPersona);
        imgreturnlp = (ImageView) findViewById(R.id.imgreturnlc);

        ObtenerListaPersonas();

        // inicializamos el adaptador
        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,ArregloPersonas);
        lvconsultapersonas.setAdapter(adp);

        editTextBuscarPersonaLista.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adp.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        imgreturnlp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        lvconsultapersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Persona p = lista.get(i);

                Intent intent = new Intent(getApplicationContext(),ActivityAccionPersona.class);

                intent.putExtra("ipeId", p.getId().toString());
                intent.putExtra("ipeNombres", p.getNombres());
                intent.putExtra("ipeApellidos", p.getApellidos());
                intent.putExtra("ipeEdad", p.getEdad().toString());
                intent.putExtra("ipeCorreo", p.getCorreo());
                intent.putExtra("ipeDireccion", p.getDireccion());

                startActivity(intent);
                finish();
            }
        });

    }

    private void ObtenerListaPersonas() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        // definimos el cursor
        Cursor cursor = db.rawQuery("SELECT * FROM " + Transacciones.tablapersonas, null);
        // reccorremos el cursor
        while (cursor.moveToNext()){
            list_personas = new Persona();
            list_personas.setId(cursor.getInt(0));
            list_personas.setNombres(cursor.getString(1));
            list_personas.setApellidos(cursor.getString(2));
            list_personas.setEdad(cursor.getInt(3));
            list_personas.setCorreo(cursor.getString(4));
            list_personas.setDireccion(cursor.getString(5));
            // Agregamos a la lista
            lista.add(list_personas);
        }
        // cerramos el cursor
        cursor.close();

        LlenarLista();
    }

    private void LlenarLista() {
        ArregloPersonas = new ArrayList<String>();
        for(int i = 0; i < lista.size(); i++){
            ArregloPersonas.add(lista.get(i).getId().toString() + " | " +
                    lista.get(i).getNombres() + " | " +
                    lista.get(i).getApellidos() + " | " +
                    lista.get(i).getEdad() + " | " +
                    lista.get(i).getCorreo() + " | " +
                    lista.get(i).getDireccion());

        }
    }
}