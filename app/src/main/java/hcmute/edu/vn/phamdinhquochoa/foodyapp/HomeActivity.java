package hcmute.edu.vn.phamdinhquochoa.foodyapp;

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

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Fragment fragment = new HomeFragment();
        loadFragment(fragment);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    Toast.makeText(HomeActivity.this,"Trang chu", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_saved:
                    fragment = new SavedFragment();
                    loadFragment(fragment);
                    Toast.makeText(HomeActivity.this,"Da luu", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_chat:
                    fragment = new ChatFragment();
                    loadFragment(fragment);
                    Toast.makeText(HomeActivity.this,"Tin nhan", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_notify:
                    fragment = new NotifyFragment();
                    loadFragment(fragment);
                    Toast.makeText(HomeActivity.this,"Thong bao", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_profile:
                    fragment = new ProfileFragment();
                    loadFragment(fragment);
                    Toast.makeText(HomeActivity.this,"Thong tin ca nhan", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }
    };
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}