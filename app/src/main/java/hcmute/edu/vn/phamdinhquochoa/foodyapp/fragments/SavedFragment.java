package hcmute.edu.vn.phamdinhquochoa.foodyapp.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Objects;

import hcmute.edu.vn.phamdinhquochoa.foodyapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SavedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SavedFragment extends Fragment {

    private LinearLayout layout_saved;
    private LinearLayout btn_saved_food, btn_saved_restaurant;
    private TextView tv_saved_food, tv_saved_restaurant;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public SavedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SavedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SavedFragment newInstance(String param1, String param2) {
        SavedFragment fragment = new SavedFragment();
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
        View mainView = inflater.inflate(R.layout.fragment_saved, container, false);

        layout_saved = mainView.findViewById(R.id.layout_saved);

        btn_saved_food = mainView.findViewById(R.id.btn_saved_food);
        tv_saved_food = mainView.findViewById(R.id.tv_saved_food);
        btn_saved_restaurant = mainView.findViewById(R.id.btn_saved_restaurant);
        tv_saved_restaurant = mainView.findViewById(R.id.tv_saved_restaurant);

        btn_saved_food.setOnClickListener(view -> {
            btn_saved_food.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()),R.color.main_color));
            tv_saved_food.setTextColor(Color.WHITE);
            btn_saved_restaurant.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()),R.drawable.bg_white));
            tv_saved_restaurant.setTextColor(Color.BLACK);

            LoadSavedCard("food");
        });

        btn_saved_restaurant.setOnClickListener(view -> {
            btn_saved_food.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()),R.drawable.bg_white));
            tv_saved_food.setTextColor(Color.BLACK);
            btn_saved_restaurant.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()),R.color.main_color));
            tv_saved_restaurant.setTextColor(Color.WHITE);

            LoadSavedCard("restaurant");
        });

        return mainView;
    }

    private void LoadSavedCard(String type){
        layout_saved.removeAllViews();

        if(type.equals("food")){

        } else{

        }
    }
}