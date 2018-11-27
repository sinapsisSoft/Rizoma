package com.sinapsissoft.rizoma;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sinapsissoft.rizoma.dto.FirebaseReferences;
import com.sinapsissoft.rizoma.dto.User;

import java.util.UUID;


public class RegisteredActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText textUser;
    private EditText textPassword;
    private EditText textPasswordValidate;
    private ProgressDialog progressDialog;
    private Button btnCreateUser;
    private FirebaseAuth firebaseAuth;
    public View focusView = null;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    public static final String EXTRA_MESSAGE = "com.sinapsissoft.rizoma.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        startFirebase();
        onLoadView();
    }
    private void startFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
    }
    public void onLoadView() {
        Button btn_registered = (Button) findViewById(R.id.btn_user_create);


        textUser = findViewById(R.id.et_user_email);
        textPassword = findViewById(R.id.et_user_password);
        textPasswordValidate = findViewById(R.id.et_user_password_validate);
        btnCreateUser = findViewById(R.id.btn_user_create);
        btnCreateUser.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.

    }

    public void registeredUser() {
        final String email = textUser.getText().toString().trim();
        final String password = textPassword.getText().toString().trim();
        String passwordValidate = textPasswordValidate.getText().toString().trim();
        boolean validate=true;

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Valide los datos ingresados", Toast.LENGTH_LONG).show();
            focusView = textUser;
            textUser.setError("Required");
            validate=false;
        }
        else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Falta los datos de la contraseña", Toast.LENGTH_LONG).show();
            focusView = textPassword;
            textPassword.setError("Required");
            validate=false;
        }
        else if (!password.equals(passwordValidate)) {
            Toast.makeText(this, "Las contraseñas no coindiden", Toast.LENGTH_LONG).show();
            textPasswordValidate.setError("Required");
            validate=false;
        }
        if(!validate){
            focusView.requestFocus();
            return;
        }
        progressDialog.setMessage("Realizando registro en línea...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user=firebaseAuth.getCurrentUser();

                    Toast.makeText(getApplicationContext(), "Se realizó el registro ", Toast.LENGTH_LONG).show();
                    User u=new User();
                    u.setUserId(user.getUid());
                    u.setUserMail(email);
                    u.setUserPassword(password);

                    createUser(u);
                    String []dataUser={email,password};
                    loadViewLogin(dataUser);
                } else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {

                        Toast.makeText(getApplicationContext(), "El usuario ya se encuentra registrado", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "No se pudo realizar el registro", Toast.LENGTH_LONG).show();
                    }


                }
                progressDialog.dismiss();
            }
        });


    }
    private void createUser(User user){

        databaseReference.child(FirebaseReferences.USERS).child(user.getUserId()).setValue(user);

    }
    public void loadViewLogin(String[]data){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        String message=data[0]+","+data[1];
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivity(intent);
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_user_create:
                registeredUser();
                break;
        }
    }
}
