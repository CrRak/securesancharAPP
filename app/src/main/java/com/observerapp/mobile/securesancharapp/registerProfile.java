package com.observerapp.mobile.securesancharapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.net.URI;

public class registerProfile extends getMedia{

    ImageView iv;
    Uri uri;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_profile);

        iv = (ImageView) findViewById(R.id.profilePic);
        sharedPreferences = getSharedPreferences(mypreference , Context.MODE_PRIVATE);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getMedia G = new getMedia();
                selectImageFromGallery();
                String media_name = sharedPreferences.getString(temp_media,"");

                try{
                    uri = Uri.parse(media_name);
                    iv.setImageBitmap(Uri_to_Bitmap(uri));
                }catch (Exception e){

                }


            }
        });

    }


}
