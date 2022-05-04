package hcmute.edu.vn.phamdinhquochoa.foodyapp.dbcontext;

import static java.lang.String.format;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.phamdinhquochoa.foodyapp.R;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.*;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "foody.sqlite";
    private static final Integer DATABASE_VERSION = 1;
    private static final SQLiteDatabase.CursorFactory DATABASE_FACTORY = null;
    private Context context;

    private List<User> userList;
    private List<Food> foodList;
    private List<FoodSize> foodSizeList;
    private List<Notify> notifyList;
    private List<NotifyToUser> notifyToUsers;
    private List<Order> orderList;
    private List<OrderDetail> orderDetailList;
    private List<FoodSaved> foodSavedList;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, DATABASE_FACTORY, DATABASE_VERSION);
        this.context = context;
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

    public byte[] convertDrawableToByteArray(Drawable drawable){
        // Convert khi đúng cấu trúc bitmap
        if (drawable instanceof BitmapDrawable) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            return stream.toByteArray();
        }

        // Không cùng cấu trúc bitmap
        final int width = !drawable.getBounds().isEmpty() ? drawable
                .getBounds().width() : drawable.getIntrinsicWidth();

        final int height = !drawable.getBounds().isEmpty() ? drawable
                .getBounds().height() : drawable.getIntrinsicHeight();

        final Bitmap bitmap = Bitmap.createBitmap(width <= 0 ? 1 : width,
                height <= 0 ? 1 : height, Bitmap.Config.ARGB_8888);

        // Vẽ hình
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        // Chuyển kiểu
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    public Bitmap convertByteArrayToBitmap(byte[] image){
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        return bitmap;
    }

    public void SampleData(){
        userList = new ArrayList<>();
        userList.add(new User(1,"Phạm Đinh Quốc Hòa", "Male", "25-06-2001", "0388891635", "hoadaknong101", "123456"));
        userList.add(new User(2,"Võ Nhật Phi", "Male", "17-04-2001", "0947679750", "phivo", "1234567"));
        userList.add(new User(3,"Nguyễn Quang Sang", "Male", "25-06-2001", "0388891635", "sangdang", "1234568"));
        userList.add(new User(4,"Phạm Văn Thắng", "Male", "22-04-2001", "0388891635", "thengpham", "1234569"));

        foodList = new ArrayList<>();
        foodList.add(new Food(1, "Kem hộp đậu đỏ", "Kem",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.kemhop_daudo, null)),
                "Kem tươi mát ngon lắm bạn ơi!"));
        foodList.add(new Food(1, "Kem hộp sữa dừa", "Kem",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.kemhop_suadua, null)),
                "Kem tươi mát ngon lắm bạn ơi!"));
        foodList.add(new Food(1, "Kem ốc quế vani", "Kem",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.kemocque_vani, null)),
                "Kem tươi mát ngon lắm bạn ơi!"));
        foodList.add(new Food(1, "Kem ôc quế dâu", "Kem",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.kemocque_dau, null)),
                "Kem tươi mát ngon lắm bạn ơi!"));
        foodList.add(new Food(1, "Kem ốc quế socola", "Kem",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.kemocque_socola, null)),
                "Kem tươi mát ngon lắm bạn ơi!"));

        foodSizeList = new ArrayList<>();
        foodSizeList.add(new FoodSize(1, 1, 10000d, false));
        foodSizeList.add(new FoodSize(1, 2, 12000d, false));
        foodSizeList.add(new FoodSize(1, 3, 15000d, false));
        foodSizeList.add(new FoodSize(2, 1, 10000d, false));
        foodSizeList.add(new FoodSize(2, 2, 12000d, false));
        foodSizeList.add(new FoodSize(2, 3, 15000d, false));
        foodSizeList.add(new FoodSize(3, 1, 12000d, false));
        foodSizeList.add(new FoodSize(3, 2, 18000d, false));
        foodSizeList.add(new FoodSize(3, 3, 25000d, false));
        foodSizeList.add(new FoodSize(4, 1, 12000d, false));
        foodSizeList.add(new FoodSize(4, 2, 18000d, false));
        foodSizeList.add(new FoodSize(4, 3, 25000d, false));
        foodSizeList.add(new FoodSize(5, 1, 12000d, false));
        foodSizeList.add(new FoodSize(5, 2, 18000d, false));
        foodSizeList.add(new FoodSize(5, 3, 25000d, false));

        foodSavedList = new ArrayList<>();
        foodSavedList.add(new FoodSaved(1, 3, 1));
        foodSavedList.add(new FoodSaved(1, 3, 3));
        foodSavedList.add(new FoodSaved(3, 3, 3));
        foodSavedList.add(new FoodSaved(2, 1, 4));

        notifyList = new ArrayList<>();
        notifyList.add(new Notify(1, "Chào bạn mới!",
                "Chào mừng bạn đến với ứng dụng Foody!", "3/3/2022"));
        notifyList.add(new Notify(2, "Thông báo chung!",
                "Ứng dụng Foody dùng cho việc lựa chọn thưởng thức món ăn tại gia!", "3/3/2022"));
        notifyList.add(new Notify(3, "Bạn đi đâu đấy!",
                "Foody luôn luôn chào mừng bạn!", "3/3/2022"));

        notifyToUsers = new ArrayList<>();
        notifyToUsers.add(new NotifyToUser(3, 1));
        notifyToUsers.add(new NotifyToUser(3, 2));
        notifyToUsers.add(new NotifyToUser(3, 3));
        notifyToUsers.add(new NotifyToUser(3, 4));

        orderList = new ArrayList<>();
        orderList.add(new Order(1, 1, "Thủ Đức", "4/3/2022", 27000d, "Delivered"));
        orderList.add(new Order(2, 1, "Thủ Đức", "5/3/2022", 18000d, "Craft"));
        orderList.add(new Order(3, 3, "Quận 9", "4/3/2022", 68000d, "Coming"));

        orderDetailList = new ArrayList<>();
        orderDetailList.add(new OrderDetail(1, 1, 2, 12000d));
        orderDetailList.add(new OrderDetail(1, 2, 3, 15000d));
        orderDetailList.add(new OrderDetail(2, 3, 2, 18000d));
        orderDetailList.add(new OrderDetail(3, 3, 3, 25000d));
        orderDetailList.add(new OrderDetail(3, 4, 3, 25000d));
        orderDetailList.add(new OrderDetail(3, 5, 2, 18000d));
    }

    public void addSampleData(SQLiteDatabase db) {
        SampleData();

        // Add user
        for (User user : userList) {
            db.execSQL(format("INSERT INTO tblUser VALUES(null, '%s','%s', '%s', '%s', '%s', '%s')",
                    user.getName(), user.getGender(), user.getDateOfBirth(), user.getPhone(), user.getUsername(), user.getPassword()));
        }

//        db.execSQL("INSERT INTO tblUser VALUES(null,'Pham Dinh Quoc Hoa','Male','25-06-2001','0388891635','hoadaknong101','123456')");
//        db.execSQL("INSERT INTO tblUser VALUES(null,'Vo Nhat Phi','Male','11-11-2001','0388891635','phivo','1234567')");
//        db.execSQL("INSERT INTO tblUser VALUES(null,'Nguyen Quang Sang','Male','25-06-2001','0388891635','sangdang','1234568')");
//        db.execSQL("INSERT INTO tblUser VALUES(null,'Pham Van Thang','Male','25-06-2001','0388891635','thengpham','1234569')");

        // Add food
        for (Food food : foodList) {
            String sql = "INSERT INTO tblFood VALUES (null, ?, ?, ?, ?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.clearBindings ();
            statement.bindString (1, food.getName());
            statement.bindString (2, food.getType());
            statement.bindBlob(3, food.getImage());
            statement.bindString(4, food.getDescription());
            statement.executeInsert();
//            db.execSQL("INSERT INTO tblFood VALUES(null, '" + food.getName() + "', '"
//                    + food.getType() + "', " + null + ", '" + food.getDescription() + "')");
        }

        // Add food size
        for (FoodSize foodSize : foodSizeList) {
            db.execSQL(format("INSERT INTO tblFoodSize VALUES('%d', '%d', '%.1f')",
                    foodSize.getFoodId(), foodSize.getSize(), foodSize.getPrice()));
        }

        // Add food saved
        for (FoodSaved foodSaved : foodSavedList){
            db.execSQL(format("INSERT INTO tblFoodSaved VALUES('%d', '%d', '%d')",
                    foodSaved.getFoodId(), foodSaved.getSize(), foodSaved.getUserId()));
        }

        // Add notify
        for (Notify notify : notifyList) {
            db.execSQL(format("INSERT INTO tblNotify VALUES(null, '%s', '%s', '%s')",
                    notify.getId(), notify.getTitle(), notify.getContent(), notify.getDateMake()));
        }

        // Add notify to user
        for (NotifyToUser notifyToUser : notifyToUsers) {
            db.execSQL("INSERT INTO tblNotifyToUser VALUES('"
                    + notifyToUser.getNotifyId() + "', '"
                    + notifyToUser.getUserId() + "')");
        }

        // Add order
        for (Order order : orderList) {
            db.execSQL(format("INSERT INTO tblOrder VALUES(null, '%d', '%s', '%s', '%.1f', '%s')",
                    order.getUserId(), order.getAddress(), order.getDateOfOrder(), order.getTotalValue(), order.getStatus()));
        }

        // Add order detail
        for (OrderDetail orderDetail: orderDetailList) {
            db.execSQL("INSERT INTO tblOrderDetail VALUES(" + orderDetail.getOrderId() + ", " +
                    orderDetail.getFoodId() + ", " +
                    orderDetail.getSize() + ", " +
                    orderDetail.getPrice()+ ")");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create table "Food"
        String queryCreateFood = "CREATE TABLE IF NOT EXISTS tblFood(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name NVARCHAR(200)," +
                "type NVARCHAR(200)," +
                "image BLOB," +
                "description NVARCHAR(200))";
        sqLiteDatabase.execSQL(queryCreateFood);

        //Create table "FoodSize"
        String queryCreateFoodSize = "CREATE TABLE IF NOT EXISTS tblFoodSize(" +
                "food_id INTEGER," +
                "size INTEGER ," +
                "price DOUBLE," +
                "PRIMARY KEY (food_id, size))";
        sqLiteDatabase.execSQL(queryCreateFoodSize);

        //Create table "OrderDetail"
        String queryCreateOrderDetail = "CREATE TABLE IF NOT EXISTS tblOrderDetail(" +
                "order_id INTEGER," +
                "food_id INTEGER," +
                "size INTEGER," +
                "price DOUBLE," +
                "PRIMARY KEY (order_id, food_id, size))";
        sqLiteDatabase.execSQL(queryCreateOrderDetail);

        //Create table "User"
        String queryCreateUser = "CREATE TABLE IF NOT EXISTS tblUser(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name NVARCHAR(200)," +
                "gender VARCHAR(10)," +
                "date_of_birth VARCHAR(20)," +
                "phone VARCHAR(15)," +
                "username VARCHAR(30)," +
                "password VARCHAR(100))";
        sqLiteDatabase.execSQL(queryCreateUser);

        //Create table "FoodSaved"
        String queryCreateFoodSaved = "CREATE TABLE IF NOT EXISTS tblFoodSaved(" +
                "food_id INTEGER," +
                "size INTEGER ," +
                "user_id INTEGER," +
                "PRIMARY KEY (food_id, size, user_id))";
        sqLiteDatabase.execSQL(queryCreateFoodSaved);

        //Create table "Order"
        String queryCreateOrder = "CREATE TABLE IF NOT EXISTS tblOrder(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_id INTEGER," +
                "address NVARCHAR(200)," +
                "date_order VARCHAR(20)," +
                "total_value DOUBLE," +
                "status VARCHAR(200))";
        sqLiteDatabase.execSQL(queryCreateOrder);

        //Create table "Notify"
        String queryCreateNotify = "CREATE TABLE IF NOT EXISTS tblNotify(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title NVARCHAR(200)," +
                "content NVARCHAR(200)," +
                "date_make VARCHAR(20))";
        sqLiteDatabase.execSQL(queryCreateNotify);

        //Create table "NotifyToUser"
        String queryCreateNotifyToUser = "CREATE TABLE IF NOT EXISTS tblNotifyToUser(" +
                "notify_id INTEGER," +
                "user_id INTEGER," +
                "PRIMARY KEY (notify_id, user_id))";
        sqLiteDatabase.execSQL(queryCreateNotifyToUser);

        Log.i("SQLite", "DATABASE CREATED");
        addSampleData(sqLiteDatabase);
        Log.i("SQLite", "ADDED DATA");
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
