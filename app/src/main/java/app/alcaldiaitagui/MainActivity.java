package app.alcaldiaitagui;

import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.lang.reflect.Field;

import app.alcaldiaitagui.Fragments.DatosContacto;
import app.alcaldiaitagui.Fragments.ListaNoticias;
import app.alcaldiaitagui.Fragments.Menu;
import app.alcaldiaitagui.Fragments.MenuCircular;
import app.alcaldiaitagui.Fragments.Soporte;

import static app.alcaldiaitagui.R.id.navigation;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        bottomNavigationView = (BottomNavigationView) findViewById(navigation);
        bottomNavigationView.setItemBackgroundResource(R.color.selector);


        disableShiftMode(bottomNavigationView);


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
                    getSupportActionBar().hide();
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

    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi

                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                //noinspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }


}
