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
import hcmute.edu.vn.phamdinhquochoa.foodyapp.FoodDetailsActivity;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.HomeActivity;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View view;

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
        view = inflater.inflate(R.layout.fragment_home, container, false);

        LinearLayout linearLayout = view.findViewById(R.id.layoutHamburger);
        linearLayout.setOnClickListener(view -> startActivity(new Intent(getActivity(), CategoryActivity.class)));

        LinearLayout linearLayout1 = view.findViewById(R.id.layoutCake);
        linearLayout1.setOnClickListener(view -> startActivity(new Intent(getActivity(), CategoryActivity.class)));

        LinearLayout linearLayout2 = view.findViewById(R.id.layoutRice);
        linearLayout2.setOnClickListener(view -> startActivity(new Intent(getActivity(), CategoryActivity.class)));

        LinearLayout linearLayout3 = view.findViewById(R.id.layoutMilkTea);
        linearLayout3.setOnClickListener(view -> startActivity(new Intent(getActivity(), CategoryActivity.class)));

        LinearLayout linearLayout4 = view.findViewById(R.id.layoutIceCream);
        linearLayout4.setOnClickListener(view -> startActivity(new Intent(getActivity(), CategoryActivity.class)));

        LinearLayout linearLayout5 = view.findViewById(R.id.layoutWaterFood);
        linearLayout5.setOnClickListener(view -> startActivity(new Intent(getActivity(), CategoryActivity.class)));

        ImageView imageBanhMi = view.findViewById(R.id.imageBanhMi);
        imageBanhMi.setOnClickListener(view -> startActivity(new Intent(getActivity(), FoodDetailsActivity.class)));

        ImageView imageCart = view.findViewById(R.id.imageCart);
        imageCart.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(),HomeActivity.class);
            intent.putExtra("isCartClick",true);
            startActivity(intent);
        });

        return view;
    }
}