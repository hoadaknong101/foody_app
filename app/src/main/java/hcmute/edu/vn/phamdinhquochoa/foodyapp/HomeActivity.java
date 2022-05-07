package hcmute.edu.vn.phamdinhquochoa.foodyapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.User;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.fragments.*;

public class HomeActivity extends AppCompatActivity {

    public static User user;
    private Fragment homeFragment, savedFragment, chatFragment, notifyFragment, profileFragment;

    private void referenceFragment(){
        homeFragment = new HomeFragment();
        savedFragment = new SavedFragment();
        chatFragment = new ChatFragment();
        notifyFragment = new NotifyFragment();
        profileFragment = new ProfileFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        referenceFragment();

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Boolean isCartClick = intent.getBooleanExtra("isCartClick",false);
        if(isCartClick){
            Fragment fragment = chatFragment;
            loadFragment(fragment);
            navigation.getMenu().getItem(2).setChecked(true);
        }else{
            Fragment fragment = homeFragment;
            loadFragment(fragment);
        }
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        loadFragment(homeFragment);
                        return true;
                    case R.id.navigation_saved:
                        loadFragment(savedFragment);
                        return true;
                    case R.id.navigation_chat:
                        loadFragment(chatFragment);
                        return true;
                    case R.id.navigation_notify:
                        loadFragment(notifyFragment);
                        return true;
                    case R.id.navigation_profile:
                        loadFragment(profileFragment);
                        return true;
                }
                return false;
            };

    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}