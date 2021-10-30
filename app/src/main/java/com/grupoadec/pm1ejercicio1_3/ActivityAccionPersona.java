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
import android.widget.ImageView;
import android.widget.Toast;

import com.grupoadec.pm1ejercicio1_3.Configuracion.SQLiteConexion;
import com.grupoadec.pm1ejercicio1_3.Configuracion.Transacciones;

public class ActivityAccionPersona extends AppCompatActivity {
    // declaracion de variables
    SQLiteConexion conexion;
    AlertDialog.Builder builder;

    EditText editTextAccionId, editTextAccionNombres,editTextAccionApellidos,editTextAccionEdad,editTextAccionCorreo,editTextAccionDireccion;
    ImageView imgreturnAccion;
    Button btnAccionActualizar,btnAccionEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accion_persona);

        conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        builder = new AlertDialog.Builder(this);

        editTextAccionId = (EditText) findViewById(R.id.editTextAccionId);
        editTextAccionNombres = (EditText) findViewById(R.id.editTextAccionNombres);
        editTextAccionApellidos = (EditText) findViewById(R.id.editTextAccionApellidos);
        editTextAccionEdad = (EditText) findViewById(R.id.editTextAccionEdad);
        editTextAccionCorreo = (EditText) findViewById(R.id.editTextAccionCorreo);
        editTextAccionDireccion = (EditText) findViewById(R.id.editTextAccionDireccion);

        imgreturnAccion = (ImageView) findViewById(R.id.imgreturnAccion);

        btnAccionActualizar = (Button) findViewById(R.id.btnAccionActualizar);
        btnAccionEliminar = (Button) findViewById(R.id.btnAccionEliminar);

        String gieId = getIntent().getStringExtra("ipeId");
        String gieNombres = getIntent().getStringExtra("ipeNombres");
        String gieApellidos = getIntent().getStringExtra("ipeApellidos");
        String gieEdad = getIntent().getStringExtra("ipeEdad");
        String gieCorreo = getIntent().getStringExtra("ipeCorreo");
        String gieDireccion = getIntent().getStringExtra("ipeDireccion");

        editTextAccionId.setText(gieId);
        editTextAccionNombres.setText(gieNombres);
        editTextAccionApellidos.setText(gieApellidos);
        editTextAccionEdad.setText(gieEdad);
        editTextAccionCorreo.setText(gieCorreo);
        editTextAccionDireccion.setText(gieDireccion);

        imgreturnAccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ActivityConsultaPersonas.class);
                startActivity(intent);
                finish();
            }
        });

        btnAccionActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextAccionNombres.length()>0 && editTextAccionApellidos.length()>0 && editTextAccionEdad.length()>0 && editTextAccionCorreo.length()>0 && editTextAccionDireccion.length()>0){
                    //Mensaje de dialogo
                    builder.setMessage("Desea actualizar el registro ?")
                            .setCancelable(false)
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    ActualizarPersona();
                                    Toast.makeText(getApplicationContext(),"Registro Actualizado",
                                            Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //  Action for 'NO' Button
                                    dialog.cancel();
                                    Toast.makeText(getApplicationContext(),"No se actualizo el registro",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                    //Creating dialog box
                    AlertDialog alert = builder.create();
                    //Setting the title manually
                    alert.setTitle("Alerta");
                    alert.show();

                }else {
                    Toast.makeText(getApplicationContext(),"No pueden haber campos vacios, por favor verificar ",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnAccionEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextAccionId.length()>0){
                    builder.setMessage("Desea eliminar el registro ?")
                            .setCancelable(false)
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    EliminarPersona();
                                    Toast.makeText(getApplicationContext(),"Registro Eliminado",
                                            Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //  Action for 'NO' Button
                                    dialog.cancel();
                                    Toast.makeText(getApplicationContext(),"No se elimino el registro",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                    //Creating dialog box
                    AlertDialog alert = builder.create();
                    //Setting the title manually
                    alert.setTitle("Alerta");
                    alert.show();
                }else {
                    Toast.makeText(getApplicationContext(),"Debe seleccionar un registro",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void ActualizarPersona() {
        SQLiteDatabase db = conexion.getWritableDatabase();

        String [] params = { editTextAccionId.getText().toString() };

        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombres, editTextAccionNombres.getText().toString());
        valores.put(Transacciones.apellidos, editTextAccionApellidos.getText().toString());
        valores.put(Transacciones.edad, editTextAccionEdad.getText().toString());
        valores.put(Transacciones.correo, editTextAccionCorreo.getText().toString());
        valores.put(Transacciones.direccion, editTextAccionDireccion.getText().toString());

        db.update(Transacciones.tablapersonas, valores, Transacciones.id + "=?", params);
        db.close();

        Intent intent = new Intent(getApplicationContext(),ActivityConsultaPersonas.class);
        startActivity(intent);
        finish();
    }

    private void EliminarPersona() {
        SQLiteDatabase db = conexion.getWritableDatabase();

        String [] params = { editTextAccionId.getText().toString() };
        String whereCondition = Transacciones.id + "=?";

        db.delete(Transacciones.tablapersonas, whereCondition, params);

        db.close();

        Intent intent = new Intent(getApplicationContext(),ActivityConsultaPersonas.class);
        startActivity(intent);
        finish();
    }
}