package com.acastillo.nicestart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class mainbab extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageAdapter adapter;
    private ArrayList<String> imageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainbab);

        setupRecyclerView();
        setupBottomAppBar();
    }

    private void setupRecyclerView() {
        // Lista de URLs de imágenes (puedes agregar más)
        imageUrls.add("https://opbr-en.bn-ent.net/assets/data/webp/character/0004_2d.png.webp");
        imageUrls.add("https://opbr-en.bn-ent.net/assets/data/webp/character/0003_2d.png.webp");
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ImageAdapter(imageUrls);
        recyclerView.setAdapter(adapter);
    }

    private void setupBottomAppBar() {
        BottomAppBar bottomAppBar = findViewById(R.id.bottom_app_bar);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(v ->
                Toast.makeText(mainbab.this, "FAB Clicked", Toast.LENGTH_SHORT).show());

        bottomAppBar.setNavigationOnClickListener(v ->
                Toast.makeText(mainbab.this, "Menú clicked", Toast.LENGTH_SHORT).show());

        bottomAppBar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.menu_submenu) {
                Toast.makeText(this, "Añadido a favoritos", Toast.LENGTH_SHORT).show();
            } else if (item.getItemId() == R.id.menu_submenu2) {
                Toast.makeText(this, "Buscando...", Toast.LENGTH_SHORT).show();
            }
            return true;
        });
    }

    private class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

        private final List<String> imageUrls;

        public ImageAdapter(List<String> imageUrls) {
            this.imageUrls = imageUrls;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.lista_imagenes, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Glide.with(holder.itemView.getContext())
                    .load(imageUrls.get(position))
                    .placeholder(R.drawable.sanji)
                    .error(R.drawable.error)
                    .centerCrop()
                    .into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return imageUrls.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            final ImageView imageView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.itemImage);

                itemView.setOnClickListener(v -> {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Toast.makeText(mainbab.this,
                                "Imagen: " + (position + 1),
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
}