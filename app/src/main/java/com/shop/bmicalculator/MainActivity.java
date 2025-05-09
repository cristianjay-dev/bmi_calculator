package com.shop.bmicalculator;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edtTxtWeight;
    EditText edtTxtHeight;
    EditText edtTxtAge;
    RadioButton rbtnMale;
    RadioButton rbtnFemale;
    TextView txtBmi;
    TextView txtResult;
    TextView txtAge;
    TextView txtGender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void onCalculateClick(View view) {
        edtTxtWeight = findViewById(R.id.edtTxtWeight);
        String weightStr = edtTxtWeight.getText().toString().trim();
        edtTxtHeight = findViewById(R.id.edtTxtHeight);
        String heightStr = edtTxtHeight.getText().toString().trim();
        edtTxtAge = findViewById(R.id.edtTxtAge);
        String ageStr = edtTxtAge.getText().toString().trim();

        if (weightStr.isEmpty() || heightStr.isEmpty() || ageStr.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }
            double weight;
            int age;
            try {
                weight = Double.parseDouble(weightStr);
                age = Integer.parseInt(ageStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
                return;
            }

            // Splits the height string into feet and inches
            String[] heightParts = heightStr.split("\\.");
            int feet = Integer.parseInt(heightParts[0]);
            int inches = (heightParts.length > 1) ? Integer.parseInt(heightParts[1]) : 0;

            int totalInches = (feet * 12) + inches;
            double heightInMeters = totalInches * 0.0254; // Convert inches to meters

            double bmi = weight / (heightInMeters * heightInMeters);

            TextView txtResult = findViewById(R.id.txtResult);
            if (bmi < 18.5) {
                txtResult.setText("Result: Underweight");
            } else if (bmi >= 18.5 && bmi <= 24.9) {
                txtResult.setText("Result: Normal weight");
            } else if (bmi >= 25 && bmi <= 29.9) {
                txtResult.setText("Result: Overweight");
            } else {
                txtResult.setText("Result: Obese");
            }
            txtBmi = findViewById(R.id.txtBMI);
            txtBmi.setText("Your BMI is: " + String.format("%.2f", bmi));

            txtAge = findViewById(R.id.txtAge);
            txtAge.setText("Age: " + age);
            rbtnMale = findViewById(R.id.rbtnMale);
            rbtnFemale = findViewById(R.id.rbtnFemale);

            txtGender = findViewById(R.id.txtGender);
            if (rbtnMale.isChecked()) {
                txtGender.setText("Gender: Male");
            } else if (rbtnFemale.isChecked()) {
                txtGender.setText("Gender: Female");
            }


    }

    public void onResetClick(View view) {
        edtTxtWeight = findViewById(R.id.edtTxtWeight);
        edtTxtHeight = findViewById(R.id.edtTxtHeight);
        edtTxtAge = findViewById(R.id.edtTxtAge);
        rbtnMale = findViewById(R.id.rbtnMale);
        rbtnFemale = findViewById(R.id.rbtnFemale);
        txtBmi = findViewById(R.id.txtBMI);
        txtResult = findViewById(R.id.txtResult);
        txtAge = findViewById(R.id.txtAge);

        rbtnMale.setChecked(false);
        rbtnFemale.setChecked(false);
        edtTxtWeight.setText("");
        edtTxtHeight.setText("");
        edtTxtAge.setText("");
        txtBmi.setText("Your BMI is: ");
        txtResult.setText("Result: ");
        txtAge.setText("Age: ");
    }
}