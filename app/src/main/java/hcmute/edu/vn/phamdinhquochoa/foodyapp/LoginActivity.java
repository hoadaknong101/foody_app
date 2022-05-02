package hcmute.edu.vn.phamdinhquochoa.foodyapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.User;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.dao.DAO;

public class LoginActivity extends AppCompatActivity {

    public static final String PREFERENCES = "store_info" ;
    public static final Integer DEFAULT_USER_ID = 1;
    private EditText txtUsername, txtPassword;
    private DAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        referenceComponent();
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        dao = new DAO(this);

        Button btnDangNhap = findViewById(R.id.button_login_login);
        btnDangNhap.setOnClickListener(view -> {
            String username = txtUsername.getText().toString().trim();
            System.out.println("Username: " + username);
            String password = txtPassword.getText().toString().trim();
            System.out.println("Password: " +password);
            User userExist = dao.getUserByUsernameAndPassword(username,password);

            boolean isRightAuthentication = false;
            if(userExist != null){
                System.out.println(userExist);
                isRightAuthentication = dao.signIn(userExist);
            }
            if(isRightAuthentication){
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("UserID",userExist.getId());
                editor.apply();
                Integer userId = sharedPreferences.getInt("UserID",DEFAULT_USER_ID);

                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            }else{
                Toast.makeText(this, "Sai thông tin đăng nhập", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void referenceComponent(){
        txtPassword = findViewById(R.id.editText_password_login);
        txtUsername = findViewById(R.id.editText_username_login);
    }
}