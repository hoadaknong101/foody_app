package hcmute.edu.vn.phamdinhquochoa.foodyapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.User;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.dao.DAO;

public class LoginActivity extends AppCompatActivity {

    public static final String PREFERENCES = "store_info" ;
//    public static final Integer DEFAULT_USER_ID = 1;
    private SharedPreferences sharedPreferences;
    private EditText txtUsername, txtPassword;
    private DAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        referenceComponent();
        sharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        txtUsername.setText(sharedPreferences.getString("username", ""));
        txtPassword.setText(sharedPreferences.getString("password", ""));
        dao = new DAO(this);
    }

    private void referenceComponent(){
        txtPassword = findViewById(R.id.editText_password_login);
        txtUsername = findViewById(R.id.editText_username_login);

        Button btnLogin = findViewById(R.id.button_login_login);
        btnLogin.setOnClickListener(view -> {
            String username = txtUsername.getText().toString().trim();
            String password = txtPassword.getText().toString().trim();

            if(username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Vui lòng điền đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            User userExist = dao.getUserByUsernameAndPassword(username, password);

            boolean isRightAuthentication = false;
            if(userExist != null){
                isRightAuthentication = dao.signIn(userExist);
            }
            if(isRightAuthentication){
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", userExist.getUsername());
                editor.putString("password", userExist.getPassword());
                editor.apply();

//                Integer userId = sharedPreferences.getInt("UserID", DEFAULT_USER_ID);
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                HomeActivity.user = userExist;
                startActivity(intent);
            } else{
                Toast.makeText(this, "Sai thông tin đăng nhập", Toast.LENGTH_SHORT).show();
            }
        });

        Button btnSignup = findViewById(R.id.button_signup_login);
        btnSignup.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivityForResult(intent, 0);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null) {
            txtUsername.setText(data.getStringExtra("username"));
            txtPassword.setText(data.getStringExtra("password"));
        }
    }
}