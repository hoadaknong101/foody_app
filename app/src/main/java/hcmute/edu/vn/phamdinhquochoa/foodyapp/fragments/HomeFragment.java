package hcmute.edu.vn.phamdinhquochoa.foodyapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;

import java.util.ArrayList;

import hcmute.edu.vn.phamdinhquochoa.foodyapp.CategoryActivity;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.HomeActivity;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.R;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.Restaurant;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.components.RestaurantCard;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private Intent intent;
    private View mainView;
    private LinearLayout layout_container;
    
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

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
            // TODO: Rename and change types of parameters
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(mainView == null){
            mainView = inflater.inflate(R.layout.fragment_home, container, false);

            layout_container = mainView.findViewById(R.id.layout_container_restaurant);

            ArrayList<Restaurant> restaurantArrayList = HomeActivity.dao.getRestaurantList();

            layout_container.removeAllViews();
            for(Restaurant restaurant : restaurantArrayList){
                RestaurantCard card = new RestaurantCard(getContext(), restaurant, false);
                card.setOnClickListener(view->{
                    intent = new Intent(getActivity(), CategoryActivity.class);
                    intent.putExtra("restaurantId", restaurant.getId());
                    startActivity(intent);
                });

                layout_container.addView(card);
            }

            ImageView imageCart = mainView.findViewById(R.id.imageCart);
            imageCart.setOnClickListener(view -> {
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                intent.putExtra("request", "cart");
                startActivity(intent);
            });

            SearchView searchBar = mainView.findViewById(R.id.search_bar);
            searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    String textSearch = searchBar.getQuery().toString();
                    intent = new Intent(getActivity(), CategoryActivity.class);
                    intent.putExtra("nameFood", textSearch);
                    startActivity(intent);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    return false;
                }
            });
        }

        return mainView;
    }
}