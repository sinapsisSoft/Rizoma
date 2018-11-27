package com.sinapsissoft.rizoma;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_MESSAGE_LOGIN = "com.sinapsissoft.rizoma.LOGIN";


    // UI references.
    private EditText textEmailView;
    private EditText textPasswordView;
    private FirebaseAuth firebaseAuth;
    public View focusView = null;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.

        loadView();


    }

    private void loadView() {

        textEmailView = (EditText) findViewById(R.id.user_email);
        textPasswordView = (EditText) findViewById(R.id.user_password);
        progressDialog = new ProgressDialog(this);
        String getData = getIntent().getStringExtra(RegisteredActivity.EXTRA_MESSAGE);
        if (!TextUtils.isEmpty(getData)) {
            String[] parts = getData.split(",");
            textEmailView.setText(parts[0]);
            textPasswordView.setText(parts[1]);
        }

        Button btnSignIn = (Button) findViewById(R.id.sign_in_button);
        Button btnRegisteredUser = (Button) findViewById(R.id.btn_registered_user);

        btnSignIn.setOnClickListener(this);
        btnRegisteredUser.setOnClickListener(this);

        firebaseAuth=FirebaseAuth.getInstance();
    }


    public void loginUser() {
        final String email = textEmailView.getText().toString().trim();
        final String password = textPasswordView.getText().toString().trim();

        boolean validate=true;

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Valide los datos ingresados", Toast.LENGTH_LONG).show();
            focusView = textEmailView;
            textEmailView.setError("Required");
            validate=false;
        }
        else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Falta los datos de la contraseña", Toast.LENGTH_LONG).show();
            focusView = textPasswordView;
            textPasswordView.setError("Required");
            validate=false;
        }

        if(!validate){
            focusView.requestFocus();

            return;
        }
        progressDialog.setMessage("Realizando login en línea...");
        progressDialog.setCancelable(false);
        progressDialog.show();

            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Bienvenido "+email, Toast.LENGTH_LONG).show();
                    loadStartActivity(email);
                } else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {

                        Toast.makeText(getApplicationContext(), "El usuario no se encuentra registrado", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), RegisteredActivity.class);
                        getApplicationContext().startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "No se pudo realizar el login, valide la información", Toast.LENGTH_LONG).show();
                    }


                }
                progressDialog.dismiss();
            }
        });


    }

    private void loadStartActivity(String email) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(EXTRA_MESSAGE_LOGIN, email);
        startActivity(intent);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_registered_user:
                Intent intent = new Intent(getApplicationContext(), RegisteredActivity.class);
                startActivity(intent);

                break;
            case R.id.sign_in_button:
                loginUser();
                break;
        }
    }


}

