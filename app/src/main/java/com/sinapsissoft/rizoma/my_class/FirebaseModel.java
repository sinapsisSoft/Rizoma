package com.sinapsissoft.rizoma.my_class;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseModel {
    private FirebaseUser firebaseUser=null;
    public FirebaseModel() {

        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
    }
    public String loggedIn(){

        if(firebaseUser!=null){
            FirebaseReferences.userID=firebaseUser.getUid();
        }
        return FirebaseReferences.userID;
    }


}
