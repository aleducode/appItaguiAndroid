package app.alcaldiaitagui.Fragments;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import app.alcaldiaitagui.Entidades.Entidades;
import app.alcaldiaitagui.NoticiaDetalle;
import app.alcaldiaitagui.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class ListaNoticias extends Fragment {



    private final String URL_TO_HIT = "http://itagui.gov.co/app/consumir_noticias.php"; //define la url que contiene datos

    private ListView lvNoticias;  //define variables para mostrar la información
    private ProgressDialog dialog;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_lista_noticias, container, false);



        dialog = new ProgressDialog(getContext());  //clase dialog hace que en pantalla se muestre mensaje animado mientras carga la info
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("Cargando toda la información sobre Itagüí");
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config);

        lvNoticias=(ListView)view.findViewById(R.id.lvNoticias);

        new JSONTask().execute( URL_TO_HIT);// inicializa conexión
        return view;
    }




    public class JSONTask extends AsyncTask<String,String, List<Entidades> > {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }  // método para mostrar mensajes

        @Override
        protected List<Entidades> doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]); //se realiza la respectiva conexión a BD
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line ="";
                while ((line = reader.readLine()) != null){
                    buffer.append(line);
                }

                String finalJson = buffer.toString();
                //uso de JSON para leer lo que se trae de Base de datos a través del web service

                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray parentArray = parentObject.getJSONArray("noticias"); // Asignación del Array creado en .php

                List<Entidades> noticiaModelList = new ArrayList<>();  // creación lista de lectura con los atributos de la clase Entidades

                Gson gson = new Gson();
                for(int i=0; i<parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);

                    Entidades noticiaModel = gson.fromJson(finalObject.toString(), Entidades.class);
                    //Se usa el método GSON para leer toda la tabla situada en BD de manera general, a diferencia de JSON que se debe nombrar
                    //una a una las variables con su respectivo orden en BD
                    noticiaModelList.add(noticiaModel);
                }
                return noticiaModelList;

            } catch (MalformedURLException e) {
                e.printStackTrace(); //Posibles errores de conexión
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if(connection != null) { //despues de la lectura cerrar conexión
                    connection.disconnect();
                }
                try {
                    if(reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return  null;
        } // conexion a DB indicando el nombre de la tabla que contiene la información

        @Override
        protected void onPostExecute(final List<Entidades> result) {


            super.onPostExecute(result);  //variable result es el identificador de la noticia seleccionado
            dialog.dismiss();

            if(result != null) {
                NoticiaAdapter adapter = new NoticiaAdapter(getContext(), R.layout.row, result); //define el adapter para mostrar noticias

                lvNoticias.setAdapter(adapter); // muestra la lista de noticias
                lvNoticias.setOnItemClickListener(new AdapterView.OnItemClickListener() {  // list item click opens a new detailed activity
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Entidades noticiaModel = result.get(position); // obtiene los parámetros de la noticia
                        Intent intent = new Intent(getContext(), NoticiaDetalle.class); //abre Actividad de Detalle
                       intent.putExtra("noticiaModel", new Gson().toJson(noticiaModel)); //envía la informacion
                       startActivity(intent);



                    }
                });
            } else {
                Toast.makeText(getContext(), "Error de Conexión a red", Toast.LENGTH_SHORT).show();
            }
        }  //método para redirigir a la clase DetailAvctivity cuando se haga click en el item del listview
    }



    public class NoticiaAdapter extends ArrayAdapter {

        private List<Entidades> noticiaModelList;
        private int resource;
        private LayoutInflater inflater;
        // declaración de emetodo con los objetos
        public NoticiaAdapter(Context context, int resource, List<Entidades> objects) {
            super(context, resource, objects);
            noticiaModelList = objects;
            this.resource = resource;

            inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE); //prepara para ser mostrado
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            if(convertView == null){ // carga de la información
                holder = new ViewHolder();
                convertView = inflater.inflate(resource, null);
                holder.ivMovieIcon = (ImageView)convertView.findViewById(R.id.ivIcon);
                holder.tvTitulo = (TextView)convertView.findViewById(R.id.tvTitulo);
                holder.tvFecha =(TextView)convertView.findViewById(R.id.tvFecha);
                holder.tvResumen=(TextView)convertView.findViewById(R.id.tvResumen) ;
                //holder.tvStory = (TextView)convertView.findViewById(R.id.tvStory);



                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            final ProgressBar progressBar = (ProgressBar)convertView.findViewById(R.id.progressBar);
            final ImageView a = (ImageView) convertView.findViewById(R.id.ivIcon);
            final TextView titulo=(TextView) convertView.findViewById(R.id.tvTitulo);
            DisplayMetrics displaymetrics = new DisplayMetrics();
            int config = getResources().getConfiguration().orientation;
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            int height = displaymetrics.heightPixels;
            int width = displaymetrics.widthPixels;
            if(config==1) {
            FrameLayout.LayoutParams param1 = new FrameLayout.LayoutParams(
                    width/3,
                    height/7
            );
            a.setLayoutParams(param1);}
            else{FrameLayout.LayoutParams param1 = new FrameLayout.LayoutParams(
                    width/3,
                    height/3
            );
                a.setLayoutParams(param1);

            }



            // carga de imagen
            final ViewHolder finalHolder = holder;
            ImageLoader.getInstance().displayImage(noticiaModelList.get(position).getUrlimagen(), holder.ivMovieIcon, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    progressBar.setVisibility(View.VISIBLE);
                    finalHolder.ivMovieIcon.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    progressBar.setVisibility(View.GONE);
                    finalHolder.ivMovieIcon.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    progressBar.setVisibility(View.GONE);
                    finalHolder.ivMovieIcon.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {
                    progressBar.setVisibility(View.GONE);
                    finalHolder.ivMovieIcon.setVisibility(View.INVISIBLE);
                }
            });

            holder.tvTitulo.setText(noticiaModelList.get(position).getTitulo());

            holder.tvResumen.setText(noticiaModelList.get(position).getResumen());
            holder.tvFecha.setText(noticiaModelList.get(position).getFecha());
            //holder.tvStory.setText(noticiaModelList.get(position).getTexto());
            return convertView;
        } //Genera la visualización de la información  y las imagenes respectivas


        class ViewHolder{
            private ImageView ivMovieIcon;
            private TextView tvTitulo;
            private TextView tvFecha;
            private TextView tvResumen;


            //private TextView tvStory;
        } //clase de visualización

    }



}
