package app.alcaldiaitagui;

import android.content.res.Configuration;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.widget.TextView;

import app.alcaldiaitagui.Fragments.DatosContacto;
import app.alcaldiaitagui.Fragments.ListaNoticias;
import app.alcaldiaitagui.Fragments.Menu;
import app.alcaldiaitagui.Fragments.MenuCircular;
import app.alcaldiaitagui.Fragments.Soporte;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        final Fragment menu= new Menu();
        final Fragment listaNoticias = new ListaNoticias();
        final Fragment menuCircular=new MenuCircular();
        final Fragment datosContacto = new DatosContacto();
        final Fragment soporte= new Soporte();



        if (savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainer,menu).commit();
        }





        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentManager fragmentManager = getSupportFragmentManager();

                if (item.getItemId() == R.id.inicioItem) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentContainer,menu).commit();
                } else if (item.getItemId() == R.id.buscarItem) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentContainer, listaNoticias).commit();
                } else if (item.getItemId() == R.id.camaraItem) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentContainer, menuCircular).commit();
                } else if (item.getItemId() == R.id.favoritosItem) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentContainer, datosContacto).commit();
                } else if (item.getItemId() == R.id.perfilItem) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentContainer, soporte).commit();
                }

                return true;
            }
        });

    }







}
