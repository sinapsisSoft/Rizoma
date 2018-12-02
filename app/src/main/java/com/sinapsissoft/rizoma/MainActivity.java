package com.sinapsissoft.rizoma;

import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sinapsissoft.rizoma.adapters.AdapterCrops;
import com.sinapsissoft.rizoma.dto.Crops;
import com.sinapsissoft.rizoma.my_class.FirebaseReferences;
import com.sinapsissoft.rizoma.dto.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static java.util.Calendar.*;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TextView textView;
    private RecyclerView recyclerViewCrop;
    private AdapterCrops mAdapter;
    private TextView tViewUser, tViewUserName;
    private ImageView imgProfile;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private List<User> userList = new ArrayList<User>();
    public static String idUser = "";
    private String userEmail;
    List<Crops> listCrops;


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
                createCrop();
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

        NavigationView navigationView1 = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        tViewUser = headerView.findViewById(R.id.textUser);
        tViewUserName= headerView.findViewById(R.id.textUserName);
        imgProfile = headerView.findViewById(R.id.imgProfile);

        startFirebase();
        listDataUser();

        getProfile();


    }
    @Override
    protected void onStart () {

        super.onStart();
        listCrops();
    }
    private void listCrops() {
        // Declaración el ArrayList

        listCrops = new ArrayList<>();
        mAdapter = new AdapterCrops();

        recyclerViewCrop.setAdapter(mAdapter);
        DatabaseReference refCrop = databaseReference.child(FirebaseReferences.CROPS);
        refCrop.child(idUser).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listCrops.removeAll(listCrops);

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Crops crops = snapshot.getValue(Crops.class);
                    Log.i("CROPS", crops.getCropId());
                    listCrops.add(crops);
                }
                mAdapter.notifyDataSetChanged();
                mAdapter.setDataSet(listCrops);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("CROPS", databaseError.getMessage());
            }
        });

    }

    private void listDataUser() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
            String uid = user.getUid();
            idUser = uid;
            FirebaseReferences.userID = idUser;
            //userEmail = user.getEmail();
            //Toast.makeText(this, "is signed in" + uid, Toast.LENGTH_LONG).show();
        } else {
            //Toast.makeText(this, "No is signed in", Toast.LENGTH_LONG).show();// No user is signed in
        }
    }

    private void getProfile() {
        DatabaseReference referenceUsers = databaseReference.child(FirebaseReferences.USERS);
        referenceUsers.child(FirebaseReferences.userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    User user = dataSnapshot.getValue(User.class);
                    tViewUser.setText(user.getUserMail());
                    tViewUserName.setText(user.getUserName()+" "+user.getUserSurname());
                    Picasso.with(getApplicationContext()).load(user.getUserImg()).resize(60,60).centerCrop().into(imgProfile);

                    Log.i("CROPS", dataSnapshot.toString());



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("CROPS", databaseError.getMessage());
            }
        });



    }

    private void startFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        listDataUser();

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


    private void createCrop() {
        Crops crops = new Crops();
        Date currentTime = getInstance().getTime();
        crops.setCropId(UUID.randomUUID().toString());

        crops.setCropDateStart(currentTime.toString());
        crops.setCropName("Lechuga");
        crops.setCropNameScientific("Lactuca sativa L");
        crops.setCropType("Quenopodiáceas");
        crops.setCropDescription("La lechuga es conocida y cultivada en todo el mundo. En Lanzarote en el año 2010 existía 26 Ha\n" +
                "de superficie cultivada, según datos del Servicio de Estadísticas del Gobierno de Canarias. La\n" +
                "lechuga es un producto que tiene un alto contenido en agua y además posee un bajo valor\n" +
                "energético, por lo que se puede utilizar en dietas hipocalóricas. Es rica en antioxidantes como la\n" +
                "vitamina A,C, E, B1, B2, B3 y minerales (fósforo, hierro, calcio, potasio).");
        crops.setCropImg("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQc8JrRmE1Zdx5V8We4TVg9xIPPp1RgSvYMF05BH_OT6MNaWrtr");

        databaseReference.child(FirebaseReferences.CROPS).child(idUser).child(crops.getCropId()).setValue(crops);


    }


}
