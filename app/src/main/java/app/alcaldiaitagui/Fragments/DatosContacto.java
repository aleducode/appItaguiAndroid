package app.alcaldiaitagui.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import app.alcaldiaitagui.R;

import static android.text.Html.fromHtml;
//import static app.alcaldiaitagui.R.id.layoutAlcaldia;


public class DatosContacto extends Fragment implements View.OnClickListener{

    LinearLayout mapas;
    LinearLayout correo;
    LinearLayout llamar1;

    TextView ubicacion;
    TextView tel;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_datos_contacto, container, false);


        mapas=(LinearLayout)view.findViewById(R.id.layoutmapa);
        mapas.setOnClickListener(this);
        llamar1=(LinearLayout)view.findViewById(R.id.layouttelefono);
        llamar1.setOnClickListener(this);

        correo=(LinearLayout) view.findViewById(R.id.layoutcorreo);
        correo.setOnClickListener(this);


        //Redimiensionar imagen dependiendo del tamaño de la pantalla//
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ImageView banner=(ImageView)view.findViewById(R.id.imageView);
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        Double bannerheigth=(height/2)-height*0.25;
        banner.getLayoutParams().height=bannerheigth.intValue();
        //---------------------------------------------------------//





        //correo.setOnClickListener(this);
        ubicacion=(TextView)view.findViewById(R.id.ubicacion);
        ubicacion.setText("Centro Administrativo Municipal \nde Itagüí -CAMI- \nCarrera 51 No 51-55 \nItagüí - Colombia");

        tel=(TextView)view.findViewById(R.id.telefonos);
        tel.setText("Línea Nacional: 01 8000 518 225 \n\nLínea Local: 034 373 76 76");

       return view;

}


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layoutmapa:
                Uri uri = Uri.parse("https://www.google.com/maps/place/Alcald%C3%ADa+de+Itag%C3%BC%C3%AD/@6.1735059,-75.6087993,205m/data=!3m1!1e3!4m12!1m6!3m5!1s0x0:0x9bd014363837a1bd!2zQWxjYWxkw61hIGRlIEl0YWfDvMOt!8m2!3d6.1735063!4d-75.6090269!3m4!1s0x0:0x9bd014363837a1bd!8m2!3d6.1735063!4d-75.6090269?hl=es-ES");
                if (URLUtil.isValidUrl(uri.toString())) {
                    startActivity( new Intent(Intent.ACTION_VIEW, uri));
                }
                break;
            case R.id.layoutcorreo:

                String[] TO = {"contactenos@itagui.gov.co"};

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");


                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);


                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));

                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getContext(),
                            "No tiene ningún Cliente Email instalado", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.layouttelefono:
                Intent callIntent1=new Intent(Intent.ACTION_DIAL);
                callIntent1.setData(Uri.parse("tel:0343737676"));
                startActivity(callIntent1);
                break;
            default:break;

        }
    }
}
