package com.observerapp.mobile.securesancharapp;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class registerProfile extends AppCompatActivity {

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_profile);

        iv = (ImageView) findViewById(R.id.profilePic);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMedia G = new getMedia();
                G.selectImageFromGallery();
                iv.setImageBitmap(G.Uri_to_Bitmap(G.ResultURI));
            }
        });

    }
}
