package com.acastillo.nicestart;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        ImageView mLuffy = findViewById(R.id.luffy);

        Glide.with(this)
            .load("https://img.rtve.es/imagenes/por-estrellas-solo-brillan-noche/1682336188585.jpg")
            .transition(DrawableTransitionOptions.withCrossFade(2000))
            .placeholder(new ColorDrawable(this.getResources().getColor(R.color.green)))
            .centerCrop()
            .into(mLuffy);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.act_login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void openSignup(View v) {
        Intent intent = new Intent(this, sign_up.class);
        startActivity(intent);
    }

    public void loginToMain(View view) {
        Intent intent = new Intent(this, main.class);
        startActivity(intent);
    }

}
