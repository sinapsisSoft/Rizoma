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

import com.sinapsissoft.rizoma.fragment.AlertCropFragment;
import com.sinapsissoft.rizoma.fragment.ConfigurationCropFragment;
import com.sinapsissoft.rizoma.fragment.CropFragment;
import com.sinapsissoft.rizoma.fragment.PestFragment;


public class DetailCrop extends AppCompatActivity implements CropFragment.OnFragmentInteractionListener, AlertCropFragment.OnFragmentInteractionListener, ConfigurationCropFragment.OnFragmentInteractionListener, PestFragment.OnFragmentInteractionListener, NavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_crop);

        //create fragment as default
        this.selectViewFragments(0);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

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
                fragment = new ConfigurationCropFragment();
                break;
            case 3:
                fragment = new AlertCropFragment();
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
                    Toast.makeText(getApplicationContext(),"Datos:"+menuItem.getTitle(),Toast.LENGTH_LONG).show();
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
        MenuInflater inflater=getMenuInflater();


        inflater.inflate(R.menu.main, menu);
        //menu.getItem(0).setVisible(false);
        return true;
    }
}
