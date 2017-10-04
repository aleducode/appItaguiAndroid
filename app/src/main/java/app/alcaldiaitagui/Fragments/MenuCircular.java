package app.alcaldiaitagui.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

import app.alcaldiaitagui.R;


public class MenuCircular extends Fragment {
    private void gotoUrl(String url){Intent intent=new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(url));
        startActivity(intent);

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_circular, container, false);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ImageView banner=(ImageView)view.findViewById(R.id.imageView);
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        Double bannerheigth=(height/2)-height*0.25;
        banner.getLayoutParams().height=bannerheigth.intValue();

        ImageView fb=(ImageView)view.findViewById(R.id.fb);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://es-la.facebook.com/alcaldiaitagui/");

            }
        });
        ImageView tw=(ImageView)view.findViewById(R.id.tw);
        tw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://twitter.com/alcaldiaitagui?lang=es");

            }
        });

        ImageView ig=(ImageView)view.findViewById(R.id.ig);
        ig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.instagram.com/alcaldiadeitagui/?hl=es");

            }
        });

        ImageView yt=(ImageView)view.findViewById(R.id.yt);
        yt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.youtube.com/user/alcaldiaitagui1");

            }
        });
        return view;

    }
}
