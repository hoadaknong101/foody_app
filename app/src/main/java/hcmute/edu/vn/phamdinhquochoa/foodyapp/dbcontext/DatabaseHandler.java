package hcmute.edu.vn.phamdinhquochoa.foodyapp.dbcontext;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "foody.sqlite";
    private static final Integer DATABASE_VERSION = 1;
    private static final SQLiteDatabase.CursorFactory DATABASE_FACTORY = null;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, DATABASE_FACTORY, DATABASE_VERSION);
    }

    public void queryData(String sql) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public Cursor getData(String sql) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql, null);
    }

    public Cursor getDataRow(String sql) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        return c;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create table "Food"
        String queryCreateFood = "CREATE TABLE IF NOT EXISTS tblFood(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR(200)," +
                "type VARCHAR(200)," +
                "image BLOB," +
                "description VARCHAR(200))";
        sqLiteDatabase.execSQL(queryCreateFood);
        //Create table "FoodSize"
        String queryCreateFoodSize = "CREATE TABLE IF NOT EXISTS tblFoodSize(" +
                "food_id INTEGER," +
                "size INTEGER ," +
                "price DOUBLE," +
                "is_save BOOLEAN, PRIMARY KEY (food_id, size))";
        sqLiteDatabase.execSQL(queryCreateFoodSize);

        //Create table "OrderDetail"
        String queryCreateOrderDetail = "CREATE TABLE IF NOT EXISTS tblOrderDetail(" +
                "order_id INTEGER PRIMARY KEY," +
                "food_id INTEGER," +
                "size INTEGER," +
                "price DOUBLE)";
        sqLiteDatabase.execSQL(queryCreateOrderDetail);

        //Create table "User"
        String queryCreateUser = "CREATE TABLE IF NOT EXISTS tblUser(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR(200)," +
                "gender VARCHAR(10)," +
                "date_of_birth VARCHAR(20)," +
                "phone VARCHAR(15)," +
                "username VARCHAR(30)," +
                "password VARCHAR(100))";
        sqLiteDatabase.execSQL(queryCreateUser);

        //Create table "Order"
        String queryCreateOrder = "CREATE TABLE IF NOT EXISTS tblOrder(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_id INTEGER," +
                "address VARCHAR(200)," +
                "date_order VARCHAR(20)," +
                "total_value DOUBLE," +
                "status VARCHAR(200))";
        sqLiteDatabase.execSQL(queryCreateOrder);

        //Create table "Notify"
        String queryCreateNotify = "CREATE TABLE IF NOT EXISTS tblNotify(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title VARCHAR(200)," +
                "content VARCHAR(200)," +
                "date_make VARCHAR(20))";
        sqLiteDatabase.execSQL(queryCreateNotify);

        //Create table "NotifyToUser"
        String queryCreateNotifyToUser = "CREATE TABLE IF NOT EXISTS tblNotifyToUser(" +
                "notify_id INTEGER," +
                "user_id INTEGER," +
                "PRIMARY KEY (notify_id, user_id))";
        sqLiteDatabase.execSQL(queryCreateNotifyToUser);
        Log.i("SQLite", "DATABASE CREATED");
        addData(sqLiteDatabase);
        Log.i("SQLite", "ADDED DATA");
    }

    public void addData(SQLiteDatabase db) {

        // Add user
        db.execSQL("INSERT INTO tblUser VALUES(null,'Pham Dinh Quoc Hoa','Male','25-06-2001','0388891635','hoadaknong101','123456')");
        db.execSQL("INSERT INTO tblUser VALUES(null,'Vo Nhat Phi','Male','11-11-2001','0388891635','phivo','1234567')");
        db.execSQL("INSERT INTO tblUser VALUES(null,'Nguyen Quang Sang','Male','25-06-2001','0388891635','sangdang','1234568')");
        db.execSQL("INSERT INTO tblUser VALUES(null,'Pham Van Thang','Male','25-06-2001','0388891635','thengpham','1234569')");

        // Add food
        db.execSQL("INSERT INTO tblFood VALUES(null,'Pork Bread','Breakfast','','Description')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.i("SQLite","Upgrade SQLite");

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tblUser");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tblFood");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tblFoodSize");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tblOrderDetail");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tblOrder");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tblNotify");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tblNotifyToUser");

        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db,oldVersion,newVersion);
    }
}
