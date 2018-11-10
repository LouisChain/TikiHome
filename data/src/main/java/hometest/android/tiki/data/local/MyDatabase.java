package hometest.android.tiki.data.local;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = MyDatabase.NAME, version = MyDatabase.VERSION)
public class MyDatabase {

    public static final String NAME = "MY_DB";

    public static final int VERSION = 1;
}
