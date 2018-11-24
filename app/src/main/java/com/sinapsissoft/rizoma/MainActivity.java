package com.sinapsissoft.rizoma;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.sinapsissoft.rizoma.adapters.AdapterCrops;
import com.sinapsissoft.rizoma.dto.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TextView textView;
    private RecyclerView recyclerViewCrop;
    private AdapterCrops mAdapter;
    private TextView textViewUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerViewCrop = (RecyclerView) findViewById(R.id.recycler_view_crops);
        recyclerViewCrop.setLayoutManager(new LinearLayoutManager(this));

        // Declaración el ArrayList
        final List<Product> listCrops = new ArrayList<>();

// Añadimos 10 Elementos en el ArrayList
        Product product;
        for (int i = 0; i < 1; i++) {
            product = new Product();
            product.setProductId(i);
            product.setProductName("Producto: Acelga");
            product.setProducDescription("Descripción: La acelga es una planta bianual y de ciclo largo que no forma raíz o fruto comestible.\n" +
                    "\n" +
                    "El vástago floral alcanza una altura promedio de 1,20 m.\n" +
                    "\n" +
                    "Las flores son sésiles y hermafroditas pudiendo aparecer solas o en grupos de dos o tres.\n" +
                    "\n" +
                    "El cáliz es de color verdoso y está compuesto por 5 sépalos y 5 pétalos.\n" +
                    "\n" +
                    "Las semillas son muy pequeñas y están encerradas en un pequeño fruto al que comúnmente se le llama semilla (realmente es un fruto), el que contiene de 3 a 4 semillas.");
            product.setProductImgId(R.drawable.acelga);
            listCrops.add(product);
        }

        mAdapter = new AdapterCrops();

        mAdapter.setDataSet(listCrops);

        recyclerViewCrop.setAdapter(mAdapter);


        String userEmail = getIntent().getStringExtra(LoginActivity.EXTRA_MESSAGE_LOGIN);

        NavigationView navigationView1=(NavigationView)findViewById(R.id.nav_view);
        View headerView =navigationView.getHeaderView(0);
        textViewUser=headerView.findViewById(R.id.textUser);

        textViewUser.setText(userEmail);

        // textView=(TextView)findViewById(R.id.text_home);
        //textView.setText(message);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
