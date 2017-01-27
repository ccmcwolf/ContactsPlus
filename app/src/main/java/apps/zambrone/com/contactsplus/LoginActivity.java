package apps.zambrone.com.contactsplus;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


import com.google.firebase.auth.FirebaseAuth;

import apps.zambrone.com.contactsplus.configuration.DataStorage;

import static apps.zambrone.com.contactsplus.configuration.SharedPref.EMAIL_STATUS;
import static apps.zambrone.com.contactsplus.configuration.SharedPref.LOGIN_STATS;
import static apps.zambrone.com.contactsplus.configuration.SharedPref.PHONE_NUMBER;

public class LoginActivity extends AppCompatActivity {


    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private boolean loggedIn = false;


    private EditText txtemail;
    private EditText txtPassword;
    private Button btnLogin;
    private TextView btnRegister;
    private ProgressDialog pDialog;

    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //SharedPreferences sharedpreferences = getSharedPreferences(LOGIN_STATS, Context.MODE_PRIVATE);


        txtemail = (EditText) findViewById(R.id.activitylogin_et_email);
        txtPassword = (EditText) findViewById(R.id.activitylogin_et_password);
        btnLogin = (Button) findViewById(R.id.activitylogin_btn_signin_button);
        btnRegister = (TextView) findViewById(R.id.activitylogin_btn_signup_button);
//        pDialog = new ProgressDialog(LoginActivity.this);
//        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        pDialog.setMessage("Signing In ...");
//        pDialog.setIndeterminate(true);
//        pDialog.setCanceledOnTouchOutside(false);
//        pDialog.setCancelable(false);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtemail.setEnabled(false);
                txtPassword.setEnabled(false);

                pDialog = new ProgressDialog(LoginActivity.this);
                pDialog.setMessage("Connecting..");
                pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
                pDialog.setCancelable(false);
                pDialog.show();

                if (txtemail.getText().toString().trim().isEmpty()) {
                    pDialog.dismiss();
                    txtemail.setEnabled(true);
                    txtPassword.setEnabled(true);
                    txtemail.setError("Username field cannot be empty.");
                } else if (txtPassword.getText().toString().trim().isEmpty()) {
                    pDialog.dismiss();
                    txtemail.setEnabled(true);
                    txtPassword.setEnabled(true);
                    txtPassword.setError("Password field cannot be empty.");
                } else {

                    Login(txtemail.getText().toString().trim(), txtPassword.getText().toString().trim());
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
                finish();
            }
        });
    }

    private void Login(final String email, final String password) {

        auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    pDialog.dismiss();
                    Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    txtemail.setError(task.getException().getMessage());

                } else {
                    pDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("email", txtemail.getText());
                    startActivity(intent);

                    DataStorage dataStorage = new DataStorage(getBaseContext());
                    dataStorage.setString(LOGIN_STATS, auth.getCurrentUser().getUid());


                    String phone = dataStorage.getString(PHONE_NUMBER, "");
                    if (phone.equalsIgnoreCase("")) {
                        Intent phone_intent = new Intent(LoginActivity.this, PhoneNumberActivity.class);

                        startActivity(phone_intent);


                    } else {

                        if (auth.getCurrentUser().isEmailVerified()) {
                            dataStorage.setString(EMAIL_STATUS, auth.getCurrentUser().getUid());
                            Intent phone_intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("email", txtemail.getText());
                            startActivity(phone_intent);

                        }else{
                            auth.getCurrentUser().sendEmailVerification();
                            System.out.println("verification mail sent");
                        }

                    }


                }
            }
        });
    }

    // Show error on Snack Bar.
    public void showError(String error) {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.main_content), error, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }
}
