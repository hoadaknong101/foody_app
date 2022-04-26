package hcmute.edu.vn.phamdinhquochoa.foodyapp.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import hcmute.edu.vn.phamdinhquochoa.foodyapp.PaymentActivity;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private View view;
    private LinearLayout cartContainer, btnDangDen, btnLichSu, btnGioHang;
    private Button btnThanhToan;
    private TextView tvGioHang, tvDangDen, tvLichSu;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_chat, container, false);
        cartContainer = view.findViewById(R.id.cartContainer);
        btnGioHang = view.findViewById(R.id.btnGioHang);
        btnDangDen = view.findViewById(R.id.btnDangDen);
        btnLichSu = view.findViewById(R.id.btnLichSu);
        tvGioHang = view.findViewById(R.id.tvGioHang);
        tvDangDen = view.findViewById(R.id.tvDangDen);
        tvLichSu = view.findViewById(R.id.tvLichSu);
        btnThanhToan = view.findViewById(R.id.btnThanhToan);

        btnThanhToan.setOnClickListener(view ->{
            startActivity(new Intent(getActivity(), PaymentActivity.class));
        });


        btnGioHang.setOnClickListener(view ->{
            resetAttribute();
            btnGioHang.setBackground(ContextCompat.getDrawable(getContext(),R.color.main_color));
            tvGioHang.setTextColor(Color.WHITE);

            //Function here



        });

        btnDangDen.setOnClickListener(view->{
            resetAttribute();
            btnDangDen.setBackground(ContextCompat.getDrawable(getContext(),R.color.main_color));
            tvDangDen.setTextColor(Color.WHITE);

            //Function here





        });

        btnLichSu.setOnClickListener(view -> {
            resetAttribute();
            btnLichSu.setBackground(ContextCompat.getDrawable(getContext(),R.color.main_color));
            tvLichSu.setTextColor(Color.WHITE);

            //Function here



        });


        return view;
    }

    private void resetAttribute(){
        btnGioHang.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.bg_white));
        btnDangDen.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.bg_white));
        btnLichSu.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.bg_white));
        tvGioHang.setTextColor(Color.BLACK);
        tvLichSu.setTextColor(Color.BLACK);
        tvDangDen.setTextColor(Color.BLACK);
    }
}