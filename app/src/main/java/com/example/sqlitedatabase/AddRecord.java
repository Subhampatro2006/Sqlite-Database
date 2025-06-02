package com.example.sqlitedatabase;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Calendar;

public class AddRecord extends AppCompatActivity {

    EditText edt1, edt2;
    Spinner sp1;
    Toolbar tb1;
    Button btn1;

    String name, qualification, dob;

    Databasehelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        edt1 = findViewById(R.id.edt1); // Name
        edt2 = findViewById(R.id.edt2); // DOB
        sp1 = findViewById(R.id.sp1);   // Qualification
        tb1 = findViewById(R.id.tb1);   // Toolbar
        btn1 = findViewById(R.id.btn1); // Button

        dbHelper = new Databasehelper(this); // SQLite helper instance

        edt2.setOnClickListener(v -> showDatePicker());

        setSupportActionBar(tb1);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        tb1.setNavigationOnClickListener(v -> {
            Intent i = new Intent(AddRecord.this, MainActivity.class);
            startActivity(i);
            finish();
        });

        String[] qualifications = {
                "BTech", "MBBS", "BSc", "BCA", "BBA", "BCom", "BA", "BPharm",
                "BDS", "BAMS", "BHMS", "BPT", "BFA", "BMS", "BMM", "B.Ed (Integrated)",
                "B.Voc", "BSc Nursing", "BSc Agriculture", "CA", "CS", "CMA", "Hotel Management",
                "Fashion Designing", "Interior Designing", "Animation & Multimedia", "Event Management",
                "BA LLB (Integrated Law)", "BDes", "Pilot Training", "Merchant Navy", "NDA",
                "Digital Marketing", "Travel & Tourism", "Journalism & Mass Communication"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, qualifications);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter);

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                qualification = qualifications[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                qualification = "";
            }
        });

        btn1.setOnClickListener(v -> {
            name = edt1.getText().toString().trim();
            dob = edt2.getText().toString().trim();

            if (name.isEmpty() || dob.isEmpty() || qualification.isEmpty()) {
                Toast.makeText(AddRecord.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                dbHelper.addStudentData(name, dob, qualification);
                Toast.makeText(AddRecord.this, "Data added successfully", Toast.LENGTH_SHORT).show();
                edt1.setText("");
                edt2.setText("");
                sp1.setSelection(0);
            }
        });
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) ->
                        edt2.setText(selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear),
                year, month, day);
        datePickerDialog.show();
    }
}
