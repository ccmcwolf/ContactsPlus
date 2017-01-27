package apps.zambrone.com.contactsplus;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import apps.zambrone.com.contactsplus.Fragments.Groups;
import apps.zambrone.com.contactsplus.configuration.DataStorage;

import static apps.zambrone.com.contactsplus.configuration.SharedPref.EMAIL_STATUS;
import static apps.zambrone.com.contactsplus.configuration.SharedPref.LOGIN_STATS;
import static apps.zambrone.com.contactsplus.configuration.SharedPref.PHONE_NUMBER;

public class MainActivity extends AppCompatActivity {

    Groups groupsFragment;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Intent intent = new Intent(MainActivity.this, CreateGroupActivity.class);
//        startActivity(intent);

        auth.getInstance();

        DataStorage dataStorage = new DataStorage(getApplicationContext());
        String userid = dataStorage.getString(LOGIN_STATS, null);
        String phone = dataStorage.getString(PHONE_NUMBER, null);
        String verified_stats = dataStorage.getString(EMAIL_STATUS, null);


        System.out.println("USER ID " + userid);
        if (userid == null) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            return;
        } else {

            if (verified_stats == null) {

                Toast.makeText(getApplicationContext(), "Your Email is Not Verified", Toast.LENGTH_LONG)
                        .show();

                Intent intent = new Intent(MainActivity.this, EmailVerifyActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);


                startActivity(intent);
                finish();
                return;

            }else{
                dataStorage.setString(EMAIL_STATUS,"verified");

                if (phone == null) {
                    Intent intent = new Intent(MainActivity.this, PhoneNumberActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                    return;
                }
            }



        }


    }
}
