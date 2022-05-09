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

import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.*;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.dao.DAO;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.dbcontext.DatabaseHandler;

public class FoodDetailsActivity extends AppCompatActivity {
    private ImageView image;
    private LinearLayout layout_sizeS, layout_sizeM, layout_sizeL;
    private TextView tvName, tvDescription, tvPrice,
            tvRestaurantName, tvRestaurantAddress,
            tvPriceSizeS,tvPriceSizeM, tvPriceSizeL;

    public static User user;
    private Food food;
    private Restaurant restaurant;
    private FoodSize foodSize;
    private DAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        referenceComponent();
        dao = new DAO(this);
        LoadData();
    }

    private String getRoundPrice(Double price){
        return Math.round(price) + " VNĐ";
    }

    private void referenceComponent(){
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(view -> this.finish());

        tvName = findViewById(R.id.tvName);
        tvDescription = findViewById(R.id.tvDescription);
        tvPrice = findViewById(R.id.tvPrice);
        image = findViewById(R.id.image);

        layout_sizeS = findViewById(R.id.layout_size_S);
        layout_sizeM = findViewById(R.id.layout_size_M);
        layout_sizeL = findViewById(R.id.layout_size_L);

        tvPriceSizeS = findViewById(R.id.tvPriceSizeS);
        tvPriceSizeM = findViewById(R.id.tvPriceSizeM);
        tvPriceSizeL = findViewById(R.id.tvPriceSizeL);

        tvRestaurantName = findViewById(R.id.tvRestaurantName);
        tvRestaurantAddress = findViewById(R.id.tvRestaurantAddress);

        Button btnAddToCart = findViewById(R.id.btnAddToCart);
        btnAddToCart.setOnClickListener(view -> {
            System.out.println(user);
            Cursor cursor = dao.getCart(user.getId());
            if (!cursor.moveToFirst()){
                dao.addOrder(new Order(1, user.getId(), "", "", 0d, "Craft"));
                cursor = dao.getCart(user.getId());
            }

            cursor.moveToFirst();
            System.out.println(cursor.getInt(0));
            dao.addOrderDetail(new OrderDetail(cursor.getInt(0),
                    foodSize.getFoodId(), foodSize.getSize(), foodSize.getPrice()));

            Toast.makeText(this, "Thêm sản phẩm vào giỏ hàng thành công!", Toast.LENGTH_SHORT).show();
        });

        Button btnSavedFood = findViewById(R.id.btnSavedFood);
        btnSavedFood.setOnClickListener(view -> {

        });
    }

    private void LoadData(){
        Intent intent = getIntent();
        if(intent != null){
            Bundle bundle = intent.getBundleExtra("foodDetail");

            food = (Food) bundle.getSerializable("food");
            restaurant = (Restaurant) bundle.getSerializable("restaurant");
            FoodSize foodSizeS = (FoodSize) bundle.getSerializable("foodSizeS");
            FoodSize foodSizeM = (FoodSize) bundle.getSerializable("foodSizeM");
            FoodSize foodSizeL = (FoodSize) bundle.getSerializable("foodSizeL");

            if(food!= null){
                tvName.setText(food.getName());
                tvDescription.setText(food.getDescription());
                image.setImageBitmap(DatabaseHandler.convertByteArrayToBitmap(food.getImage()));

                if(foodSizeS != null){
                    tvPriceSizeS.setText(getRoundPrice(foodSizeS.getPrice()));
                    layout_sizeS.setOnClickListener(view -> {
                        tvPrice.setText(tvPriceSizeS.getText());
                        foodSize = foodSizeS;
                    });
                } else {
                    layout_sizeS.setVisibility(View.INVISIBLE);
                }

                if(foodSizeM != null){
                    tvPriceSizeM.setText(getRoundPrice(foodSizeM.getPrice()));
                    layout_sizeM.setOnClickListener(view -> {
                        tvPrice.setText(tvPriceSizeM.getText());
                        foodSize = foodSizeM;
                    });
                } else {
                    layout_sizeM.setVisibility(View.INVISIBLE);
                }

                if(foodSizeL != null){
                    tvPriceSizeL.setText(getRoundPrice(foodSizeL.getPrice()));
                    layout_sizeL.setOnClickListener(view -> {
                        tvPrice.setText(tvPriceSizeL.getText());
                        foodSize = foodSizeL;
                    });
                } else {
                    layout_sizeL.setVisibility(View.INVISIBLE);
                }

                tvRestaurantName.setText(String.format("Tên cửa hàng \n%s", restaurant.getName()));
                tvRestaurantAddress.setText(String.format("Địa chỉ\n%s", restaurant.getAddress()));

                foodSize = (FoodSize) bundle.getSerializable("defaultFoodSize");
                tvPrice.setText(getRoundPrice(foodSize.getPrice()));
            }
        }
    }
}