package app.alcaldiaitagui.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import app.alcaldiaitagui.Concurso;
import app.alcaldiaitagui.GridAdapter;
import app.alcaldiaitagui.MainActivity;
import app.alcaldiaitagui.NoticiaDetalle;
import app.alcaldiaitagui.R;

import static app.alcaldiaitagui.R.id.icons;


public class Menu extends Fragment implements NavigationView.OnNavigationItemSelectedListener {
    String url[]={"http://www.itagui.gov.co/","http://www.transitoitagui.gov.co/","","http://itaguitransparente.gov.co/","https://www.personeriaitagui.gov.co/","http://www.contraloriadeitagui.gov.co/","http://www.adeli.gov.co","https://aplicaciones.itagui.gov.co/pqrs/","https://aplicaciones.itagui.gov.co/WFSecurity/Login.aspx?ReturnUrl=%2f","https://aplicaciones.itagui.gov.co/sisged/radicacionweb/sisgedweb"};

    GridView gridView; // Creación de la lista que contiene la información del Menú  letterIcon-Imagenes

    String letterList[]={"Sitio Web","Secretaría de Movilidad","Ponte la Camiseta","Itagüí Transparente","Personería","Contraloría","Adeli","PQRS","Pagos en Línea","Radicación Web"};


    //** Vector que contiene las imagenes de los logos **//
    int lettersIcon[]={R.drawable.sitioweb,R.drawable.movilidad,R.drawable.leones,
            R.drawable.transparencia,
            R.drawable.personeria,R.drawable.contraloria,
            R.drawable.adeli,R.drawable.pqrs,
            R.drawable.pagosenlinea,R.drawable.radicacion,
    };
    //**Clase para Redireccionar a URL**//
    private void gotoUrl(String url){Intent intent=new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(url));
        startActivity(intent);

    }

public Menu(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_menu, container, false);
     gridView=(GridView)view.findViewById(R.id.gridView);

        gridView.setNumColumns(3);




        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW); //se refiere a que la accion sera visual
        intent.addCategory(Intent.CATEGORY_BROWSABLE);


        GridAdapter adapter = new GridAdapter(getActivity(), lettersIcon, letterList);//invoca funcion grid adapter que genera la cuadricula tipo menuToolbar

        DisplayMetrics displaymetrics = new DisplayMetrics();
        ImageView banner=(ImageView)view.findViewById(R.id.imageView);
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        Double bannerheigth=(height/2)-height*0.1;
        banner.getLayoutParams().height=bannerheigth.intValue();




        gridView.setAdapter(adapter); //ubica la cuadricula

//** cuando se haga selección en el grid ( tabla de menú)  **//
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                // Al seleccionar cualquier item, en la variable position se asigna su respectivo numero
                                                // al tener el numero en esa posicion se busca en el vector url, la direccion web que le corresponde a la selecion
                                                if(position==2)
                                                {
                                                    Intent intent = new Intent(getContext(),Concurso.class); //abre Actividad de Detalle

                                                    startActivity(intent);
                                                }
                                                else{
                                                gotoUrl(url[position]); // teneiendo la direccion web de la seleccion, se usa la funcion go to url para redirigir a la web
                                                Toast.makeText(getContext(), "Selección: " + letterList[position], Toast.LENGTH_SHORT).show(); }// mensaje de texto con lo seleccionado
                                            }

                                        }
        );

        return view;
    }


    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
