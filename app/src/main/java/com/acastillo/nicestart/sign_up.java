package com.acastillo.nicestart;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class sign_up extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView mZoro = findViewById(R.id.zoro);

        Glide.with(this)
                .load("https://services.meteored.com/img/article/universo-pode-estar-desacelerando-segundo-novas-observacoes-de-galaxias-1712261219743_1280.png") // o usa una URL si lo prefieres
                .transition(DrawableTransitionOptions.withCrossFade(2000))
                .placeholder(new ColorDrawable(this.getResources().getColor(R.color.green)))
                .centerCrop()
                .centerCrop()
                .into(mZoro);
    }
    public void cancel(View v){
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }

    public void signupToMain(View view) {
        Intent intent = new Intent(this, main.class);
        startActivity(intent);
    }
}