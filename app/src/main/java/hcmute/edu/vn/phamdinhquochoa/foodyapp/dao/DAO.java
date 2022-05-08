package hcmute.edu.vn.phamdinhquochoa.foodyapp.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.*;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.dbcontext.DatabaseHandler;

public class DAO {

    DatabaseHandler dbHelper;
    SQLiteDatabase db ;

    public DAO(Context context){
        dbHelper = new DatabaseHandler(context);
        db = dbHelper.getReadableDatabase();
    }

    // region Restaurant
    public Restaurant getRestaurantInformation(Integer restaurantId){
        String query = "SELECT * FROM tblRestaurant WHERE id=" + restaurantId;
        Cursor cursor = dbHelper.getDataRow(query);
        return new Restaurant(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
    }
    // endregion

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

    public ArrayList<Order> findAllOrderOfUser(Integer userId){
        ArrayList<Order> orderList = new ArrayList<>();
        String query = "SELECT * FROM tblOrder WHERE user_id=" + userId;
        Cursor cursor = dbHelper.getData(query);
        while (cursor.moveToNext()){
            orderList.add(new Order(cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getDouble(4),
                    cursor.getString(5)));
        }
        return orderList;
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
        return cursor.moveToNext();
    }

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

    public Double getFoodDefaultPrice(Integer foodId){
        String sql = "SELECT * FROM tblFoodSize WHERE food_id=" + foodId;
        Cursor cursor = dbHelper.getDataRow(sql);
        if (cursor == null)
            return 0d;
        return cursor.getDouble(2); //FoodSize.getPrice();
    }

    public ArrayList<FoodSize> getAllFoodSize(Integer foodId){
        ArrayList<FoodSize> foodSizeList = new ArrayList<>();
        String sql = "SELECT * FROM tblFoodSize WHERE food_id=" + foodId;
        Cursor cursor = dbHelper.getData(sql);
        while (cursor.moveToNext()){
            foodSizeList.add(new FoodSize(cursor.getInt(0), cursor.getInt(1), cursor.getDouble(2)));
        }
        return foodSizeList;
    }

    public ArrayList<Food> getFoodByKeyWord(String keyword){
        ArrayList<Food> listFood = new ArrayList<>();
        String query = "SELECT * FROM tblFood WHERE name LIKE '%" + keyword + "%'";
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

    public ArrayList<Food> getFoodByType(String type){
        ArrayList<Food> listFood = new ArrayList<>();
        String query = "SELECT * FROM tblFood WHERE type='" + type + "'";
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
