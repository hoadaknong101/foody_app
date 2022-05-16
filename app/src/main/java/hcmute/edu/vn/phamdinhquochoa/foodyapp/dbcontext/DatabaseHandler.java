package hcmute.edu.vn.phamdinhquochoa.foodyapp.dbcontext;

import static java.lang.String.format;

import android.annotation.SuppressLint;
import android.content.Context;
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
import java.util.Random;

import hcmute.edu.vn.phamdinhquochoa.foodyapp.R;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.*;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "foody.sqlite";
    private static final Integer DATABASE_VERSION = 1;
    private static final SQLiteDatabase.CursorFactory DATABASE_FACTORY = null;
    private final Context context;

    // region List Sample Data
    private List<User> userList;
    private List<Restaurant> restaurantList;
    private List<RestaurantSaved> restaurantSavedList;
    private List<Food> foodList;
    private List<FoodSize> foodSizeList;
    private List<Notify> notifyList;
    private List<NotifyToUser> notifyToUsers;
    private List<Order> orderList;
    private List<OrderDetail> orderDetailList;
    private List<FoodSaved> foodSavedList;
    // endregion

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

    // region Convert Image
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

    public static Bitmap convertByteArrayToBitmap(byte[] image){
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
    // endregion

    private void SampleData(){
        // region User
        userList = new ArrayList<>();
        userList.add(new User(1,"Phạm Đinh Quốc Hòa", "Male", "25-06-2001", "0388891635", "hoadaknong101", "123456"));
        userList.add(new User(2,"Võ Nhật Phi", "Male", "17-04-2001", "0947679750", "phivo", "1234567"));
        userList.add(new User(3,"Nguyễn Quang Sang", "Male", "25-06-2001", "0388891635", "sangdang", "1234568"));
        userList.add(new User(4,"Phạm Văn Thắng", "Male", "22-04-2001", "0388891635", "thengpham", "1234569"));
        // endregion

        // region Restaurant
        restaurantList = new ArrayList<>();
        restaurantList.add(new Restaurant(1, "Quán bánh mì cô Ba", "hẻm 68 Bùi Thị Xuân, quận Tân Bình, Thành phố Hồ Chí Minh",
                "0631335935", convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.quan_banh_mi_co_ba, null))));
        restaurantList.add(new Restaurant(2, "Quán trà sữa Coffee House", "số 11 Võ Văn Ngân, Thành phố Thủ Đức",
                "0885438847", convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.quan_tra_sua_coffee_house, null))));
        restaurantList.add(new Restaurant(3, "Quán cơm tấm Phúc Mạp", "khu phố 6, quận 5, Thành phố Hồ Chí Minh",
                "0559996574", convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.quan_com_tam_phuc_map, null))));
        restaurantList.add(new Restaurant(4, "Quán bánh ngọt Lê Văn Việt", "ngã ba Tiền Đô",
                "0141670738", convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.quan_banh_ngot_le_van_viet, null))));
        restaurantList.add(new Restaurant(5, "Quán kem cô Hai", "Dưới chân cầu vượt Linh Xuân, Thủ Đức",
                "0627724695", convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.quan_kem_co_hai, null))));
        restaurantList.add(new Restaurant(6, "Quán phở gia truyền", "Bên hông viện Sư phạm kỹ thuật, Quận 9",
                "0925063881", convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.quan_pho_gia_truyen, null))));
        // endregion

        // region Restaurant saved
        restaurantSavedList = new ArrayList<>();
        restaurantSavedList.add(new RestaurantSaved(1, 3));
        restaurantSavedList.add(new RestaurantSaved(4, 3));
        restaurantSavedList.add(new RestaurantSaved(1, 1));
        restaurantSavedList.add(new RestaurantSaved(1, 2));
        restaurantSavedList.add(new RestaurantSaved(2, 2));
        restaurantSavedList.add(new RestaurantSaved(6, 3));
        // endregion

        // region Food
        foodList = new ArrayList<>();
        // region Kem
        foodList.add(new Food(1, "Kem hộp đậu đỏ", "Kem",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.kemhop_daudo, null)),
                "Kem tươi mát ngon lắm bạn ơi!", 5));
        foodList.add(new Food(1, "Kem hộp sữa dừa", "Kem",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.kemhop_suadua, null)),
                "Kem tươi mát ngon lắm bạn ơi!", 5));
        foodList.add(new Food(1, "Kem ốc quế vani", "Kem",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.kemocque_vani, null)),
                "Kem tươi mát ngon lắm bạn ơi!", 5));
        foodList.add(new Food(1, "Kem ôc quế dâu", "Kem",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.kemocque_dau, null)),
                "Kem tươi mát ngon lắm bạn ơi!", 5));
        foodList.add(new Food(1, "Kem ốc quế socola", "Kem",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.kemocque_socola, null)),
                "Kem tươi mát ngon lắm bạn ơi!", 5));
        foodList.add(new Food(1, "Kem ôc quế dâu", "Kem",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.kemocque_dau, null)),
                "Kem tươi mát ngon lắm bạn ơi!", 4));
        foodList.add(new Food(1, "Kem ốc quế socola", "Kem",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.kemocque_socola, null)),
                "Kem tươi mát ngon lắm bạn ơi!", 4));
        // endregion
        // region Banh mi
        foodList.add(new Food(1, "Bánh mì bò kho", "Bánh mì",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.banhmi_bokho, null)),
                "Bữa ăn đơn giản cho người đơn giản!", 1));
        foodList.add(new Food(1, "Bánh mì bơ tỏi", "Bánh mì",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.banhmi_botoi, null)),
                "Bữa ăn đơn giản cho người đơn giản!", 1));
        foodList.add(new Food(1, "Bánh mì chảo", "Bánh mì",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.banhmi_chao, null)),
                "Bữa ăn đơn giản cho người đơn giản!", 1));
        foodList.add(new Food(1, "Bánh mì hoa cúc", "Bánh mì",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.banhmi_hoacuc, null)),
                "Bữa ăn đơn giản cho người đơn giản!", 3));
        foodList.add(new Food(1, "Bánh mì hoa cúc", "Bánh mì",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.banhmi_hoacuc, null)),
                "Bữa ăn đơn giản cho người đơn giản!", 4));
        foodList.add(new Food(1, "Bánh mì kẹp thịt", "Bánh mì",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.banhmi_kepthit, null)),
                "Bữa ăn đơn giản cho người đơn giản!", 1));
        foodList.add(new Food(1, "Bánh mì kẹp xúc xích", "Bánh mì",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.banhmi_kepxucxich, null)),
                "Bữa ăn đơn giản cho người đơn giản!", 1));
        foodList.add(new Food(1, "Hamburger bò", "Bánh mì",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.burger_bo, null)),
                "Bữa ăn đơn giản cho người đơn giản!", 1));
        foodList.add(new Food(1, "Hamburger heo", "Bánh mì",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.burger_heo, null)),
                "Bữa ăn đơn giản cho người đơn giản!", 1));
        foodList.add(new Food(1, "Hamburger bò", "Bánh mì",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.burger_bo, null)),
                "Bữa ăn đơn giản cho người đơn giản!", 6));
        foodList.add(new Food(1, "Hamburger heo", "Bánh mì",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.burger_heo, null)),
                "Bữa ăn đơn giản cho người đơn giản!", 6));
        foodList.add(new Food(1, "Hamburger gà", "Bánh mì",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.burger_ga, null)),
                "Bữa ăn đơn giản cho người đơn giản!", 5));
        // endregion
        // region Banh ngot
        foodList.add(new Food(1, "Bánh đậu xanh cốt dừa", "Bánh ngọt",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.banhdauxanh_cotdua, null)),
                "Cảm nhận sự ngọt ngào của những chiếc bánh ngon!", 4));
        foodList.add(new Food(1, "Bánh matcha", "Bánh ngọt",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.banh_matcha, null)),
                "Cảm nhận sự ngọt ngào của những chiếc bánh ngon!", 4));
        foodList.add(new Food(1, "Bánh sầu riêng", "Bánh ngọt",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.banh_saurieng, null)),
                "Cảm nhận sự ngọt ngào của những chiếc bánh ngon!", 4));
        foodList.add(new Food(1, "Bánh bông lan cuộn", "Bánh ngọt",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.banhbonglan_cuon, null)),
                "Cảm nhận sự ngọt ngào của những chiếc bánh ngon!", 4));
        foodList.add(new Food(1, "Bánh sầu riêng", "Bánh ngọt",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.banh_saurieng, null)),
                "Cảm nhận sự ngọt ngào của những chiếc bánh ngon!", 2));
        foodList.add(new Food(1, "Bánh bông lan cuộn", "Bánh ngọt",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.banhbonglan_cuon, null)),
                "Cảm nhận sự ngọt ngào của những chiếc bánh ngon!", 2));
        foodList.add(new Food(1, "Bánh bông lan socola", "Bánh ngọt",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.banhbonglan_socola, null)),
                "Cảm nhận sự ngọt ngào của những chiếc bánh ngon!", 4));
        foodList.add(new Food(1, "Bánh bông lan trứng muối", "Bánh ngọt",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.banhbonglan_trungmuoi, null)),
                "Cảm nhận sự ngọt ngào của những chiếc bánh ngon!", 4));
        foodList.add(new Food(1, "Bánh bông lan trứng muối", "Bánh ngọt",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.banhbonglan_trungmuoi, null)),
                "Cảm nhận sự ngọt ngào của những chiếc bánh ngon!", 1));
        foodList.add(new Food(1, "Bánh su kem", "Bánh ngọt",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.banhsukem, null)),
                "Cảm nhận sự ngọt ngào của những chiếc bánh ngon!", 4));
        // endregion
        // region Com suon
        foodList.add(new Food(1, "Cơm sườn trứng", "Cơm sườn",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.comsuon_trung, null)),
                "Món ăn bình dân truyền thống nhưng không thể thiếu hằng ngày!", 3));
        foodList.add(new Food(1, "Cơm sườn bì chả", "Cơm sườn",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.comsuon_bi_cha, null)),
                "Món ăn bình dân truyền thống nhưng không thể thiếu hằng ngày!", 3));
        foodList.add(new Food(1, "Cơm sườn nướng", "Cơm sườn",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.comsuon_nuong, null)),
                "Món ăn bình dân truyền thống nhưng không thể thiếu hằng ngày!", 3));
        foodList.add(new Food(1, "Cơm sườn ram", "Cơm sườn",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.comsuon_ram, null)),
                "Món ăn bình dân truyền thống nhưng không thể thiếu hằng ngày!", 3));
        foodList.add(new Food(1, "Cơm sườn bì chả", "Cơm sườn",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.comsuon_bi_cha, null)),
                "Món ăn bình dân truyền thống nhưng không thể thiếu hằng ngày!", 6));
        foodList.add(new Food(1, "Cơm sườn nướng", "Cơm sườn",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.comsuon_nuong, null)),
                "Món ăn bình dân truyền thống nhưng không thể thiếu hằng ngày!", 6));
        foodList.add(new Food(1, "Cơm sườn ram", "Cơm sườn",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.comsuon_ram, null)),
                "Món ăn bình dân truyền thống nhưng không thể thiếu hằng ngày!", 6));
        foodList.add(new Food(1, "Cơm sườn xào chua ngọt", "Cơm sườn",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.comsuon_chuangot, null)),
                "Món ăn bình dân truyền thống nhưng không thể thiếu hằng ngày!", 3));
        // endregion
        // region Mon nuoc
        foodList.add(new Food(1, "Bánh canh", "Món nước",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.banhcanh, null)),
                "Thức ăn mang hương vị truyền thống của những làng quê!", 6));
        foodList.add(new Food(1, "Bún mắm", "Món nước",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.bunmam, null)),
                "Vừa lạ vừa quen!", 6));
        foodList.add(new Food(1, "Bún thái", "Món nước",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.bun_thai, null)),
                "Chua chua, cay cay, ngon ngon, không thể cưỡng lại!", 6));
        foodList.add(new Food(1, "Hoành thánh", "Món nước",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.hoanhthanh, null)),
                "Cảm nhận sự thơm ngon bên trong cùng sự dẻo dai của lớp vỏ!", 6));
        foodList.add(new Food(1, "Hủ tiếu bò kho", "Món nước",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.hutieu_bokho, null)),
                "Hủ tiếu truyền thống kết hợp với sự cay nồng của món bò kho, bùng cháy vị giác!", 1));
        foodList.add(new Food(1, "Hủ tiếu nam vang", "Món nước",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.hutieu_namvang, null)),
                "Món ăn đặc trưng của người Việt!", 6));
        foodList.add(new Food(1, "Bún thái", "Món nước",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.bun_thai, null)),
                "Chua chua, cay cay, ngon ngon, không thể cưỡng lại!", 6));
        foodList.add(new Food(1, "Hoành thánh", "Món nước",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.hoanhthanh, null)),
                "Cảm nhận sự thơm ngon bên trong cùng sự dẻo dai của lớp vỏ!", 3));
        foodList.add(new Food(1, "Hủ tiếu bò kho", "Món nước",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.hutieu_bokho, null)),
                "Hủ tiếu truyền thống kết hợp với sự cay nồng của món bò kho, bùng cháy vị giác!", 3));
        foodList.add(new Food(1, "Hủ tiếu nam vang", "Món nước",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.hutieu_namvang, null)),
                "Món ăn đặc trưng của người Việt!", 3));
        foodList.add(new Food(1, "Mì vằn thắn", "Món nước",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.mi_vanthan, null)),
                "Phong cách ăn mì của người sành ăn!", 6));
        foodList.add(new Food(1, "Mì xá xíu", "Món nước",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.mi_xaxiu, null)),
                "Món ăn vừa ngon vừa lạ miệng!", 6));
        foodList.add(new Food(1, "Nui", "Món nước",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.nui, null)),
                "Từng cọng nui to vàng đang ngập tràn trong nước súp nóng hổi!", 6));
        foodList.add(new Food(1, "Phở bò", "Món nước",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.phobo, null)),
                "Nước súp đậm đà, trứ danh món Việt!", 6));
        // endregion
        // region Tra sua
        foodList.add(new Food(1, "Trà sữa dâu", "Trà sữa",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.trasua_dau, null)),
                "Uống đi, khum mặp đâu!", 2));
        foodList.add(new Food(1, "Trà sữa matcha", "Trà sữa",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.trasua_matcha, null)),
                "Uống đi, khum mặp đâu!", 2));
        foodList.add(new Food(1, "Trà sữa truyền thống", "Trà sữa",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.trasua_truyenthong, null)),
                "Uống đi, khum mặp đâu!", 2));
        foodList.add(new Food(1, "Trà sữa xoài", "Trà sữa",
                convertDrawableToByteArray(ResourcesCompat.getDrawable(context.getResources(), R.drawable.trasua_xoai, null)),
                "Uống đi, khum mặp đâu!", 2));
        // endregion
        // endregion

        // region foodSize
        foodSizeList = new ArrayList<>();
        Random random = new Random();
        for(int i = 1; i<= 55; i++){
            foodSizeList.add(new FoodSize(i, 1, (random.nextInt(20) + 1) * 1000d));
            foodSizeList.add(new FoodSize(i, 2, (random.nextInt(20) + 21) * 1000d));
            foodSizeList.add(new FoodSize(i, 3, (random.nextInt(20) + 41) * 1000d));
        }
        // endregion

        // region foodSaved
        foodSavedList = new ArrayList<>();
        foodSavedList.add(new FoodSaved(1, 3, 1));
        foodSavedList.add(new FoodSaved(36, 3, 2));
        foodSavedList.add(new FoodSaved(3, 3, 2));
        foodSavedList.add(new FoodSaved(42, 3, 2));
        foodSavedList.add(new FoodSaved(11, 3, 1));
        foodSavedList.add(new FoodSaved(28, 1, 4));
        foodSavedList.add(new FoodSaved(40, 3, 3));
        foodSavedList.add(new FoodSaved(3, 3, 3));
        foodSavedList.add(new FoodSaved(42, 3, 3));
        foodSavedList.add(new FoodSaved(31, 3, 3));
        foodSavedList.add(new FoodSaved(20, 1, 4));
        // endregion

        // region notify
        notifyList = new ArrayList<>();
        notifyList.add(new Notify(1, "Chào bạn mới!",
                "Chào mừng bạn đến với ứng dụng Foody!", "3/3/2022"));
        notifyList.add(new Notify(2, "Thông báo chung!",
                "Ứng dụng Foody dùng cho việc lựa chọn thưởng thức món ăn tại gia!", "3/3/2022"));
        notifyList.add(new Notify(3, "Bạn đi đâu đấy!",
                "Foody luôn luôn chào mừng bạn!", "3/3/2022"));
        // endregion

        // region notify to user
        notifyToUsers = new ArrayList<>();
        notifyToUsers.add(new NotifyToUser(3, 1));
        notifyToUsers.add(new NotifyToUser(3, 2));
        notifyToUsers.add(new NotifyToUser(3, 3));
        notifyToUsers.add(new NotifyToUser(3, 4));
        // endregion

        // region Order
        orderList = new ArrayList<>();
        orderList.add(new Order(1, 1, "Thủ Đức", "4/3/2022", 0d, "Delivered"));
        orderList.add(new Order(2, 1, "Thủ Đức", "5/3/2022", 0d, "Craft"));
        orderList.add(new Order(3, 3, "Quận 9", "4/3/2022", 0d, "Coming"));
        orderList.add(new Order(4, 4, "Thủ Đức", "5/4/2022", 0d, "Craft"));
        orderList.add(new Order(5, 1, "Quận 2", "4/5/2022", 0d, "Coming"));
        // endregion

        // region Order detail
        orderDetailList = new ArrayList<>();
        orderDetailList.add(new OrderDetail(1, 1, 2, 12000d, 1));
        orderDetailList.add(new OrderDetail(1, 2, 3, 15000d, 2));
        orderDetailList.add(new OrderDetail(2, 31, 2, 18000d, 1));
        orderDetailList.add(new OrderDetail(3, 3, 3, 25000d, 3));
        orderDetailList.add(new OrderDetail(3, 4, 3, 25000d, 1));
        orderDetailList.add(new OrderDetail(3, 25, 2, 18000d, 1));
        orderDetailList.add(new OrderDetail(4, 23, 2, 18000d, 2));
        orderDetailList.add(new OrderDetail(4, 32, 3, 25000d, 3));
        orderDetailList.add(new OrderDetail(5, 11, 2, 12000d, 1));
        orderDetailList.add(new OrderDetail(5, 17, 3, 15000d, 2));
        orderDetailList.add(new OrderDetail(5, 31, 2, 18000d, 1));
        orderDetailList.add(new OrderDetail(5, 33, 3, 25000d, 3));
        orderDetailList.add(new OrderDetail(5, 41, 3, 25000d, 1));
        // endregion
    }

    private void addSampleData(SQLiteDatabase db) {
        SampleData();

        // Add user
        for (User user : userList) {
            db.execSQL(format("INSERT INTO tblUser VALUES(null, '%s','%s', '%s', '%s', '%s', '%s')",
                    user.getName(), user.getGender(), user.getDateOfBirth(), user.getPhone(), user.getUsername(), user.getPassword()));
        }

        // Add restaurant
        for (Restaurant restaurant : restaurantList) {
            String sql = "INSERT INTO tblRestaurant VALUES(null, ?, ?, ?, ?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.clearBindings();
            statement.bindString(1, restaurant.getName());
            statement.bindString(2, restaurant.getAddress());
            statement.bindString(3, restaurant.getPhone());
            statement.bindBlob(4, restaurant.getImage());
            statement.executeInsert();
        }

        // Add restaurant saved
        for(RestaurantSaved restaurantSaved: restaurantSavedList){
            db.execSQL("INSERT INTO tblRestaurantSaved VALUES(" + restaurantSaved.getRestaurantId() + ", " + restaurantSaved.getUserId() + ")");
        }

        // Add food
        for (Food food : foodList) {
            String sql = "INSERT INTO tblFood VALUES (null, ?, ?, ?, ?, ?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.clearBindings();
            statement.bindString(1, food.getName());
            statement.bindString(2, food.getType());
            statement.bindBlob(3, food.getImage());
            statement.bindString(4, food.getDescription());
            statement.bindLong(5, food.getRestaurantId());
            statement.executeInsert();
        }

        // Add food size
        for (FoodSize foodSize : foodSizeList) {
            String sql = "INSERT INTO tblFoodSize VALUES(?, ?, ?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.clearBindings();
            statement.bindLong(1, foodSize.getFoodId());
            statement.bindLong(2, foodSize.getSize());
            statement.bindDouble(3, foodSize.getPrice());
            statement.executeInsert();
        }

        // Add food saved
        for (FoodSaved foodSaved : foodSavedList) {
            String sql = "INSERT INTO tblFoodSaved VALUES(?, ?, ?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.clearBindings();
            statement.bindLong(1, foodSaved.getFoodId());
            statement.bindLong(2, foodSaved.getSize());
            statement.bindLong(3, foodSaved.getUserId());
            statement.executeInsert();
        }

        // Add notify
        for (Notify notify : notifyList) {
            db.execSQL(format("INSERT INTO tblNotify VALUES(null, '%s', '%s', '%s')",
                    notify.getTitle(), notify.getContent(), notify.getDateMake()));
        }

        // Add notify to user
        for (NotifyToUser notifyToUser : notifyToUsers) {
            db.execSQL("INSERT INTO tblNotifyToUser VALUES('"
                    + notifyToUser.getNotifyId() + "', '"
                    + notifyToUser.getUserId() + "')");
        }

        // Add order
        for (Order order : orderList) {
            String sql = "INSERT INTO tblOrder VALUES(null, ?, ?, ?, ?, ?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.clearBindings();
            statement.bindLong(1, order.getUserId());
            statement.bindString(2, order.getAddress());
            statement.bindString(3, order.getDateOfOrder());
            statement.bindDouble(4, order.getTotalValue());
            statement.bindString(5, order.getStatus());
            statement.executeInsert();
        }

        // Add order detail
        for (OrderDetail orderDetail : orderDetailList) {
            db.execSQL("INSERT INTO tblOrderDetail VALUES(" + orderDetail.getOrderId() + ", " +
                    orderDetail.getFoodId() + ", " +
                    orderDetail.getSize() + ", " +
                    orderDetail.getPrice() + ", " +
                    orderDetail.getQuantity() + ")");
        }

        // Update order price
        String queryGetTotal;
        for (Order order : orderList) {
            queryGetTotal = "SELECT SUM(price * quantity) FROM tblOrderDetail WHERE order_id=" + order.getId();
            @SuppressLint("Recycle")
            Cursor cursor = db.rawQuery(queryGetTotal, null);
            cursor.moveToFirst();

            String sql = "UPDATE tblOrder SET total_value=" + cursor.getDouble(0) + " WHERE id=" + order.getId();
            db.execSQL(sql);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
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

        //Create table "Restaurant"
        String queryCreateRestaurant = "CREATE TABLE IF NOT EXISTS tblRestaurant(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name NVARCHAR(200), " +
                "address NVARCHAR(200)," +
                "phone CHAR(10)," +
                "image BLOB)";
        sqLiteDatabase.execSQL(queryCreateRestaurant);

        //Create table "RestaurantSaved"
        String queryCreateRestaurantSaved = "CREATE TABLE IF NOT EXISTS tblRestaurantSaved(" +
                "restaurant_id INTEGER, user_id INTEGER," +
                "PRIMARY KEY(restaurant_id, user_id))";
        sqLiteDatabase.execSQL(queryCreateRestaurantSaved);

        //Create table "Food"
        String queryCreateFood = "CREATE TABLE IF NOT EXISTS tblFood(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name NVARCHAR(200)," +
                "type NVARCHAR(200)," +
                "image BLOB," +
                "description NVARCHAR(200)," +
                "restaurant_id INTEGER)";
        sqLiteDatabase.execSQL(queryCreateFood);

        //Create table "FoodSize"
        String queryCreateFoodSize = "CREATE TABLE IF NOT EXISTS tblFoodSize(" +
                "food_id INTEGER," +
                "size INTEGER ," +
                "price DOUBLE," +
                "PRIMARY KEY (food_id, size))";
        sqLiteDatabase.execSQL(queryCreateFoodSize);

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

        //Create table "OrderDetail"
        String queryCreateOrderDetail = "CREATE TABLE IF NOT EXISTS tblOrderDetail(" +
                "order_id INTEGER," +
                "food_id INTEGER," +
                "size INTEGER," +
                "price DOUBLE," +
                "quantity INTEGER," +
                "PRIMARY KEY (order_id, food_id, size))";
        sqLiteDatabase.execSQL(queryCreateOrderDetail);

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

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tblNotifyToUser");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tblNotify");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tblFoodSaved");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tblFoodSize");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tblFood");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tblOrderDetail");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tblOrder");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tblRestaurantSaved");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tblRestaurant");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tblUser");

        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db,oldVersion,newVersion);
    }
}
