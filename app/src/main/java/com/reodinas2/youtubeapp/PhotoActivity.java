package com.reodinas2.youtubeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class PhotoActivity extends AppCompatActivity {

    ImageView imgPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        imgPhoto = findViewById(R.id.imgPhoto);

        // 이미지의 URL을 받아서 보여준다.
        String url = getIntent().getStringExtra("highUrl");

        Glide.with(PhotoActivity.this)
                .load(url)
                .placeholder(R.drawable.baseline_ondemand_video_24)
                .into(imgPhoto);

    }
}