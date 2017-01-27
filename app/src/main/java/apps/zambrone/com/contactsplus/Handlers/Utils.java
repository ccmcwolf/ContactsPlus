package apps.zambrone.com.contactsplus.Handlers;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Chamith on 11/11/2016.
 */public class Utils {
    private static FirebaseDatabase mDatabase;

    public static FirebaseDatabase getDatabase() {
        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance();
            mDatabase.setPersistenceEnabled(true);
        }
        return mDatabase;
    }

}
