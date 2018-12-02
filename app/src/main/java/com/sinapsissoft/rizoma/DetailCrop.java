package com.sinapsissoft.rizoma;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sinapsissoft.rizoma.dto.Crops;
import com.sinapsissoft.rizoma.my_class.FirebaseReferences;
import com.sinapsissoft.rizoma.fragment.AlertCropFragment;
import com.sinapsissoft.rizoma.fragment.ConfigurateCropFragment;
import com.sinapsissoft.rizoma.fragment.CycleCropFragment;
import com.sinapsissoft.rizoma.fragment.CropFragment;
import com.sinapsissoft.rizoma.fragment.PestFragment;


public class DetailCrop extends AppCompatActivity implements ConfigurateCropFragment.OnFragmentInteractionListener,CropFragment.OnFragmentInteractionListener, AlertCropFragment.OnFragmentInteractionListener, CycleCropFragment.OnFragmentInteractionListener, PestFragment.OnFragmentInteractionListener, NavigationView.OnNavigationItemSelectedListener {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_crop);

        //create fragment as default
        this.selectViewFragments(0);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        startFirebase();
        loadView();
    }

    public void loadView() {

        Crops objCrop=FirebaseReferences.CROP;
        Toast.makeText(this,"ID"+objCrop.getCropId(),Toast.LENGTH_LONG).show();


    }

    private void startFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();


    }


    // Replace current Fragment with the destination Fragment.
    private void replaceFragment(Fragment fragmentDefault) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragments, fragmentDefault);
        fragmentTransaction.commit();

    }

    // This method is used to set the fragment that will be shown.
    private void selectViewFragments(Integer iSelect) {
        Fragment fragment = null;
        switch (iSelect) {
            case 0:
                fragment = new CropFragment();
                break;
            case 1:
                fragment = new PestFragment();
                break;
            case 2:
                fragment = new CycleCropFragment();
                break;
            case 3:
                fragment = new AlertCropFragment();
                break;
            case 4:
                fragment = new ConfigurateCropFragment();
                break;
        }
        this.replaceFragment(fragment);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.navigation_crop:
                    selectViewFragments(0);
                    return true;
                case R.id.navigation_pests:
                    selectViewFragments(1);
                    return true;
                case R.id.navigation_configuration:
                    selectViewFragments(2);
                    return true;
                case R.id.navigation_notifications:
                    selectViewFragments(3);
                    Toast.makeText(getApplicationContext(), "Datos:" + menuItem.getTitle(), Toast.LENGTH_LONG).show();
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();


        inflater.inflate(R.menu.detail, menu);
        //menu.getItem(0).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_configuration_crop) {
            selectViewFragments(4);
            Toast.makeText(this, "Gone", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
