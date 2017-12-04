package ServicioWebMenu;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import app.alcaldiaitagui.GridAdapter;
import app.alcaldiaitagui.R;

/**
 * Created by alejandroduquecorrea on 28/11/17.
 */

public class Downloader extends AsyncTask<Void,Void,String> {

    Context c;
    String urlAddress;
    GridView gv;

    ProgressDialog pd;


    public Downloader(Context c, String urlAddress, GridView gv) {
        this.c = c;
        this.urlAddress = urlAddress;
        this.gv = gv;
    }

    private void gotoUrl(String url){Intent intent=new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(url));
        c.startActivity(intent);

    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(c);
        pd.setTitle("Cargando");
        pd.setMessage("Cargando Menú principal... ");
        pd.show();
    }

    @Override
    protected String doInBackground(Void... params)  {
        return this.downloadData();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        pd.dismiss();

        if(s==null)
        {
            Toast.makeText(c,"Conexión Incorrecta, Verifique para poder acceder a toda la información sobre Itagüí",Toast.LENGTH_LONG).show();
            final String url[]={"http://www.itagui.gov.co/","http://www.transitoitagui.gov.co/","http://itaguitransparente.gov.co/","https://www.personeriaitagui.gov.co/","http://www.contraloriadeitagui.gov.co/","http://www.adeli.gov.co","https://aplicaciones.itagui.gov.co/pqrs/","https://aplicaciones.itagui.gov.co/WFSecurity/Login.aspx?ReturnUrl=%2f","https://aplicaciones.itagui.gov.co/sisged/radicacionweb/sisgedweb"};
            final String letterList[]={"Sitio Web","Secretaría de Movilidad","Itagüí Transparente","Personería","Contraloría","Adeli","PQRS","Pagos en Línea","Radicación Web"};


            //** Vector que contiene las imagenes de los logos **//
            int lettersIcon[]={R.drawable.sitioweb,R.drawable.movilidad,
                    R.drawable.transparencia,
                    R.drawable.personeria,R.drawable.contraloria,
                    R.drawable.adeli,R.drawable.pqrs,
                    R.drawable.pagosenlinea,R.drawable.radicacion,
            };


            GridAdapter adapter = new GridAdapter(c, lettersIcon, letterList);//invoca funcion grid adapter que genera la cuadricula tipo menuToolbar
            gv.setAdapter(adapter); //ubica la cuadricula
            //** cuando se haga selección en el grid ( tabla de menú)  **//
            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                          @Override
                                          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                              // Al seleccionar cualquier item, en la variable position se asigna su respectivo numero
                                              // al tener el numero en esa posicion se busca en el vector url, la direccion web que le corresponde a la selecion

                                              gotoUrl(url[position]); // teneiendo la direccion web de la seleccion, se usa la funcion go to url para redirigir a la web
                                              Toast.makeText(c, "Selección: " + letterList[position], Toast.LENGTH_SHORT).show(); }// mensaje de texto con lo seleccionado


                                      }
            );

        }else {
            //CALL DATA PARSER TO PARSE
            DataParser parser=new DataParser(c,s,gv);
            parser.execute();
        }
    }

    private String downloadData()
    {
        HttpURLConnection con=Connector.connect(urlAddress);
        if(con==null)
        {
            return null;
        }


        try {

            InputStream is = new BufferedInputStream(con.getInputStream());
            BufferedReader br=new BufferedReader(new InputStreamReader(is));

            String line ;
            StringBuffer s=new StringBuffer();

            if(br != null)
            {
                while ((line=br.readLine()) != null)
                {
                    s.append(line+"\n");
                }

                br.close();
                is.close();

            }else{
                return null;
            }


            return s.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

}