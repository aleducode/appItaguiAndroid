package app.alcaldiaitagui.Fragments;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import app.alcaldiaitagui.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Soporte extends Fragment implements View.OnClickListener {

    EditText asunto;
    EditText nombre;
    EditText correo;
    EditText mensaje;
    int contador=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_soporte, container, false);


        DisplayMetrics displaymetrics = new DisplayMetrics();
        ImageView banner=(ImageView)view.findViewById(R.id.imageView);
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        Double bannerheigth=(height/2)-height*0.25;
        banner.getLayoutParams().height=bannerheigth.intValue();
        asunto=(EditText) view.findViewById(R.id.asunto) ;
        nombre=(EditText) view.findViewById(R.id.nombre) ;
        correo = (EditText) view.findViewById(R.id.correo);
        mensaje = (EditText) view.findViewById(R.id.mensaje);
        Button enviar1=(Button)view.findViewById(R.id.ingresar);
        enviar1.setOnClickListener(this);



        // ESCONDER EL TECLADO AL HACER CLICK//
        final ConstraintLayout fragmentSoporte=(ConstraintLayout)view.findViewById(R.id.fragmentSoporte);
        final LinearLayout contenido=(LinearLayout)view.findViewById(R.id.linearLayout1);

        nombre.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (hasFocus)
                {
                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(nombre, InputMethodManager.SHOW_IMPLICIT);
                }
                else
                {
                    InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(nombre.getWindowToken(), 0);
                }
            }
        });

        asunto.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (hasFocus)
                {
                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(asunto, InputMethodManager.SHOW_IMPLICIT);
                }
                else
                {
                    InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(asunto.getWindowToken(), 0);
                }
            }
        });
        correo.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (hasFocus)
                {
                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(correo, InputMethodManager.SHOW_IMPLICIT);
                }
                else
                {
                    InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(correo.getWindowToken(), 0);
                }
            }
        });

        mensaje.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (hasFocus)
                {
                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(mensaje, InputMethodManager.SHOW_IMPLICIT);
                }
                else
                {
                    InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mensaje.getWindowToken(), 0);
                }
            }
        });

        fragmentSoporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSoporte.requestFocus(); // use this to trigger the focus listner
                //or use code below to set the keyboard to hidden
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(fragmentSoporte.getWindowToken(), 0);

            }
        });


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
