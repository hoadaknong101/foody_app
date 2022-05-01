package hcmute.edu.vn.phamdinhquochoa.foodyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.Food;

public class FoodDetailsActivity extends AppCompatActivity {

    private ImageView btnBack;
    private TextView tvName, tvDescription, tvPrice;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        referenceComponent();

        Intent intent = getIntent();
        Food food = (Food) intent.getSerializableExtra("food");

        if(food!= null){
//            tvName.setText(food.getName());
//            tvPrice.setText(food.getPrice() + " VNĐ");
//            tvDescription.setText(food.getDescription());
        }
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void referenceComponent(){
        btnBack = findViewById(R.id.btnBack);
        tvName = findViewById(R.id.tvName);
        tvDescription = findViewById(R.id.tvDescription);
        tvPrice = findViewById(R.id.tvPrice);
    }
}