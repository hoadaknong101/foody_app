package hcmute.edu.vn.phamdinhquochoa.foodyapp.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import hcmute.edu.vn.phamdinhquochoa.foodyapp.R;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.Cart;

public class CartCard extends LinearLayout {

    private ImageView image;
    private TextView tvName, tvPrice;
    private Cart cart;

    public CartCard(Context context) {
        super(context);
    }

    public CartCard(Context context, Cart cart) {
        super(context);
        this.cart = cart;
        initControl(context);
    }

    private void initControl(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.cart_card, this);

        image = findViewById(R.id.imageCartFood);
        tvName = findViewById(R.id.tvFoodNameCart);
        tvPrice = findViewById(R.id.tvFoodPrice);

        // Set text here


    }

}
