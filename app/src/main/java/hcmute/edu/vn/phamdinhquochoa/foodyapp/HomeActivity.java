package hcmute.edu.vn.phamdinhquochoa.foodyapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.User;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.fragments.*;

public class HomeActivity extends AppCompatActivity {

    private static int clickToLogout;
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
        clickToLogout = 0;

        referenceFragment();

        Intent intent = getIntent();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        String request = intent.getStringExtra("request");
        if(request != null) {
            switch (request) {
                case "payment":
                case "history":
                case "check":
                case "cart":
                    loadFragment(chatFragment);
                    navigation.getMenu().getItem(2).setChecked(true);
                    break;
                case "hint":
                    loadFragment(notifyFragment);
                    navigation.getMenu().getItem(2).setChecked(true);
                    break;
                default:
                    loadFragment(homeFragment);
                    break;
            }
        } else {
            loadFragment(homeFragment);
        }
    }

    @Override
    public void onBackPressed() {
        clickToLogout++;

        if(clickToLogout > 1)
            finish();
        else {
            Toast.makeText(this, "Click thêm lần nữa để đăng xuất!", Toast.LENGTH_SHORT).show();
        }

        new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                clickToLogout = 0;
            }
        }.start();
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

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
//        transaction.addToBackStack(null);
        transaction.setTransition(FragmentTransaction.TRANSIT_NONE);
        transaction.commit();
    }
}