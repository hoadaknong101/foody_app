package hcmute.edu.vn.phamdinhquochoa.foodyapp.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import hcmute.edu.vn.phamdinhquochoa.foodyapp.R;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.Food;

public class FoodCard extends LinearLayout {

    private ImageView image;
    private TextView tvName, tvPrice;
    private Food food;

    public FoodCard(Context context, Food food){
        super(context);
        this.food = food;
        initControl(context);
    }

    public FoodCard(Context context) {
        super(context);
        initControl(context);
    }

    private void initControl(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.food_card, this);

        image = findViewById(R.id.imageFood);
        tvName = findViewById(R.id.tvNameFood);
        tvPrice = findViewById(R.id.tvPriceFood);

        // Set image for food cart

        // Set name for food cart
        tvName.setText(food.getName());

        // Set price for food cart
//        tvPrice.setText(food.getPrice() + " VNĐ");

    }
}
