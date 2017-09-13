package app.alcaldiaitagui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class GridAdapter extends BaseAdapter {

    private int icons[];
    private String letters[];
    private Context context;
    private LayoutInflater inflater;




    // Declaracion e inicialización de FUNCIÓN GridAdapter con sus variables respectivas
    public GridAdapter(Context context, int icons[], String letters[]){

        this.context=context;
        this.icons=icons;
        this.letters=letters;
    }

// Fétodos GET
    public int getCount(){return letters.length;}
    public Object getItem(int position){return letters[position];}
    public long getItemId(int position){return position;}

    @Override
    //Función que Invoca el layout que contiene el gridView con iconos y texto
    public View getView(int position , View convertView, ViewGroup parent){

        View gridView=convertView;
        if(convertView==null){
            inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridView=inflater.inflate(R.layout.custom_layout,null);
        }

        ImageView icon=(ImageView) gridView.findViewById(R.id.icons);
        TextView letter=(TextView) gridView.findViewById(R.id.letters);
        icon.setImageResource(icons[position]);
        letter.setText(letters[position]);

        return gridView;
    }

}
