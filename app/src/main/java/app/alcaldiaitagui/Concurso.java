package app.alcaldiaitagui;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Concurso extends AppCompatActivity implements View.OnClickListener {
    EditText nombre;
    EditText documento;
    EditText correo;
    EditText barrio;
    EditText telefono;
    int contador=0;
    Button enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_concurso);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        ImageView regresar=(ImageView)findViewById(R.id.regresar);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

            nombre=(EditText) findViewById(R.id.nombre);
            documento=(EditText) findViewById(R.id.documento);
            correo=(EditText) findViewById(R.id.correo);
            barrio=(EditText) findViewById(R.id.barrio);
            telefono=(EditText) findViewById(R.id.telefono);

            enviar=(Button)findViewById(R.id.ingresar);
            enviar.setOnClickListener(this);


        final LinearLayout fragmentSoporte=(LinearLayout)findViewById(R.id.linearLayout1);

        nombre.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (hasFocus)
                {
                    InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(nombre, InputMethodManager.SHOW_IMPLICIT);
                }
                else
                {
                    InputMethodManager imm = (InputMethodManager)getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(nombre.getWindowToken(), 0);
                }
            }
        });
        fragmentSoporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSoporte.requestFocus(); // use this to trigger the focus listner
                //or use code below to set the keyboard to hidden
                InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(fragmentSoporte.getWindowToken(), 0);

            }
        });



    }


    public void onClick(View v) {

        contador = 5;
        if (nombre.length() < 1) {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Ingrese un nombre válido", Toast.LENGTH_SHORT);
            contador = contador - 1;
            toast1.show();

        } else if (documento.length()<1) {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Ingrese un documento válido", Toast.LENGTH_SHORT);
            contador = contador - 1;
            toast1.show();

        } else if (correo.length()<1) {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Ingrese un correo válido", Toast.LENGTH_SHORT);
            contador = contador - 1;
            toast1.show();

        } else if (barrio.length()<1) {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Ingrese un barrio válido", Toast.LENGTH_SHORT);
            contador = contador - 1;
            toast1.show();

        }
        else if (telefono.length()<1) {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Ingrese un telefono válido", Toast.LENGTH_SHORT);
            contador = contador - 1;
            toast1.show();

        }
        if (contador == 5) {
            String[] TO = {"gobiernoenlinea.itagui@gmail.com"};

            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");


            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);

            emailIntent.putExtra(Intent.EXTRA_SUBJECT, " Gánate la camiseta LEÓNES");
            emailIntent.putExtra(Intent.EXTRA_TEXT, nombre.getText() + "\n" + documento.getText() + "\n" + correo.getText() + barrio.getText() + "\n" + telefono.getText());

            try {
                startActivity(Intent.createChooser(emailIntent, "Seleccione un servicio de correo"));




            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(Concurso.this,
                        "No tiene instalado Cliente de Correo en su dispositivo", Toast.LENGTH_SHORT).show();
            }

         AlertDialog.Builder dialog= new AlertDialog.Builder(this);
            dialog.setTitle("IMPORTANTE");
            dialog.setMessage("Su registro ha sido exitoso, En el Facebook de la Alcaldía de Itagüí se anunciará el listado de los ganadores");
            dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class); //abre Actividad de Detalle

                    startActivity(intent);
                }
            });
            dialog.setIcon(R.drawable.leones);







            dialog.show();


        }

    }
}
