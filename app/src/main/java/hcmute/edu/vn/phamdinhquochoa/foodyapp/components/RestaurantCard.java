package hcmute.edu.vn.phamdinhquochoa.foodyapp.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import hcmute.edu.vn.phamdinhquochoa.foodyapp.HomeActivity;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.R;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.Restaurant;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.RestaurantSaved;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.dbcontext.DatabaseHandler;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.fragments.SavedFragment;

public class RestaurantCard extends LinearLayout {
    private Restaurant restaurant;
    private boolean isSaved;
    
    public RestaurantCard(Context context, Restaurant restaurant, boolean isSaved) {
        super(context);
        this.restaurant = restaurant;
        this.isSaved = isSaved;
        initControl(context);
    }

    public RestaurantCard(Context context){
        super(context);
    }
    
    private void initControl(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.restaurant_card, this);

        ImageView image = findViewById(R.id.imageRestaurant);
        TextView tvRestaurantName = findViewById(R.id.tvRestaurantName_res_cart);
        TextView tvRestaurantAddress = findViewById(R.id.tvRestaurantAddress_res_cart);

        Button btnSaved = findViewById(R.id.btnSavedRestaurant);
        if(isSaved){ btnSaved.setText("BỎ LƯU"); } // Thẻ được lưu
        btnSaved.setOnClickListener(view ->{
            if(isSaved){
                if(HomeActivity.dao.deleteRestaurantSaved(new RestaurantSaved(restaurant.getId(), HomeActivity.user.getId()))){
                    Toast.makeText(context, "Đã bỏ lưu thông tin nhà hàng!", Toast.LENGTH_SHORT).show();
                    SavedFragment.saved_container.removeView(this);
                } else {
                    Toast.makeText(context, "Có lỗi khi xóa thông tin!", Toast.LENGTH_SHORT).show();
                }
            } else {
                if(HomeActivity.dao.addRestaurantSaved(new RestaurantSaved(restaurant.getId(), HomeActivity.user.getId()))){
                    Toast.makeText(context, "Lưu thông tin nhà hàng thành công!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Bạn đã lưu thông tin nhà hàng này rồi!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // set information
        image.setImageBitmap(DatabaseHandler.convertByteArrayToBitmap(restaurant.getImage()));
        tvRestaurantName.setText(restaurant.getName());
        tvRestaurantAddress.setText(restaurant.getAddress());
    }
}
