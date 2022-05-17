package hcmute.edu.vn.phamdinhquochoa.foodyapp;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.support.v7.app.AppCompatActivity;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.Food;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.FoodSize;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.Restaurant;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.components.FoodCard;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.dao.DAO;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.dbcontext.DatabaseHandler;

public class CategoryActivity extends AppCompatActivity {
    private LinearLayout foodCartContainer;
    private DAO dao;
    private Intent intent_get_data;
    private Integer restaurantId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        intent_get_data = getIntent();

        dao = new DAO(this);
        referencesComponents();
        getFoodData(null);
    }

    private void referencesComponents(){
        ImageView image = findViewById(R.id.imageCartC);
        image.setOnClickListener(view -> {
            Intent intent = new Intent(CategoryActivity.this, HomeActivity.class);
            intent.putExtra("request","cart");
            startActivity(intent);
        });

        ImageView imageSync = findViewById(R.id.imageSync);
        imageSync.setOnClickListener(view -> getFoodData(null));

        SearchView searchBar = findViewById(R.id.search_bar);
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                String nameFoodOfThisRestaurant = searchBar.getQuery().toString();
                getFoodData(nameFoodOfThisRestaurant);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        ImageView restaurantImage = findViewById(R.id.imageRestaurant_category);
        TextView tvRestaurantName = findViewById(R.id.tvRestaurantName_category);
        TextView tvRestaurantAddress = findViewById(R.id.tvRestaurantAddress_category);
        TextView tvRestaurantPhone = findViewById(R.id.tvRestaurantPhone_category);

        foodCartContainer = findViewById(R.id.foodCartContainer);

        // Restaurant data
        LinearLayout layoutRestaurant = findViewById(R.id.layout_restaurantInformation);
        restaurantId = intent_get_data.getIntExtra("restaurantId", -1);
        if(restaurantId != -1){
            Restaurant restaurant = dao.getRestaurantInformation(restaurantId);

            restaurantImage.setImageBitmap(DatabaseHandler.convertByteArrayToBitmap(restaurant.getImage()));
            tvRestaurantName.setText(restaurant.getName());
            tvRestaurantAddress.setText(String.format("Địa chỉ: %s", restaurant.getAddress()));
            tvRestaurantPhone.setText(String.format("Số điện thoại: %s", restaurant.getPhone()));
        } else {
            layoutRestaurant.setVisibility(View.GONE);
        }
    }

    private void getFoodData(String nameFoodOfThisRestaurant){
        foodCartContainer.removeAllViews();
        // Add food cart to layout container
        ArrayList<Food> foodArrayList;

        if(nameFoodOfThisRestaurant == null) {
            int getRestaurantId = intent_get_data.getIntExtra("restaurantId", -1);
            System.out.println(getRestaurantId);
            if (getRestaurantId == -1){
                String foodKeyword = intent_get_data.getStringExtra("nameFood");
                foodArrayList = dao.getFoodByKeyWord(foodKeyword, null);
                System.out.println(foodArrayList);
            } else {
                foodArrayList = dao.getFoodByRestaurant(getRestaurantId);
            }
        } else {
            foodArrayList = dao.getFoodByKeyWord(nameFoodOfThisRestaurant, restaurantId);
        }

        for(Food food : foodArrayList){
            Restaurant restaurant = dao.getRestaurantInformation(food.getRestaurantId());
            FoodSize foodSize = dao.getFoodDefaultSize(food.getId());

            FoodCard foodCard = new FoodCard(this, food, foodSize.getPrice(), restaurant.getName());

            foodCard.setOnClickListener(view -> {
                FoodDetailsActivity.foodSize = foodSize;
                Intent intent = new Intent(this, FoodDetailsActivity.class);
                intent.putExtra("food", food);
                try {
                    startActivity(intent);
                } catch (Exception e){
                    Toast.makeText(this, "Không thể hiển thị thông tin!", Toast.LENGTH_SHORT).show();
                }
            });
            foodCartContainer.addView(foodCard);
        }
    }
}