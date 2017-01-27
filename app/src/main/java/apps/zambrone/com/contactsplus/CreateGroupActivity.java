package apps.zambrone.com.contactsplus;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

import apps.zambrone.com.contactsplus.model.Group;

public class CreateGroupActivity extends AppCompatActivity {

    private EditText etgroupName;
    private Bitmap groupImage;
    private EditText et_passcode;
    private RadioGroup radioGroup_type;
    private RadioButton radio_private;
    private RadioButton radio_public;
    private Button btnCreateGroup;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference dbReference;
    private long cursysmilies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cursysmilies = (System.currentTimeMillis() / 1000);
        setContentView(R.layout.activity_create_group);

        etgroupName = (EditText) findViewById(R.id.activity_create_group_et_group_name);
        et_passcode = (EditText) findViewById(R.id.activity_create_group_passcode);
        radio_private = (RadioButton) findViewById(R.id.activity_create_group_radiobtn_private);
        radio_public = (RadioButton) findViewById(R.id.activity_create_group_radiobtn_public);
        radioGroup_type = (RadioGroup) findViewById(R.id.activity_create_group_grouptype);
        final Random r = new Random(Integer.SIZE - 1);
        int code = r.nextInt();
        et_passcode.setText(code + "");

        radioGroup_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int code = r.nextInt();

                if (checkedId == radio_private.getId()) {
                    et_passcode.setText(code + "");
                    et_passcode.setVisibility(View.VISIBLE);
//                    Random r = new Random(100);
//                    int code = r.nextInt();

                    Toast.makeText(CreateGroupActivity.this, "private"+code, Toast.LENGTH_SHORT).show();
                }
                if (checkedId == radio_public.getId()) {
                    et_passcode.setVisibility(View.GONE);
                    Toast.makeText(CreateGroupActivity.this, "Public", Toast.LENGTH_SHORT).show();
                }


            }


        });

        btnCreateGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = auth.getCurrentUser();

                if (user != null) {
                    Group group = new Group();

                    group.setGroupName(etgroupName.getText().toString());
                    group.setPassCode(et_passcode.getText().toString());
                    group.setAdminId(user.getUid());
                    dbReference = database.getReference("groups").child((cursysmilies) + "");

                    dbReference.setValue(group);
                    Toast.makeText(CreateGroupActivity.this, "Group registered", Toast.LENGTH_LONG).show();
                } else {

                    Toast.makeText(CreateGroupActivity.this, "Not logged in", Toast.LENGTH_LONG).show();
                }
            }
        });


    }




}
