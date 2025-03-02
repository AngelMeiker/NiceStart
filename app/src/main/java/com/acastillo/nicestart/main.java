package com.acastillo.nicestart;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.util.Random;

public class main extends AppCompatActivity {


    private SwipeRefreshLayout swipeLayout;
    private WebView miVisorWeb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Aplicar los márgenes de las barras del sistema (p. ej., la barra de estado)
        setSystemBarsInsets();

        // Configuración del WebView
        miVisorWeb = findViewById(R.id.vistaweb);
        configureWebView();

        // Configuración del SwipeRefreshLayout
        swipeLayout = findViewById(R.id.myswipe);
        swipeLayout.setOnRefreshListener(mOnRefreshListener);
    }

    private void setSystemBarsInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void configureWebView() {
        WebSettings webSettings = miVisorWeb.getSettings();
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        miVisorWeb.loadUrl(urlList[currentUrlIndex]); // Carga la primera URL de la lista
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_appbar, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        return handleMenuItemSelection(item);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return handleMenuItemSelection(item);
    }

    private boolean handleMenuItemSelection(MenuItem item) {
        int id = item.getItemId();
        ConstraintLayout mLayout = findViewById(R.id.main);

        String message;
        String action = "";

        // Determinar el mensaje y la acción según el item seleccionado
        if (id == R.id.item1) {
            message = "Copiando";
            action = "UNDO";
        } else if (id == R.id.item2) {
            message = "Descargando";
            action = "UNDO";
        }
        else if(id == R.id.item3) {
            message = "";
            Intent intent = new Intent(this, profileActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.item0){
            message = "";
            showAlertDialogButtonClicked();
        }
        else if(id == R.id.item4){
            message = "";
            Intent intent = new Intent(this, MainBn.class);
            startActivity(intent);
        }
        else if(id == R.id.item4){
            message = "";
            Intent intent = new Intent(this, MainBn.class);
            startActivity(intent);
        }
        else if(id == R.id.item5){
            message = "";
            Intent intent = new Intent(this, mainbab.class);
            startActivity(intent);
        }
        else {
            message = "";
        }

        if (!message.isEmpty()) {
            Snackbar snackbar = Snackbar.make(mLayout, message, Snackbar.LENGTH_LONG)
                    .setAction(action, view -> {
                        Snackbar undoSnackbar = Snackbar.make(mLayout, message + " cancelada", Snackbar.LENGTH_SHORT);
                        undoSnackbar.show();
                    });
            snackbar.show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_appbar, menu);
        return true;
    }

    private final SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {

            currentUrlIndex = new Random().nextInt(urlList.length);

            // Cargar la nueva URL
            miVisorWeb.loadUrl(urlList[currentUrlIndex]);

            Toast.makeText(
                    main.this,
                    "Cargando...",
                    Toast.LENGTH_SHORT
            ).show();

            miVisorWeb.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    swipeLayout.setRefreshing(false);
                }
            });
        }
    };

    // Lista de URLs de imágenes
    private final String[] urlList = {
            "https://i.pinimg.com/originals/9c/a7/25/9ca725d88fe8e5ef9ab37bc50c75903f.gif",
            "https://qph.cf2.quoracdn.net/main-qimg-d29d307c48530bebb6fd5f1269cac99e",
            "https://gifdb.com/images/high/fighting-scenario-between-luffy-and-rob-lucci-pbfic8u38ui8edol.gif"
    };
    private int currentUrlIndex = 0;

    private void showAlertDialogButtonClicked() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setTitle("Salir de la aplicación");
        builder.setMessage("¿Estás seguro?");
        builder.setIcon(R.drawable.login);
        builder.setCancelable(false);
        builder.setPositiveButton("Sí", (dialog, which) -> {
            Intent intent = new Intent(this, login.class);
            startActivity(intent);
        });
        builder.setNegativeButton("No", (dialog, which) -> {
        });
        builder.show();
    }
}
