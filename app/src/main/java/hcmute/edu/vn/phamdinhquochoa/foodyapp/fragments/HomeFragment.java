package hcmute.edu.vn.phamdinhquochoa.foodyapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import hcmute.edu.vn.phamdinhquochoa.foodyapp.CategoryActivity;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.HomeActivity;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private Intent intent;
    View mainView;
    
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        if(mainView == null){
            mainView = inflater.inflate(R.layout.fragment_home, container, false);

            LinearLayout layoutHamburger = mainView.findViewById(R.id.layoutHamburger);
            layoutHamburger.setOnClickListener(view -> {
                intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("typeFood", "Bánh mì");
                startActivity(intent);
            });

            LinearLayout layoutCake = mainView.findViewById(R.id.layoutCake);
            layoutCake.setOnClickListener(view -> {
                intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("typeFood", "Bánh ngọt");
                startActivity(intent);
            });

            LinearLayout layoutRice = mainView.findViewById(R.id.layoutRice);
            layoutRice.setOnClickListener(view -> {
                intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("typeFood", "Cơm sườn");
                startActivity(intent);
            });

            LinearLayout layoutMilkTea = mainView.findViewById(R.id.layoutMilkTea);
            layoutMilkTea.setOnClickListener(view -> {
                intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("typeFood", "Trà sữa");
                startActivity(intent);
            });

            LinearLayout layoutIceCream = mainView.findViewById(R.id.layoutIceCream);
            layoutIceCream.setOnClickListener(view -> {
                intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("typeFood", "Kem");
                startActivity(intent);
            });

            LinearLayout layoutWaterFood = mainView.findViewById(R.id.layoutWaterFood);
            layoutWaterFood.setOnClickListener(view -> {
                intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("typeFood", "Món nước");
                startActivity(intent);
            });

            ImageView imageCart = mainView.findViewById(R.id.imageCart);
            imageCart.setOnClickListener(view1 -> {
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                intent.putExtra("request", "cart");
                startActivity(intent);
            });
        }

//        ImageView imageBanhMi = mainView.findViewById(R.id.imageBanhMi);
//        imageBanhMi.setOnClickListener(view -> startActivity(new Intent(getActivity(), FoodDetailsActivity.class)));

        return mainView;
    }
}