package oneclick.yonclick.dataa.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = DatabaseHelper.class.getName();
    // Database Version
    public static final int DATABASE_VERSION = 1;

    // Database Name
    public static final String DATABASE_NAME = "yonclickDB";


    public static final String TABLE_CART = "table_cart";


    // Common column names
    public static final String KEY_ID              = "id";
    public static final String KEY_PRODUCT_ID      = "product_id";
    public static final String KEY_NAME            = "name";
    public static final String KEY_IMAGES          = "images";
    public static final String KEY_PRICE           = "price";
    public static final String KEY_IS_SELECTED     = "is_selected";
    public static final String KEY_QUANTITY        = "quantity";

    // CART Table - column name
    public static final String KEY_ATTRIBUTE       = "attribute";


    // CART table create statement
    private static final String CREATE_TABLE_CART = "CREATE TABLE "+ TABLE_CART +
            "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_PRODUCT_ID + " INTEGER,"
                + KEY_NAME + " TEXT,"
                + KEY_IMAGES + " TEXT,"
                + KEY_PRICE + " REAL,"
                + KEY_QUANTITY + " INTEGER,"
                + KEY_ATTRIBUTE + " TEXT,"
                + KEY_IS_SELECTED + " INTEGER)";


    private static DatabaseHelper dbHelper = null;


    public static DatabaseHelper getInstance(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        if(dbHelper == null) {
            dbHelper =  new DatabaseHelper(context,name,factory,version);
        }
        return dbHelper;
    }

  public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_CART);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        // create new tables
        onCreate(db);
    }
}
