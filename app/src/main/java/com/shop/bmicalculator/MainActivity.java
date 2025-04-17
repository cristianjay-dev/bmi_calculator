package com.shop.bmicalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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
        EditText edtTxtWeight = findViewById(R.id.edtTxtWeight);
        double weight = Double.parseDouble(edtTxtWeight.getText().toString());
        EditText edtTxtHeight = findViewById(R.id.edtTxtHeight);
        String heightStr = edtTxtHeight.getText().toString();

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
        TextView txtBmi = findViewById(R.id.txtBMI);
        txtBmi.setText("Your BMI is: " + String.format("%.2f", bmi));

    }

    public void onResetClick(View view) {
        EditText edtTextWeight = findViewById(R.id.edtTxtWeight);
        EditText edtTextHeight = findViewById(R.id.edtTxtHeight);
        TextView txtBmi = findViewById(R.id.txtBMI);
        TextView txtResult = findViewById(R.id.txtResult);

        edtTextWeight.setText("");
        edtTextHeight.setText("");
        txtBmi.setText("Your BMI is: ");
        txtResult.setText("Result: ");
    }
}