package apps.zambrone.com.contactsplus;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;

import apps.zambrone.com.contactsplus.Handlers.ErrorMessageShow;
import apps.zambrone.com.contactsplus.Handlers.ExceptionMessageClass;
import apps.zambrone.com.contactsplus.configuration.DataStorage;
import apps.zambrone.com.contactsplus.model.User;
import de.hdodenhof.circleimageview.CircleImageView;

import static apps.zambrone.com.contactsplus.configuration.SharedPref.LOGIN_STATS;
import static apps.zambrone.com.contactsplus.configuration.SharedPref.USERNAME;

public class SignupActivity extends AppCompatActivity {


    EditText txtContactNo;
    EditText txtName;
    EditText txtEmail;
    EditText txtPassword;
    Button btnRegister;
    TextView btnLogin;
    CircleImageView profileImage;
    Bitmap bitmap;
    ProgressDialog progressDialog;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference dbReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        txtPassword = (EditText) findViewById(R.id.activity_signup_et_password_text_field);
        txtEmail = (EditText) findViewById(R.id.activity_signup_et_email);
        txtName = (EditText) findViewById(R.id.activity_signup_et_name);
        profileImage = (CircleImageView) findViewById(R.id.activity_signup_image);
        btnRegister = (Button) findViewById(R.id.activity_signup_btn_register_button);
        btnLogin = (TextView) findViewById(R.id.activity_signup_tv_signin_button);

        //       FirebaseStorage storage = FirebaseStorage.getInstance();
//        StorageReference storageRef = storage.getReferenceFromUrl("gs://contact-plus-72da0.appspot.com");
//
//        StorageReference imagesRef = storageRef.child("images");
//        StorageReference proimageRef = storageRef.child("reference");

        auth = FirebaseAuth.getInstance();

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpProcess(txtEmail.getText().toString().trim(), txtPassword.getText().toString().trim(), v);
            }
        });


    }

    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }

    private void signUpProcess(final String email, final String password, View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        progressDialog = ProgressDialog.show(SignupActivity.this, "Connecting to database",
                "Please wait..", true);

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(SignupActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();


                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {

                            ErrorMessageShow errorMessageShow = ExceptionMessageClass.getFirebaseErrorMessage(task.getException());
                            if (errorMessageShow != null) {
                                int errorType = errorMessageShow.getType();
                                switch (errorType) {
                                    case 1:
                                        txtEmail.setError(errorMessageShow.getMessage());
                                        break;
                                    case 2:
                                        txtPassword.setError(errorMessageShow.getMessage());
                                        break;
                                    case 3:
                                        txtEmail.setError(errorMessageShow.getMessage());
                                        txtPassword.setError(errorMessageShow.getMessage());
                                        break;
                                    case 4:
//                                        Toast.makeText(SignupActivity.this, errorMessageShow.getMessage(), Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                       // intent.putExtra("email", txtEmail.getText());
                                        startActivity(intent);
                                        Snackbar.make(findViewById(R.id.activity_signup_snacklayout), errorMessageShow.getMessage(), Snackbar.LENGTH_LONG)
                                                .show();

                                        break;
                                    case 5:
                                        txtPassword.setError(errorMessageShow.getMessage());
                                        break;
                                    case 6:
                                        txtPassword.setError(errorMessageShow.getMessage());
                                        break;

                                    default:

                                        break;
                                }

                            }
//                            Toast.makeText(MainActivity.this, ,
//                                    Toast.LENGTH_SHORT).show();


                        } else {
                            // User user = writeNewUser(auth.getCurrentUser().getUid(), email, txtName.getText().toString().trim(), null, null);
                            Snackbar.make(findViewById(R.id.activity_signup_snacklayout), "USER CREATED SUCCESSFULLY", Snackbar.LENGTH_LONG)
                                    .show();

                            DataStorage dataStorage = new DataStorage(getBaseContext());
                            dataStorage.setString(USERNAME, txtName.getText().toString().trim());

                            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);


//                            Gson gson = new Gson();
//                            String json = gson.toJson(user);
                            startActivity(intent);
                        }
                        progressDialog.dismiss();
                    }
                });

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
                FirebaseCrash.report(ex);
            }
        });


    }


}
