package apps.zambrone.com.contactsplus;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import apps.zambrone.com.contactsplus.configuration.DataStorage;
import apps.zambrone.com.contactsplus.model.User;

import static apps.zambrone.com.contactsplus.configuration.SharedPref.LOGIN_STATS;
import static apps.zambrone.com.contactsplus.configuration.SharedPref.PHONE_NUMBER;
import static apps.zambrone.com.contactsplus.configuration.SharedPref.USERNAME;

public class PhoneNumberActivity extends AppCompatActivity {

    private EditText et_phone_number;
    private Button btn_verify;
    private static final String MY_PREFS_NAME = "USER_DETAIL";
    private FirebaseAuth auth;
    private String name;
    private FirebaseDatabase database;
    private DatabaseReference dbReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);
        et_phone_number = (EditText) findViewById(R.id.activityphone_et_phonenumber);
        btn_verify = (Button) findViewById(R.id.activityphone_register_button);

        auth = FirebaseAuth.getInstance();

        FirebaseUser user = auth.getCurrentUser();

        database = FirebaseDatabase.getInstance();

        if (user != null) {
            if (!auth.getCurrentUser().isEmailVerified()) {

                Toast.makeText(getApplicationContext(), "Your email is not verified", Toast.LENGTH_LONG)
                        .show();

            }
        } else {
            // No user is signed in
        }




        DataStorage dataStorage = new DataStorage(getApplicationContext());
        name = dataStorage.getString(USERNAME, null);

        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhoneNumberActivity.this, MainActivity.class);
                startActivity(intent);
                DataStorage dataStorage = new DataStorage(getApplicationContext());
                dataStorage.setString(PHONE_NUMBER, et_phone_number.getText().toString());
                writeNewUser(auth.getCurrentUser().getUid(), auth.getCurrentUser().getEmail(), name, null, null);
            }
        });


    }

    private User writeNewUser(String userid, String email, String name, String icon, String c_number) {
        User user = new User();
        user.setEmail(userid);
        user.setEmail(email);
        user.setPropicPath(null);

        dbReference = database.getReference("user").child(userid);
        dbReference.setValue(user);
        System.out.println(user);
        return user;

    }
}
