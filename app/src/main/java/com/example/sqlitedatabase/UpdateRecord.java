package com.example.sqlitedatabase;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class UpdateRecord extends AppCompatActivity {

    EditText et1,et2,et3;
    Button btn1,btn2;
    Databasehelper dbHelper;
    String name, date, qualification;

    Calendar calendar;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_record);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        dbHelper = new Databasehelper(UpdateRecord.this);
        Toolbar toolbar = findViewById(R.id.tb1);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UpdateRecord.this, MainActivity.class);
                startActivity(i);
            }
        });

        name = getIntent().getStringExtra("name");
        date = getIntent().getStringExtra("dob");
        qualification = getIntent().getStringExtra("qualification");

        et1.setText(name);
        et2.setText(date);
        et3.setText(qualification);

        et3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        UpdateRecord.this,
                        (DatePicker view, int selectedYear, int selectedMonth, int selectedDay) -> {
                            String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                            et3.setText(selectedDate);
                            et3.setFocusable(false);
                        },
                        year, month, day
                );

                datePickerDialog.show();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.updateStudentData(name,et1.getText().toString(),et3.getText().toString(),et2.getText().toString());
                Toast.makeText(UpdateRecord.this, "Course Updated..", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateRecord.this, MainActivity.class);
                startActivity(i);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteStudentData(name);
                Toast.makeText(UpdateRecord.this, "Deleted the course", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateRecord.this, MainActivity.class);
                startActivity(i);
            }
        });


    }
}