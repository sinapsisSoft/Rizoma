package com.sinapsissoft.rizoma.my_class;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sinapsissoft.rizoma.dto.Crops;
import com.sinapsissoft.rizoma.dto.User;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

public class FirebaseModel {
    private FirebaseUser firebaseUser = null;
    private FirebaseDatabase firebaseDatabase = null;
    private DatabaseReference databaseReference;

    public FirebaseModel() {

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public String loggedIn() {

        if (firebaseUser != null) {
            FirebaseReferences.userID = firebaseUser.getUid();
        }
        return FirebaseReferences.userID;
    }
    public List<Crops> listCrops() {

        final List<Crops> listCrops = new ArrayList<>();

        DatabaseReference refCrop = databaseReference.child(FirebaseReferences.CROPS);
        refCrop.child(FirebaseReferences.userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listCrops.removeAll(listCrops);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Crops crops = snapshot.getValue(Crops.class);
                    //Log.i("CROPS", crops.getCropId());
                    listCrops.add(crops);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("CROPS", databaseError.getMessage());
            }
        });

        return listCrops;
    }
    public void getProfile(final TextView tViewUser, final TextView tViewUserName, final ImageView imgProfile, final Context context) {
        DatabaseReference referenceUsers = databaseReference.child(FirebaseReferences.USERS);

        referenceUsers.child(FirebaseReferences.userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);
                tViewUser.setText(user.getUserMail());
                tViewUserName.setText(user.getUserName() + " " + user.getUserSurname());

                Picasso.with(context).load(user.getUserImg()).fit().into(imgProfile);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("CROPS", databaseError.getMessage());
            }
        });




    }

}
