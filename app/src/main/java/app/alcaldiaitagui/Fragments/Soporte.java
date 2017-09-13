package app.alcaldiaitagui.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import app.alcaldiaitagui.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Soporte extends Fragment implements View.OnClickListener {

    TextView asunto;
    TextView nombre;
    TextView correo;
    TextView mensaje;
    int contador=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_soporte, container, false);
        asunto=(TextView)view.findViewById(R.id.asunto) ;
        nombre=(TextView)view.findViewById(R.id.nombre) ;
        correo = (TextView)view.findViewById(R.id.correo);
        mensaje = (TextView)view.findViewById(R.id.mensaje);

        Button enviar1=(Button)view.findViewById(R.id.enviar);
        enviar1.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        String asunto1 = asunto.getText().toString();
        contador = 4;
        if (asunto1.length() < 1) {
            Toast toast1 =
                    Toast.makeText(getContext(),
                            "Ingrese un Asunto válido", Toast.LENGTH_SHORT);
            contador = contador - 1;
            toast1.show();

        } else if (nombre.length()<1) {
            Toast toast1 =
                    Toast.makeText(getContext(),
                            "Ingrese un Nombre válido", Toast.LENGTH_SHORT);
            contador = contador - 1;
            toast1.show();

        } else if (correo.length()<1) {
            Toast toast1 =
                    Toast.makeText(getContext(),
                            "Ingrese un Correo válido", Toast.LENGTH_SHORT);
            contador = contador - 1;
            toast1.show();

        } else if (mensaje.length()<1) {
            Toast toast1 =
                    Toast.makeText(getContext(),
                            "Ingrese un Mensaje válido", Toast.LENGTH_SHORT);
            contador = contador - 1;
            toast1.show();

        }


        if (contador == 4) {
            String[] TO = {"contactenos@itagui.gov.co"};

            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");


            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);

            emailIntent.putExtra(Intent.EXTRA_SUBJECT, asunto.getText().toString());
            emailIntent.putExtra(Intent.EXTRA_TEXT, mensaje.getText() + "\n" + correo.getText() + "\n" + nombre.getText());

            try {
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));


            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(getContext(),
                        "No tiene instalado Cliente de Correo en su dispositivo", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
