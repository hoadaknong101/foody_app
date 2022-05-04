package hcmute.edu.vn.phamdinhquochoa.foodyapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import hcmute.edu.vn.phamdinhquochoa.foodyapp.fragments.ChatFragment;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.fragments.HomeFragment;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.fragments.NotifyFragment;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.fragments.ProfileFragment;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.fragments.SavedFragment;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Intent intent  = getIntent();
        Boolean isCartClick = intent.getBooleanExtra("isCartClick",false);
        if(isCartClick){
            Fragment fragment = new ChatFragment();
            loadFragment(fragment);
            navigation.getMenu().getItem(2).setChecked(true);
        }else{
            Fragment fragment = new HomeFragment();
            loadFragment(fragment);
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        fragment = new HomeFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.navigation_saved:
                        fragment = new SavedFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.navigation_chat:
                        fragment = new ChatFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.navigation_notify:
                        fragment = new NotifyFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.navigation_profile:
                        fragment = new ProfileFragment();
                        loadFragment(fragment);
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