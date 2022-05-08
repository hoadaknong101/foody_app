package hcmute.edu.vn.phamdinhquochoa.foodyapp;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UserInformationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText edUser_name, edUser_phone, edUser_DoB;
    private Button btnChange, btnCancel;
    private Spinner spUser_gender;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
        referencesComponent();
        edUser_name.setText(HomeActivity.user.getName());
        edUser_phone.setText(HomeActivity.user.getPhone());
        edUser_DoB.setText(HomeActivity.user.getDateOfBirth());

        switch (HomeActivity.user.getGender()){
            case "Male":
                break;
            case "Female":
                break;
            default:
                break;
        }
    }

    private void referencesComponent(){
        edUser_name = findViewById(R.id.editText_user_name);
        edUser_phone = findViewById(R.id.editText_user_phone);
        edUser_DoB = findViewById(R.id.user_birthday_pick);
        edUser_DoB.setOnClickListener(view -> PickDate());

        spUser_gender = findViewById(R.id.spinner_user_gender);
        ArrayAdapter<CharSequence> genders = ArrayAdapter.createFromResource(this, R.array.genders, android.support.design.R.layout.support_simple_spinner_dropdown_item);
        genders.setDropDownViewResource(android.support.design.R.layout.support_simple_spinner_dropdown_item);
        spUser_gender.setAdapter(genders);
        spUser_gender.setOnItemSelectedListener(this);

        btnChange = findViewById(R.id.btnChangeUserInformation);
        btnChange.setOnClickListener(view -> {

        });

        btnCancel = findViewById(R.id.btnCancelChangeUserInformation);
        btnCancel.setOnClickListener(view -> finish());
    }

    private void PickDate(){
        calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(this, (datePicker, year, month, day) -> {
            calendar.set(year, month, day);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            edUser_DoB.setText(dateFormat.format(calendar.getTime()));
        }, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        spUser_gender.setTextAlignment(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}