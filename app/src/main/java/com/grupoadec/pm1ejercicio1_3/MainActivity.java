package com.grupoadec.pm1ejercicio1_3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.grupoadec.pm1ejercicio1_3.Configuracion.SQLiteConexion;
import com.grupoadec.pm1ejercicio1_3.Configuracion.Transacciones;

public class MainActivity extends AppCompatActivity {
    // declaracion de variables
    SQLiteConexion conexion;

    EditText editTextNombres, editTextApellidos, editTextEdad, editTextCorreo, editTextDireccion;
    Button btnSalvarPersona, btnConsultaPersona, btnMainSalir;

    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // inicializamos variables
        conexion = new SQLiteConexion(this, Transacciones.NameDatabase,null,1);

        editTextNombres = (EditText) findViewById(R.id.editTextNombres);
        editTextApellidos = (EditText) findViewById(R.id.editTextApellidos);
        editTextEdad = (EditText) findViewById(R.id.editTextEdad);
        editTextCorreo = (EditText) findViewById(R.id.editTextCorreo);
        editTextDireccion = (EditText) findViewById(R.id.editTextDireccion);

        btnSalvarPersona = (Button) findViewById(R.id.btnSalvarPersona);
        btnConsultaPersona = (Button) findViewById(R.id.btnConsultaPersona);
        btnMainSalir = (Button) findViewById(R.id.btnMainSalir);

        builder = new AlertDialog.Builder(this);

        btnSalvarPersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextNombres.length()>0 && editTextApellidos.length()>0 && editTextEdad.length()>0 && editTextCorreo.length()>0 && editTextDireccion.length()>0){
                    //Mensaje de dialogo
                    builder.setMessage("Desea registrar la persona " + editTextNombres.getText() + " " + editTextApellidos.getText() + " ?")
                            .setCancelable(false)
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    SalvarPersona();
                                    Toast.makeText(getApplicationContext(),"Persona registrada",
                                            Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //  Action for 'NO' Button
                                    dialog.cancel();
                                    Toast.makeText(getApplicationContext(),"No se registro la persona",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                    //Creating dialog box
                    AlertDialog alert = builder.create();
                    //Setting the title manually
                    alert.setTitle("Alerta");
                    alert.show();
                }else{
                    Toast.makeText(getApplicationContext(),"No pueden haber campos vacios, por favor verificar ",Toast.LENGTH_LONG).show();
                }

            }
        });

        btnConsultaPersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ActivityConsultaPersonas.class);
                startActivity(intent);
                finish();
            }
        });

        btnMainSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAndRemoveTask();
            }
        });

    }

    private void SalvarPersona() {
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombres, editTextNombres.getText().toString());
        valores.put(Transacciones.apellidos, editTextApellidos.getText().toString());
        valores.put(Transacciones.edad, editTextEdad.getText().toString());
        valores.put(Transacciones.correo, editTextCorreo.getText().toString());
        valores.put(Transacciones.direccion, editTextDireccion.getText().toString());

        Long resultado =db.insert(Transacciones.tablapersonas, Transacciones.id, valores);
        Toast.makeText(getApplicationContext(),"Persona ingresada, id: " + resultado.toString(),Toast.LENGTH_LONG).show();

        db.close();
        LimpiarPantalla();
    }

    private void LimpiarPantalla() {
        editTextNombres.setText("");
        editTextApellidos.setText("");
        editTextEdad.setText("");
        editTextCorreo.setText("");
        editTextDireccion.setText("");
    }

}