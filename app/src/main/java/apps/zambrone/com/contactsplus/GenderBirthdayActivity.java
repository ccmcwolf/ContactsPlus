package apps.zambrone.com.contactsplus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.google.gson.Gson;

import apps.zambrone.com.contactsplus.model.User;

public class GenderBirthdayActivity extends AppCompatActivity {

    private static final String MY_PREFS_NAME = "USER_DETAIL";
    private RadioGroup radioGenderGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender_birthday);

        Intent intent = getIntent();
        String struser = intent.getStringExtra("user");
        Gson gson = new Gson();
        User user =  gson.fromJson(struser, User.class);

        radioGenderGroup = (RadioGroup) findViewById(R.id.radiogender);


    }
}
