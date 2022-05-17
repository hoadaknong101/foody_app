package hcmute.edu.vn.phamdinhquochoa.foodyapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.*;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.dao.DAO;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.dbcontext.DatabaseHandler;

public class FoodDetailsActivity extends AppCompatActivity {
    private ImageView image;
    private LinearLayout layout_sizeS, layout_sizeM, layout_sizeL;
    private TextView tvName, tvDescription, tvPrice,
            tvRestaurantName, tvRestaurantAddress,
            tvPriceSizeS,tvPriceSizeM, tvPriceSizeL,
            tvQuantity;

    public static Integer userID;
    private static int quantity;
    public static FoodSize foodSize;
    private DAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        quantity = 1;

        referenceComponent();
        dao = new DAO(this);
        LoadData();
    }

    private String getRoundPrice(Double price){
        return Math.round(price) + " VNĐ";
    }

    private String getTotalPrice() { return Math.round(foodSize.getPrice() * quantity) + " VNĐ"; }

    private void referenceComponent(){
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(view -> this.finish());

        tvName = findViewById(R.id.tvFoodName);
        tvDescription = findViewById(R.id.tvDescription);
        tvPrice = findViewById(R.id.tvPrice);
        image = findViewById(R.id.image);

        layout_sizeS = findViewById(R.id.layout_size_S);
        layout_sizeM = findViewById(R.id.layout_size_M);
        layout_sizeL = findViewById(R.id.layout_size_L);

        tvPriceSizeS = findViewById(R.id.tvPriceSizeS);
        tvPriceSizeM = findViewById(R.id.tvPriceSizeM);
        tvPriceSizeL = findViewById(R.id.tvPriceSizeL);

        tvQuantity = findViewById(R.id.tvFoodQuantity_Food);

        tvRestaurantName = findViewById(R.id.tvRestaurantName);
        tvRestaurantAddress = findViewById(R.id.tvRestaurantAddress);

        Button btnAddToCart = findViewById(R.id.btnAddToCart);
        btnAddToCart.setOnClickListener(view -> {
            // Make cart if don't have
            Cursor cursor = dao.getCart(userID);
            if (!cursor.moveToFirst()){
                dao.addOrder(new Order(1, userID, "", "", 0d, "Craft"));
                cursor = dao.getCart(userID);
            }

            // add order detail
            cursor.moveToFirst();

            OrderDetail orderDetail = dao.getExistOrderDetail(cursor.getInt(0), foodSize);
            if(orderDetail != null) {
                orderDetail.setQuantity(orderDetail.getQuantity() + quantity);
                if(dao.updateQuantity(orderDetail)){
                    Toast.makeText(this, "Thêm món ăn vào giỏ hàng thành công!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
                }

            } else {
                boolean addOrderDetail = dao.addOrderDetail(new OrderDetail(cursor.getInt(0),
                        foodSize.getFoodId(), foodSize.getSize(), foodSize.getPrice(), quantity));

                if(addOrderDetail){
                    Toast.makeText(this, "Thêm món ăn vào giỏ hàng thành công!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button btnSavedFood = findViewById(R.id.btnSavedFood);
        btnSavedFood.setOnClickListener(view -> {
            boolean addFoodSaved = dao.addFoodSaved(new FoodSaved(foodSize.getFoodId(), foodSize.getSize(), userID));
            if(addFoodSaved){
                Toast.makeText(this, "Đã lưu thông tin món ăn!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Thông tin món ăn đã lưu trước đó!", Toast.LENGTH_SHORT).show();
            }
        });

        Button btnAddQuantity = findViewById(R.id.btnAddQuantity_Food);
        btnAddQuantity.setOnClickListener(view -> {
            quantity++;
            tvQuantity.setText(String.format("%s", quantity));
            tvPrice.setText(getTotalPrice());
        });

        Button btnSubQuantity = findViewById(R.id.btnSubQuantity_Food);
        btnSubQuantity.setOnClickListener(view -> {
            if(quantity > 1){
                quantity--;
                tvQuantity.setText(String.format("%s", quantity));
                tvPrice.setText(getTotalPrice());
            }
        });
    }

    private void SetPriceDefault(Double price){
        tvPrice.setText(getRoundPrice(price));
        quantity = 1;
        tvQuantity.setText("1");
    }

    private void LoadData(){
        Intent intent = getIntent();
        if(intent != null){
            Food food = (Food) intent.getSerializableExtra("food");

            ArrayList<FoodSize> foodSizeArrayList = dao.getAllFoodSize(food.getId());
            if(foodSizeArrayList.get(0) != null){
                if(foodSize == null) foodSize = foodSizeArrayList.get(0);

                tvPriceSizeS.setText(String.format("%s", foodSizeArrayList.get(0).getPrice()));
                layout_sizeS.setOnClickListener(view -> {
                    SetPriceDefault(foodSizeArrayList.get(0).getPrice());
                    foodSize = foodSizeArrayList.get(0);
                });
            } else {
                layout_sizeS.setVisibility(View.INVISIBLE);
            }

            if(foodSizeArrayList.get(1) != null){
                tvPriceSizeM.setText(String.format("%s", foodSizeArrayList.get(1).getPrice()));
                layout_sizeM.setOnClickListener(view -> {
                    SetPriceDefault(foodSizeArrayList.get(1).getPrice());
                    foodSize = foodSizeArrayList.get(1);
                });
            } else {
                layout_sizeM.setVisibility(View.INVISIBLE);
            }

            if(foodSizeArrayList.get(2) != null){
                tvPriceSizeL.setText(String.format("%s", foodSizeArrayList.get(2).getPrice()));
                layout_sizeL.setOnClickListener(view -> {
                    SetPriceDefault(foodSizeArrayList.get(2).getPrice());
                    foodSize = foodSizeArrayList.get(2);
                });
            } else {
                layout_sizeL.setVisibility(View.INVISIBLE);
            }

            // Set information
            tvName.setText(food.getName());
            tvDescription.setText(food.getDescription());
            image.setImageBitmap(DatabaseHandler.convertByteArrayToBitmap(food.getImage()));

            Restaurant restaurant = dao.getRestaurantInformation(food.getRestaurantId());
            tvRestaurantName.setText(String.format("Tên cửa hàng \n%s", restaurant.getName()));
            tvRestaurantAddress.setText(String.format("Địa chỉ\n%s", restaurant.getAddress()));

            tvPrice.setText(getRoundPrice(foodSize.getPrice()));
        }
    }
}