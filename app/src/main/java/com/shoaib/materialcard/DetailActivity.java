package com.shoaib.materialcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView nameView;
    private TextView detailText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imageView = findViewById(R.id.tv_image);
        nameView = findViewById(R.id.tv_name);
        detailText = findViewById(R.id.detailText);
        Intent intent = getIntent();
        nameView.setText(String.valueOf(intent.getStringExtra("Name")));
       Glide.with(getApplicationContext()).load(intent.getIntExtra("Image",-1)).into(imageView);
        detailText.setText(intent.getStringExtra("detailText"));
    }
}
