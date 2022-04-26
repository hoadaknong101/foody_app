package hcmute.edu.vn.phamdinhquochoa.foodyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.Category;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.Food;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.components.CategoryCart;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.components.FoodCart;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // Add category cart to layout container
        LinearLayout categoryCartContainer = (LinearLayout) findViewById(R.id.categoryCartContainer);
        categoryCartContainer.addView(new CategoryCart(this, new Category(1,"Món nước","Hình ảnh","Mô tả")));
        categoryCartContainer.addView(new CategoryCart(this, new Category(2,"Humberger","Hình ảnh","Mô tả")));
        categoryCartContainer.addView(new CategoryCart(this, new Category(3,"Pizza","Hình ảnh","Mô tả")));
        categoryCartContainer.addView(new CategoryCart(this, new Category(4,"Món gỏi","Hình ảnh","Mô tả")));
        categoryCartContainer.addView(new CategoryCart(this, new Category(5,"Mì cay","Hình ảnh","Mô tả")));

        ImageView image = findViewById(R.id.imageCartC);
        image.setOnClickListener(view -> {
            Intent intent = new Intent(CategoryActivity.this,HomeActivity.class);
            intent.putExtra("isCartClick",true);
            startActivity(intent);
        });


        // Add food cart to layout container
        LinearLayout foodCartContainer = (LinearLayout) findViewById(R.id.foodCartContainer);
        String description = "Thức ăn bổ dưỡng tốt cho sức khỏe, " +
                "chứa nhiều chất đạm, chất xơ," +
                " tốt cho cơ thể.";

        Food f1 = new Food(1,"Bánh mì thịt",15000.0,5.0,description);
        FoodCart fc1 = new FoodCart(this,f1);
        fc1.setOnClickListener(view -> {
            Intent intent = new Intent(CategoryActivity.this,FoodDetailsActivity.class);
            intent.putExtra("food",f1);
            startActivity(intent);
        });
        foodCartContainer.addView(fc1);

        Food f2 = new Food(1,"Bánh mì chả",18000.0,5.0,description);
        FoodCart fc2 = new FoodCart(this,f2);
        fc2.setOnClickListener(view -> {
            Intent intent = new Intent(CategoryActivity.this,FoodDetailsActivity.class);
            intent.putExtra("food",f2);
            startActivity(intent);
        });
        foodCartContainer.addView(fc2);


        Food f3 = new Food(1,"Bánh mì chả ngon",15000.0,5.0,description);
        FoodCart fc3 = new FoodCart(this,f3);
        fc3.setOnClickListener(view -> {
            Intent intent = new Intent(CategoryActivity.this,FoodDetailsActivity.class);
            intent.putExtra("food",f3);
            startActivity(intent);
        });
        foodCartContainer.addView(fc3);

        Food f4 = new Food(1,"Bánh mì thập cẩm",25000.0,5.0,description);
        FoodCart fc4 = new FoodCart(this,f4);
        fc4.setOnClickListener(view -> {
            Intent intent = new Intent(CategoryActivity.this,FoodDetailsActivity.class);
            intent.putExtra("food",f4);
            startActivity(intent);
        });
        foodCartContainer.addView(fc4);


        Food f5 = new Food(1,"Bánh mì trứng chả",30000.0,5.0,description);
        FoodCart fc5 = new FoodCart(this,f5);
        fc5.setOnClickListener(view -> {
            Intent intent = new Intent(CategoryActivity.this,FoodDetailsActivity.class);
            intent.putExtra("food",f5);
            startActivity(intent);
        });
        foodCartContainer.addView(fc5);
    }
}