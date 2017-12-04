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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by alejandroduquecorrea on 28/11/17.
 */

public class  DataParser extends AsyncTask<Void,Void,Integer> {

    Context c;
    String jsonData;
    GridView gv;

    private void gotoUrl(String url){Intent intent=new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(url));
        c.startActivity(intent);

    }
    ProgressDialog pd;
    ArrayList<Spacecraft> spacecrafts=new ArrayList<>();

    public DataParser(Context c, String jsonData ,GridView gv) {
        this.c = c;
        this.jsonData = jsonData;
        this.gv = gv;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd=new ProgressDialog(c);
        pd.setTitle("Parse");
        pd.setMessage("Parsing..Please Wait");
        pd.show();
    }

    @Override
    protected Integer doInBackground(Void... params) {
        return this.parseData();
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);

        pd.dismiss();

        if(result==0)
        {
            Toast.makeText(c,"Error en servicio Web",Toast.LENGTH_SHORT).show();

        }else
        {
            CustomAdapter adapter=new CustomAdapter(c,spacecrafts);
            gv.setAdapter(adapter);


            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   gotoUrl(spacecrafts.get(position).getUrlDireccion());

                }
            });
        }
    }

    private int parseData()
    {
        try {
            JSONObject jo = new JSONObject(jsonData);
            JSONArray ja  = jo.getJSONArray("menu");


            spacecrafts.clear();
            Spacecraft spacecraft;

            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);

                String titulo=jo.getString("titulo");
                String imagenUrl=jo.getString("imagenUrl");
                String urlDireccion=jo.getString("urlDireccion");

                spacecraft=new Spacecraft();

                spacecraft.setTitulo(titulo);
                spacecraft.setImagenUrl(imagenUrl);
                spacecraft.setUrlDireccion(urlDireccion);
                spacecrafts.add(spacecraft);
            }

            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return 0;
    }








}