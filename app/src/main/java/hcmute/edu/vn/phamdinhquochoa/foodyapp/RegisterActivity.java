package hcmute.edu.vn.phamdinhquochoa.foodyapp;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.Notify;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.NotifyToUser;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.User;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.dao.DAO;

public class RegisterActivity extends AppCompatActivity {
    private EditText txtUsername, txtPassword, txtPasswordConfirm;
    private String username, password, confirm;
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

        Button btnSignup = findViewById(R.id.button_signup_signup);
        btnSignup.setOnClickListener(view -> {
            username = txtUsername.getText().toString().trim();
            password = txtPassword.getText().toString().trim();
            confirm = txtPasswordConfirm.getText().toString().trim();

            if(username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                Toast.makeText(this, "Vui lòng điền đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }
            if(!confirm.equals(password)){
                Toast.makeText(this, "Mật khẩu xác nhận không hợp lệ!", Toast.LENGTH_SHORT).show();
                return;
            }

            if(dao.UserExited(username)){
                Toast.makeText(this, "Người dùng đã tồn tại!", Toast.LENGTH_SHORT).show();
            } else {
                dao.addUser(new User(null, "", "Male", "1/1/2000", "", username, password));
                Toast.makeText(this, "Tạo tài khoản thành công!", Toast.LENGTH_SHORT).show();

                // Make notify
                dao.addNotify(new Notify(1, "Chào mừng bạn mới!", "Cảm ơn bạn đã sử dụng Foody! \n" +
                        "Vui lòng điểu chỉnh thông tin cá nhân bằng cách click vào icon người dùng trong mục profile!",
                        dao.getDate()));
                dao.addNotifyToUser(new NotifyToUser(dao.getNewestNotifyId(), dao.getNewestUserId()));

                // Return Login
                Intent intent = new Intent();
                intent.putExtra("username", username);
                intent.putExtra("password", password);
                setResult(0, intent);
                finish();
            }
        });

        Button btnLogin = findViewById(R.id.button_login_signup);
        btnLogin.setOnClickListener(view -> finish());
    }
}