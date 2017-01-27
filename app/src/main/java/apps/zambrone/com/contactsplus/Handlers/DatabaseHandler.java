package apps.zambrone.com.contactsplus.Handlers;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;

import apps.zambrone.com.contactsplus.model.Group;
import apps.zambrone.com.contactsplus.model.UsersGroups;

/**
 * Created by Chamith on 31/12/2016.
 */
public class DatabaseHandler {

    private static FirebaseDatabase mDatabase;
    private FirebaseAuth auth;
    private DatabaseReference dbReference;

    public static FirebaseDatabase getDatabase() {
        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance();
            mDatabase.setPersistenceEnabled(true);
        }
        return mDatabase;
    }


    public ArrayList<Group> getAllGroups() {
        ArrayList<Group> arr = new ArrayList();


        auth = FirebaseAuth.getInstance();
        dbReference = mDatabase.getReference("groups");
        final ArrayList<Group> groupList = new ArrayList<Group>();
        final FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            dbReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    Log.e("Count ", "" + snapshot.getChildrenCount());
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        Group group = postSnapshot.getValue(Group.class);
                        groupList.add(group);

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }


            });

        }
        return groupList;
    }

}
