package hcmute.edu.vn.phamdinhquochoa.foodyapp.components;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import hcmute.edu.vn.phamdinhquochoa.foodyapp.HomeActivity;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.R;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.*;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.dbcontext.DatabaseHandler;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.fragments.ChatFragment;

public class CartCard extends LinearLayout {
    private Food food;
    private String restaurantName;
    private OrderDetail card;
    private boolean activateControl;
    private int quantity;

    public CartCard(Context context) {
        super(context);
        initControl(context);
    }

    public CartCard(Context context, Food food, String restaurantName, OrderDetail card) {
        super(context);
        this.food = food;
        this.restaurantName = restaurantName;
        this.card = card;
        this.activateControl = true;
        initControl(context);
    }

    public CartCard(Context context, Food food, String restaurantName, OrderDetail card, boolean activateControl) {
        super(context);
        this.food = food;
        this.restaurantName = restaurantName;
        this.card = card;
        this.activateControl = activateControl;
        initControl(context);
    }

    @SuppressLint("SetTextI18n")
    private void initControl(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.cart_card, this);
        quantity = card.getQuantity();

        ImageView image = findViewById(R.id.imageCartFood);
        TextView tvName = findViewById(R.id.tvFoodNameCart);
        TextView tvSize = findViewById(R.id.tvFoodSizeCart);
        TextView tvRestaurantName = findViewById(R.id.tvRestaurantNameCart);
        TextView tvPrice = findViewById(R.id.tvFoodPriceCart);

        // For quantity
        TextView tvQuantity = findViewById(R.id.tvFoodQuantity_Cart);
        Button btnSub = findViewById(R.id.btnSubQuantity_Cart);
        btnSub.setOnClickListener(view -> {
            if(quantity > 1){
                quantity--;
                tvQuantity.setText("" + quantity);
                card.setQuantity(quantity);
                HomeActivity.dao.updateQuantity(card);
            }
        });

        Button btnAdd = findViewById(R.id.btnAddQuantity_Cart);
        btnAdd.setOnClickListener(view -> {
            quantity++;
            tvQuantity.setText("" + quantity);
            card.setQuantity(quantity);
            HomeActivity.dao.updateQuantity(card);
        });

        Button btnDelete = findViewById(R.id.btnDeleteCartItem);
        btnDelete.setOnClickListener(view -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
            dialog.setMessage("Bạn có muốn xóa món " + food.getName() + " không?");
            dialog.setPositiveButton("Có", (dialogInterface, i) -> {
                if(HomeActivity.dao.deleteOrderDetailByOrderIdAndFoodId(card.getOrderId(), food.getId())){
                    ChatFragment.cartContainer.removeView(this);
                    Toast.makeText(context, "Đã xóa thông tin!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Gặp lỗi khi xóa thông tin!", Toast.LENGTH_SHORT).show();
                }
            });
            dialog.setNegativeButton("Không", (dialogInterface, i) -> {});
            dialog.show();
        });

        LinearLayout layout = findViewById(R.id.layout_btn_delete);

        if(!activateControl) {
            btnDelete.setVisibility(GONE);
            layout.setBackgroundColor(Color.rgb(255, 70, 70));
            btnAdd.setEnabled(activateControl);
            btnSub.setEnabled(activateControl);
        }

        // Set information for cart card
        image.setImageBitmap(DatabaseHandler.convertByteArrayToBitmap(food.getImage()));
        tvName.setText(food.getName());
        switch (card.getSize()){
            case 1:
                tvSize.setText("Size S");
                break;
            case 2:
                tvSize.setText("Size M");
                break;
            case 3:
                tvSize.setText("Size L");
                break;
        }
        tvRestaurantName.setText(restaurantName);
        tvPrice.setText(getRoundPrice(card.getPrice()));
        tvQuantity.setText("" + quantity);
    }

    private String getRoundPrice(Double price){
        return Math.round(price) + " VNĐ";
    }
}
