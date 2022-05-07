package hcmute.edu.vn.phamdinhquochoa.foodyapp.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.sql.Statement;
import java.util.ArrayList;

import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.*;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.dbcontext.DatabaseHandler;

public class DAO {

    DatabaseHandler dbHelper;
    SQLiteDatabase db ;

//    private static DAO instance;
//
//    public static DAO getInstance() {
//        return instance;
//    }

    public DAO(Context context){
        dbHelper = new DatabaseHandler(context);
        db = dbHelper.getReadableDatabase();
    }

    // region Order
    public void addOrder(Order order) {
        String query = "INSERT INTO tblOrder VALUES(null," +
                order.getUserId() + ",'" +
                order.getAddress() + "','" +
                order.getDateOfOrder() + "'," +
                order.getTotalValue() + ",'" +
                order.getStatus() + "')";
        dbHelper.queryData(query);
        System.out.println("Inserted ORDER " + order);
    }
    // endregion

    // region OrderDetail
    public void addOrderDetail(OrderDetail od) {
        String query = "INSERT INTO tblOrderDetail VALUES(" +
                od.getOrderId() + "," +
                od.getFoodId() + "," +
                od.getSize() + "," +
                od.getPrice() + ")";
        dbHelper.queryData(query);
        System.out.println("Inserted ORDER_DETAIL " + od);
    }

    public void deleteOrderDetailByOrderIdAndFoodId(Integer orderId, Integer foodId) {
        String query = "DELETE FROM tblOrderDetail WHERE food_id=" +
                foodId + " and order_id=" + orderId;
        dbHelper.queryData(query);
        System.out.println("Deleted ORDER_DETAIL");
    }
    // endregion

    // region Notify
    public void addNotify(Notify n) {
        String query = "INSERT INTO tblNofity VALUES(null,'" +
                n.getTitle() + "', '" +
                n.getContent() + "', '" +
                n.getDateMake() + "')";
        dbHelper.queryData(query);
        System.out.println("INSERTED NOTIFY " + n);
    }

    public void addNotifyToUser(NotifyToUser notifyToUser) {
        String query = "INSERT INTO tblNotifyToUser VALUES(" +
                notifyToUser.getNotifyId() + "," +
                notifyToUser.getUserId() + ")";
        dbHelper.queryData(query);
        System.out.println("NOTIFIED TO USER");
    }
    // endregion

    // region User
    public void addUser(User user) {
        String query = "INSERT INTO tblUser VALUES(null,'" +
                user.getName() + "', '" +
                user.getGender() + "', '" +
                user.getDateOfBirth() + "', '" +
                user.getPhone() + "', '" +
                user.getUsername() + "', '" +
                user.getPassword() + "')";
        dbHelper.queryData(query);
        System.out.println("INSERTED USER " + user);
    }

    public void updateUser(User user){
        String query = "UPDATE tblUser SET " +
                "name='" + user.getName() + "'," +
                "gender='" + user.getGender() + "'," +
                "date_of_birth='" + user.getDateOfBirth() + "'," +
                "phone='" + user.getPhone() + "'," +
                "password='" + user.getPassword() + "' " +
                "WHERE id=" + user.getId();
        dbHelper.queryData(query);
    }

    public User getUserById(Integer id){
        User user = new User();
        String query = "SELECT * FROM tblUser WHERE id=" + id;
        Cursor cursor = dbHelper.getData(query);

        if(cursor != null){
            cursor.moveToFirst();
            user.setId(cursor.getInt(0));
            user.setName(cursor.getString(1));
            user.setGender(cursor.getString(2));
            user.setDateOfBirth(cursor.getString(3));
            user.setPhone(cursor.getString(4));
            user.setUsername(cursor.getString(5));
            user.setPassword(cursor.getString(6));
            System.out.println("USER FOUND " + user);
            return user;
        }
        return null;
    }

    public boolean UserExited(String username) {
        String query = "SELECT * FROM tblUser WHERE username='" + username + "'";
        Cursor cursor = dbHelper.getData(query);
        System.out.println(cursor);
        return cursor.moveToNext();
    }

//    public User getUserByUsername(String username){
//        User user = new User();
//        String query = "SELECT * FROM tblUser WHERE username='" + username+"'";
//        Cursor cursor = dbHelper.getData(query);
//
//        if(cursor != null){
//            cursor.moveToFirst();
//            user.setId(cursor.getInt(0));
//            user.setName(cursor.getString(1));
//            user.setGender(cursor.getString(2));
//            user.setDateOfBirth(cursor.getString(3));
//            user.setPhone(cursor.getString(4));
//            user.setUsername(cursor.getString(5));
//            user.setPassword(cursor.getString(6));
//            System.out.println("USER FOUND " + user);
//            return user;
//        }
//        System.out.println("USER NOT FOUND!");
//        return null;
//    }
//    public boolean signUp(User user){
//        User existedUser = getUserByUsername(user.getUsername());
//        if(existedUser != null){
//            addUser(user);
//            return true;
//        }
//        System.out.println("USER EXISTED!");
//        return false;
//    }

    public User getUserByUsernameAndPassword(String username, String password) {
        User user = new User();
        String query = "SELECT * FROM tblUser WHERE username='" + username + "' and password='" + password + "'";
        Cursor cursor = dbHelper.getDataRow(query);

        if (cursor.getCount() > 0) {
            user.setId(cursor.getInt(0));
            user.setName(cursor.getString(1));
            user.setGender(cursor.getString(2));
            user.setDateOfBirth(cursor.getString(3));
            user.setPhone(cursor.getString(4));
            user.setUsername(cursor.getString(5));
            user.setPassword(cursor.getString(6));
            return user;
        }

        System.out.println("USER NOT FOUND!");
        return null;
    }

    public boolean signIn(User user){
        User existedUser = getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        return existedUser != null;
    }
    // endregion

    // region Food
    public void addFood(Food food){

    }

    public void addFoodSize(FoodSize foodSize){

    }

    public ArrayList<Food> getAllFood(){
        ArrayList<Food> listFood = new ArrayList<>();
        String query = "SELECT * FROM tblFood";
        Cursor cursor = dbHelper.getData(query);
        while(cursor.moveToNext()){
            listFood.add(new Food(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getBlob(3),
                    cursor.getString(4),
                    cursor.getInt(5))
            );
        }
        return listFood;
    }
    // endregion
}
