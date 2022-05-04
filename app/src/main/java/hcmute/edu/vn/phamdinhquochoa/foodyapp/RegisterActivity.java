package hcmute.edu.vn.phamdinhquochoa.foodyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.User;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.dao.DAO;

public class RegisterActivity extends AppCompatActivity {
    private EditText txtUsername, txtPassword, txtPasswordConfirm;
    private String username, password, confirm;
    private Button btnSignup, btnLogin;
    public DAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        referenceComponent();
        dao = new DAO(this);
    }

    private void referenceComponent(){
        txtUsername = findViewById(R.id.editText_username_signup);
        txtPassword = findViewById(R.id.editText_password_signup);
        txtPasswordConfirm = findViewById(R.id.editText_password_signup_confirm);

        btnSignup = findViewById(R.id.button_signup_signup);
        btnSignup.setOnClickListener(view -> {
            username = txtUsername.getText().toString().trim();
            password = txtPassword.getText().toString().trim();
            confirm = txtPasswordConfirm.getText().toString().trim();

            if(username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                Toast.makeText(this, "Vui lòng điền đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            if(dao.UserExited(username)){
                Toast.makeText(this, "Người dùng đã tồn tại!", Toast.LENGTH_SHORT).show();
            } else {
                dao.addUser(new User(null, "", "Male", "1/1/2000", "", username, password));
                Toast.makeText(this, "Tạo tài khoản thành công!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("username", username);
                intent.putExtra("password", password);
                setResult(123, intent);
                finish();
            }
        });

        btnLogin = findViewById(R.id.button_login_signup);
        btnLogin.setOnClickListener(view -> {
            finish();
        });
    }
}