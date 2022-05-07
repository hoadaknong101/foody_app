package hcmute.edu.vn.phamdinhquochoa.foodyapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import hcmute.edu.vn.phamdinhquochoa.foodyapp.HomeActivity;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.MainActivity;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View mainView;
    // Components
    private LinearLayout payment, history, check, hint, policy, logout;
    private TextView txtUser_name;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        mainView = inflater.inflate(R.layout.fragment_profile, container, false);
        referenceComponent();
        // Inflate the layout for this fragment
        return mainView;
    }

    private void referenceComponent(){
        payment = mainView.findViewById(R.id.account_btn_layout_payment);
        history = mainView.findViewById(R.id.account_btn_layout_history);
        check = mainView.findViewById(R.id.account_btn_layout_check);
        hint = mainView.findViewById(R.id.account_btn_layout_hint);
        policy = mainView.findViewById(R.id.account_btn_layout_policy);
        logout = mainView.findViewById(R.id.account_btn_layout_logout);
        logout.setOnClickListener(view -> {
            Toast.makeText(this.getActivity(),
                    "Đã đăng xuất khỏi hệ thống!",
                    Toast.LENGTH_SHORT).show();
            getActivity().finish();
        });

        txtUser_name = mainView.findViewById(R.id.account_user_name);
        txtUser_name.setText(HomeActivity.user.getName());
    }
}