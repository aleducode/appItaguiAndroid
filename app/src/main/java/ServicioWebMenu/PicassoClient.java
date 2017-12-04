package ServicioWebMenu;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


import app.alcaldiaitagui.R;

/**
 * Created by alejandroduquecorrea on 29/11/17.
 */

public class PicassoClient {
    public static void downloadImage(Context c,String imageUrl,ImageView img)
    {
        if(imageUrl.length()>0 && imageUrl!=null)
        {
            Picasso.with(c).load(imageUrl).placeholder(R.drawable.cargando).into(img);
        }else {
            Picasso.with(c).load(R.drawable.cargando).into(img);
        }
    }
}