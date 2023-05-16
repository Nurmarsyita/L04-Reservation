package com.example.l04_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etMobile;
    EditText etNoOfPax;
    DatePicker dp;
    TimePicker tp;
    RadioGroup preferredTable;
    RadioButton smoking;
    RadioButton nonSmoking;
    Button btnconfirm;
    Button btnreset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etMobile = findViewById(R.id.editTextPhone);
        etNoOfPax = findViewById(R.id.editTextPax);
        dp = findViewById(R.id.datePicker);
        tp = findViewById(R.id.timePicker);
        preferredTable = findViewById(R.id.radioGroupPreferred);
        btnconfirm = findViewById(R.id.buttonConfirm);
        btnreset = findViewById(R.id.buttonReset);

        smoking = findViewById(R.id.radioButtonSmoking);
        nonSmoking = findViewById(R.id.radioButtonNone);


        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getName = etName.getText().toString().trim();
                String getMobile = etMobile.getText().toString().trim();
                String getPax = etNoOfPax.getText().toString().trim();
                int getDay = dp.getDayOfMonth();
                int getMonth = dp.getMonth() + 1;
                int getYear = dp.getYear();
                int getHour = tp.getCurrentHour();
                int getMins = tp.getCurrentMinute();
                int preferred = preferredTable.getCheckedRadioButtonId();

                if (getName.isEmpty() && getMobile.isEmpty() && getPax.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Cannot be booked! Please fill up all required information!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Name: " + getName, Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, "Mobile Number: " + getMobile, Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, "No. of pax: " + getPax, Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, "Date: " + getDay + "/" + getMonth + "/" + getYear, Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, "Time: " + getHour + ":" + getMins, Toast.LENGTH_SHORT).show();

                    if (preferred == R.id.radioButtonSmoking) {
                        Toast.makeText(MainActivity.this, "Preferred table: Smoking Area", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Preferred table: Non-Smoking Area", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dp.updateDate(2023, 6-1,1);
                tp.setCurrentHour(19);
                tp.setCurrentMinute(30);
                etName.setText("");
                etMobile.setText("");
                etNoOfPax.setText("");
            }
        });

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if (tp.getCurrentHour() < 8) {
                    tp.setCurrentHour(8);
                    tp.setCurrentMinute(00);
                    Toast.makeText(MainActivity.this, "We are only open from 8AM to 8PM.", Toast.LENGTH_SHORT).show();
                } else if (tp.getCurrentHour() > 20) {
                    tp.setCurrentHour(20);
                    tp.setCurrentMinute(00);
                    Toast.makeText(MainActivity.this, "We are only open from 8AM to 8PM.", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}