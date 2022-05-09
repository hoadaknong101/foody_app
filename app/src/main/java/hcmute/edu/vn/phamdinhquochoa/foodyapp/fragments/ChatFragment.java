package hcmute.edu.vn.phamdinhquochoa.foodyapp.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

import hcmute.edu.vn.phamdinhquochoa.foodyapp.HomeActivity;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.PaymentActivity;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.R;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.Food;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.OrderDetail;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.Restaurant;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.components.CartCard;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private View mainView;
    @SuppressLint("StaticFieldLeak")
    public static LinearLayout cartContainer;
    private LinearLayout btnDangDen, btnLichSu, btnGioHang;
    private TextView tvGioHang, tvDangDen, tvLichSu;
    private ArrayList<OrderDetail> orderDetailArrayList;

    public ChatFragment() {
    }

    public static ChatFragment newInstance(String param1, String param2) {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // TODO: Rename and change types of parameters
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainView = inflater.inflate(R.layout.fragment_chat, container, false);
        cartContainer = mainView.findViewById(R.id.cartContainer);

        referencesComponent();
        LoadOrder("craft");
        return mainView;
    }

    private void referencesComponent(){
        btnGioHang = mainView.findViewById(R.id.btnGioHang);
        btnGioHang.setOnClickListener(view ->{
            resetAttribute();
            btnGioHang.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()),R.color.main_color));
            tvGioHang.setTextColor(Color.WHITE);

            LoadOrder("craft");
        });

        btnDangDen = mainView.findViewById(R.id.btnDangDen);
        btnDangDen.setOnClickListener(view->{
            resetAttribute();
            btnDangDen.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()),R.color.main_color));
            tvDangDen.setTextColor(Color.WHITE);

            LoadOrder("coming");
        });

        btnLichSu = mainView.findViewById(R.id.btnLichSu);
        btnLichSu.setOnClickListener(view -> {
            resetAttribute();
            btnLichSu.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()),R.color.main_color));
            tvLichSu.setTextColor(Color.WHITE);

            LoadOrder("history");
        });

        tvGioHang = mainView.findViewById(R.id.tvGioHang);
        tvDangDen = mainView.findViewById(R.id.tvDangDen);
        tvLichSu = mainView.findViewById(R.id.tvLichSu);

        Button btnThanhToan = mainView.findViewById(R.id.btnThanhToan);
        btnThanhToan.setOnClickListener(view ->{
            startActivity(new Intent(getActivity(), PaymentActivity.class));
        });
    }

    private void LoadOrder(String type){
        cartContainer.removeAllViews();
        if(type.equals("craft")){
            Cursor cursor = HomeActivity.dao.getCart(HomeActivity.user.getId());
            if(!cursor.moveToFirst())
                return;
            cursor.moveToFirst();
            orderDetailArrayList = HomeActivity.dao.getCartDetailList(cursor.getInt(0));
            if(orderDetailArrayList.size() > 0){
                Food food;
                Restaurant restaurant;
                for(OrderDetail orderDetail : orderDetailArrayList){
                    food = HomeActivity.dao.getFoodById(orderDetail.getFoodId());
                    restaurant = HomeActivity.dao.getRestaurantInformation(food.getRestaurantId());
                    CartCard card = new CartCard(getContext(), food, restaurant.getAddress(), orderDetail);
                    cartContainer.addView(card);
                }
            }
        } else if (type.equals("coming")) {

        } else {

        }
    }

    private void resetAttribute(){
        btnGioHang.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()),R.drawable.bg_white));
        btnDangDen.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.bg_white));
        btnLichSu.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.bg_white));

        tvGioHang.setTextColor(Color.BLACK);
        tvLichSu.setTextColor(Color.BLACK);
        tvDangDen.setTextColor(Color.BLACK);
    }
}