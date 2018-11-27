package com.sinapsissoft.rizoma;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import com.sinapsissoft.rizoma.adapters.AdapterCrops;
import com.sinapsissoft.rizoma.dto.Crops;
import com.sinapsissoft.rizoma.dto.FirebaseReferences;
import com.sinapsissoft.rizoma.dto.Product;
import com.sinapsissoft.rizoma.dto.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static java.util.Calendar.*;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TextView textView;
    private RecyclerView recyclerViewCrop;
    private AdapterCrops mAdapter;
    private TextView textViewUser;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private List<User>userList=new ArrayList<User>();
    public static  String idUser="";




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

        // Declaración el ArrayList
        final List<Product> listCrops = new ArrayList<>();
        final List<Product> listCrops2 = new ArrayList<>();

// Añadimos 10 Elementos en el ArrayList
        Product product = null;
        for (int i = 0; i < 1; i++) {
            product = new Product();
            product.setProductId(i+"");
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
            //product.setProductImgId(R.drawable.acelga);
            listCrops.add(product);
        }

        mAdapter = new AdapterCrops();

        mAdapter.setDataSet(listCrops);

        recyclerViewCrop.setAdapter(mAdapter);


        String userEmail = getIntent().getStringExtra(LoginActivity.EXTRA_MESSAGE_LOGIN);

        NavigationView navigationView1=(NavigationView)findViewById(R.id.nav_view);
        View headerView =navigationView.getHeaderView(0);
        textViewUser=headerView.findViewById(R.id.textUser);

        //textViewUser.setText(userEmail);

        startFirebase();
        listDataUser();
        // textView=(TextView)findViewById(R.id.text_home);
        //textView.setText(message);
        //databaseReference=FirebaseDatabase.getInstance();

        //String id=databaseReference.push().getKey();
        //databaseReference.child("product").child(id).setValue(product);
       // DatabaseReference myRef=databaseReference.getReference(FirebaseReferences.USERS);
        /*myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user=dataSnapshot.getValue(User.class);
                Log.i("KEY",user.getUserName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

*/


    }

    private void listDataUser() {
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            String email=user.getEmail();
            textViewUser.setText(email);
        }
        /*databaseReference.child(FirebaseReferences.USERS).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 userList.clear();
                 for(DataSnapshot snapshot:dataSnapshot.getChildren()){

                     User u=snapshot.getValue(User.class);

                     userList.add(u);
                     Log.i(FirebaseReferences.USERS,u.toString());
                 }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
    }

    private void startFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();

        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
            String uid = user.getUid();
            idUser=uid;
            Toast.makeText(this,"is signed in"+uid,Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this,"No is signed in",Toast.LENGTH_LONG).show();// No user is signed in
        }
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

    private void updateUser(String sId){
        User user=new User();
        user.setUserId(idUser);
        databaseReference.child(FirebaseReferences.USERS).child(user.getUserId()).setValue(user);
        databaseReference.child(FirebaseReferences.USERS).child(user.getUserId()).child("idCrops").setValue(sId);

    }
    private void createCrop(){
        Crops crops=new Crops();
        crops.setCropsId(UUID.randomUUID().toString());
        Date currentTime = getInstance().getTime();
        crops.setCropsDateEnd(currentTime.toString());
        databaseReference.child(FirebaseReferences.CROPS).child(idUser).child(crops.getCropsId()).setValue(crops);
        /*databaseReference.child(FirebaseReferences.PRODUCT).child(crops.getCropsId()).child().setValue(crops);*/
         Toast.makeText(this,"Data:"+incrementCounter(databaseReference),Toast.LENGTH_LONG).show();


    }

    public Integer incrementCounter(DatabaseReference reference) {

        reference.runTransaction(new Transaction.Handler() {
            @NonNull
            @Override
            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                if (mutableData.getValue() == null) {
                    mutableData.setValue(1);
                } else {
                    mutableData.setValue((long) mutableData.getValue() + 1);
                }
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {
                if (databaseError != null) {
                    Log.d("ERROR", "Firebase counter increment failed.");
                } else {
                    Log.d("ERROR", "Firebase counter increment succeeded.");
                }
            }


        });
        return null;
    }

}
