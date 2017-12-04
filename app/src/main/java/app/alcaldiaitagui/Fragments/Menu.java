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

import ServicioWebMenu.Downloader;
import app.alcaldiaitagui.Concurso;
import app.alcaldiaitagui.GridAdapter;
import app.alcaldiaitagui.MainActivity;
import app.alcaldiaitagui.NoticiaDetalle;
import app.alcaldiaitagui.R;

import static app.alcaldiaitagui.R.id.icons;


public class Menu extends Fragment implements NavigationView.OnNavigationItemSelectedListener {

    GridView gv; // Creación de la lista que contiene la información del Menú  letterIcon-Imagenes


    //**Clase para Redireccionar a URL**//
    private void gotoUrl(String url){Intent intent=new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(url));
        startActivity(intent);

    }

public Menu(){}
    final static String urlAddress="http://www.itagui.gov.co/app/app_menu.php";
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_menu, container, false);
        gv=(GridView)view.findViewById(R.id.gridView);

        gv.setNumColumns(3);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW); //se refiere a que la accion sera visual
        intent.addCategory(Intent.CATEGORY_BROWSABLE);



        DisplayMetrics displaymetrics = new DisplayMetrics();
        ImageView banner=(ImageView)view.findViewById(R.id.imageView);
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        Double bannerheigth=(height/2)-height*0.1;
        banner.getLayoutParams().height=bannerheigth.intValue();


        new Downloader(getContext(),urlAddress,gv).execute();


        return view;
    }


    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
