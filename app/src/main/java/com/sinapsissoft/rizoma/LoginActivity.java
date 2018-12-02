package com.sinapsissoft.rizoma;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.sinapsissoft.rizoma.my_class.FirebaseModel;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText textEmailView;
    private EditText textPasswordView;
    public View focusView = null;
    private ProgressDialog progressDialog;
    private FirebaseModel model = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        model = new FirebaseModel();
        String sResult = model.loggedIn();
        if (!TextUtils.isEmpty(sResult)) {
            loadStartActivity();
        } else {
            loadView();
        }
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
        Button btnSignIn = findViewById(R.id.sign_in_button);
        Button btnRegisteredUser = findViewById(R.id.btn_registered_user);
        btnSignIn.setOnClickListener(this);
        btnRegisteredUser.setOnClickListener(this);
    }

    public void loginUser() {
        final String email = textEmailView.getText().toString().trim();
        final String password = textPasswordView.getText().toString().trim();
        boolean validate = true;
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Valide los datos ingresados", Toast.LENGTH_LONG).show();
            focusView = textEmailView;
            textEmailView.setError("Required");
            validate = false;
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Falta los datos de la contraseña", Toast.LENGTH_LONG).show();
            focusView = textPasswordView;
            textPasswordView.setError("Required");
            validate = false;
        }
        if (!validate) {
            focusView.requestFocus();
            return;
        }
        progressDialog.setMessage("Realizando login en línea...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    private void loadStartActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
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

