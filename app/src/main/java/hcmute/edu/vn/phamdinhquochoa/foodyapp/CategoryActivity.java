package hcmute.edu.vn.phamdinhquochoa.foodyapp;

import android.content.Intent;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.Food;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.FoodSize;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.Restaurant;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.components.FoodCard;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.dao.DAO;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);


        ImageView image = findViewById(R.id.imageCartC);
        image.setOnClickListener(view -> {
            Intent intent = new Intent(CategoryActivity.this,HomeActivity.class);
            intent.putExtra("isCartClick",true);
            startActivity(intent);
        });


        // Add food cart to layout container
        LinearLayout foodCartContainer = (LinearLayout) findViewById(R.id.foodCartContainer);
        Intent intent_get_data = getIntent();
        DAO dao = new DAO(this);
        ArrayList<Food> foodArrayList = dao.getFoodByType(intent_get_data.getStringExtra("typeFood"));

        for(Food food : foodArrayList){
            Restaurant restaurant = dao.getRestaurantInformation(food.getRestaurantId());
            Double defaultPrice = dao.getFoodDefaultPrice(food.getId());
            FoodCard foodCard = new FoodCard(this, food, defaultPrice);
            foodCard.setOnClickListener(view -> {
                Intent intent = new Intent(this, FoodDetailsActivity.class);
                ArrayList<FoodSize> foodSizeArrayList = dao.getAllFoodSize(food.getId());
                Bundle bundle = new Bundle();
                bundle.putSerializable("food", food);
                bundle.putSerializable("restaurant", restaurant);
                bundle.putSerializable("foodSizeS", foodSizeArrayList.get(0));

                if(foodSizeArrayList.size() < 3){
                    bundle.putSerializable("foodSizeM", foodSizeArrayList.get(1));
                }
                if(foodSizeArrayList.size() == 3) {
                    bundle.putSerializable("foodSizeM", foodSizeArrayList.get(1));
                    bundle.putSerializable("foodSizeL", foodSizeArrayList.get(2));
                }

                bundle.putDouble("defaultPrice", defaultPrice);

                intent.putExtra("foodDetail", bundle);
                try {
                    startActivity(intent);
                } catch (Exception e){

                }
            });
            foodCartContainer.addView(foodCard);
        }

    }
}