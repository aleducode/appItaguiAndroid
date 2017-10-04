package app.alcaldiaitagui;

import android.app.ActionBar;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import app.alcaldiaitagui.Entidades.Entidades;
import app.alcaldiaitagui.Fragments.ListaNoticias;


public class NoticiaDetalle extends AppCompatActivity {

    private ImageView ivMovieIcon;
    private TextView tvMovie;
    private TextView tvTagline;
    private TextView tvYear;
    private TextView tvStory;
    private ProgressBar progressBar;
    private Button btnfb,btntw,btnsitio;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia_detalle);

        // ACTION BAR modificacion//
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar_regreso);
        TextView toolbar=(TextView)findViewById(R.id.mytext);
        toolbar.setText("Noticias Municipio de Itagüí");
        ImageView regresar=(ImageView)findViewById(R.id.regresar);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //------------------------//

        // Tamaños variables de botones e imagenes //
        btnfb=(Button)findViewById(R.id.buttonfb);
        btntw=(Button)findViewById(R.id.buttontw);
        btnsitio=(Button)findViewById(R.id.buttonsitio);
        ivMovieIcon=(ImageView)findViewById(R.id.ivIcon);
        int config = getResources().getConfiguration().orientation;
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels; // ancho absoluto en pixels
        int height = metrics.heightPixels; // alto absoluto en pixels
        Double btnwidth=((width-width*0.05)/3);
        if(config==1) {
            FrameLayout.LayoutParams imgparams = new FrameLayout.LayoutParams(
                    width,
                    height / 2
            );

            LinearLayout.LayoutParams btnparams = new LinearLayout.LayoutParams(
                    btnwidth.intValue(),
                    height/14
            );

            ivMovieIcon.setLayoutParams(imgparams);
            btnfb.setLayoutParams(btnparams);
            btnsitio.setLayoutParams(btnparams);
            btntw.setLayoutParams(btnparams);

        }
        else{
            FrameLayout.LayoutParams imgparams = new FrameLayout.LayoutParams(
                    width,
                    height
            );

            LinearLayout.LayoutParams btnparams = new LinearLayout.LayoutParams(
                    btnwidth.intValue(),
                    height/8
            );

            ivMovieIcon.setLayoutParams(imgparams);
            btnfb.setLayoutParams(btnparams);
            btnsitio.setLayoutParams(btnparams);
            btntw.setLayoutParams(btnparams);

        }

        // Recepción de información desde lista noticias//
        setUpUIViews();
        Bundle bundle = getIntent().getExtras(); //Recepcion de la información enviada desde MainActivityNoticias por Bundle
        if(bundle != null){
            String json = bundle.getString("noticiaModel"); // Lectura
            Entidades noticiaModel = new Gson().fromJson(json, Entidades.class);

            // CARGA LA IMAGEN
            ImageLoader.getInstance().displayImage(noticiaModel.getUrlimagen(), ivMovieIcon, new ImageLoadingListener() { //Carga de la imagen
                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    progressBar.setVisibility(View.VISIBLE);
                } // mostrar

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) { //posibles error
                    progressBar.setVisibility(View.GONE);
                }// errores

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) { //carga exitosa
                    progressBar.setVisibility(View.GONE);
                } //exitoso

                @Override
                public void onLoadingCancelled(String imageUri, View view) {
                    progressBar.setVisibility(View.GONE);
                } // error
            });

            //Mostrar la información
            final String urlnoticia;
            urlnoticia=noticiaModel.getUrlNoticia();
            tvMovie.setText(noticiaModel.getTitulo());
            tvYear.setText(noticiaModel.getFecha());
            tvStory.setText(noticiaModel.getTexto());
            //Botón Twitter
            btntw.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {


                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_TEXT, urlnoticia);
                        intent.setPackage("com.twitter.android");
                        startActivity(intent);

                } catch (ActivityNotFoundException ex) {
                    Toast.makeText(getApplicationContext(),
                            "No tiene Instalado Twitter en su dispositivo", Toast.LENGTH_SHORT).show();
                }
                }
            });
            //Botón ir al sitio
            btnsitio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse(urlnoticia);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);

                }
            });

            //Botón facebook
            btnfb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_TEXT, urlnoticia);
                        intent.setPackage("com.facebook.katana");
                        startActivity(intent);
                    } catch (ActivityNotFoundException ex) {
                        Toast.makeText(getApplicationContext(),
                                "No tiene instalado Facebook en su dispositivo", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the main_menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.regresar:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Definición de objetos para mostrar info
    private void setUpUIViews() {
        ivMovieIcon = (ImageView)findViewById(R.id.ivIcon);




        tvMovie = (TextView)findViewById(R.id.tvTitulo);
        tvTagline = (TextView)findViewById(R.id.tvFechaPublicacion);
        tvYear = (TextView)findViewById(R.id.tvFecha);

        tvStory = (TextView)findViewById(R.id.tvStory);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
    }


}


