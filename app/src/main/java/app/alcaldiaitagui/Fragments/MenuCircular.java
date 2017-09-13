package app.alcaldiaitagui.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

import app.alcaldiaitagui.R;


public class MenuCircular extends Fragment {


    String arrayName[]={"Facebook",
            "Instagram",
            "Twitter",
            "YouTube",
            "Pinterest"};

    private void gotoUrl(String url){Intent intent=new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(url));
        startActivity(intent);

    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_menu_circular, container, false);





        CircleMenu circleMenu = (CircleMenu) view.findViewById(R.id.circle_menu);

        circleMenu.setMainMenu(Color.parseColor("#1972b5"),R.drawable.tocar,R.drawable.tocar)
                .addSubMenu(Color.parseColor("#3b5998"),R.drawable.facebook)
                .addSubMenu(Color.parseColor("#3f729b"),R.drawable.instagram)
                .addSubMenu(Color.parseColor("#03a9f4"),R.drawable.twitter)
                .addSubMenu(Color.parseColor("#ff0000"),R.drawable.youtube)
                .addSubMenu(Color.parseColor("#cb2027"),R.drawable.pinterest)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int index) {
                        Toast.makeText(getContext(),"Selección:  " +arrayName[index],Toast.LENGTH_SHORT).show();
                        switch (arrayName[index]){
                            case "Facebook":
                                gotoUrl("https://es-la.facebook.com/alcaldiaitagui/");
                                break;
                            case "Instagram":
                                gotoUrl( "https://www.instagram.com/alcaldiadeitagui/?hl=es");
                                break;
                            case"Twitter":
                                gotoUrl( "https://twitter.com/alcaldiaitagui?lang=es");
                                break;
                            case"YouTube":
                                gotoUrl( "https://www.youtube.com/user/alcaldiaitagui1");
                                break;

                            case "Pinterest":
                                gotoUrl( "https://es.pinterest.com/alcaldiaitagui/");
                                break;


                            default: break;




                        }
                    }
                });
        return view;
    }


}
