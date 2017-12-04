package ServicioWebMenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import app.alcaldiaitagui.R;

/**
 * Created by alejandroduquecorrea on 29/11/17.
 */

public class CustomAdapter extends BaseAdapter {
    Context c;
    ArrayList<Spacecraft> spacecrafts;
    LayoutInflater inflater;
    public CustomAdapter(Context c, ArrayList<Spacecraft> spacecrafts) {
        this.c = c;
        this.spacecrafts = spacecrafts;
        inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return spacecrafts.size();
    }
    @Override
    public Object getItem(int position) {
        return spacecrafts.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.custom_layout,parent,false);
        }
        TextView nametxt= (TextView) convertView.findViewById(R.id.letters);
        ImageView img= (ImageView) convertView.findViewById(R.id.icons);
        //BIND DATA
        Spacecraft spacecraft=spacecrafts.get(position);
        nametxt.setText(spacecraft.getTitulo());
        //IMG
        PicassoClient.downloadImage(c,spacecraft.getImagenUrl(),img);
        return convertView;
    }
}
