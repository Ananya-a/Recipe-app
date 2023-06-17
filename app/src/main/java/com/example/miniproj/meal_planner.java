package com.example.miniproj;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class meal_planner extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private EditText mondayMealEditText;
    private EditText tuesdayMealEditText;
    private EditText wednesdayMealEditText;
    private EditText thursdayMealEditText;
    private EditText fridayMealEditText;
    private EditText saturdayMealEditText;
    private EditText sundayMealEditText;
    private EditText noteEditText;

    private Button saveBtn;
    @SuppressLint("MissingInflatedId")

    private class MealTextWatcher implements TextWatcher {
        private String key;

        public MealTextWatcher(String key) {
            this.key = key;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // No implementation needed
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // No implementation needed
        }

        @Override
        public void afterTextChanged(Editable editable) {
            editor.putString(key, editable.toString());
            editor.apply();
        }
    }

    private void saveData() {
        editor.putString("monday_meal", mondayMealEditText.getText().toString());
        editor.putString("tuesday_meal", tuesdayMealEditText.getText().toString());
        // ... save data for other EditText fields

        editor.apply();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_planner);

        sharedPreferences = getSharedPreferences("MealPlanner", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // Initialize the EditText fields
        mondayMealEditText = findViewById(R.id.mon_id);
        tuesdayMealEditText = findViewById(R.id.tue_id);
        wednesdayMealEditText = findViewById(R.id.wed_id);
        thursdayMealEditText = findViewById(R.id.thur_id);
        fridayMealEditText = findViewById(R.id.fri_id);
        saturdayMealEditText = findViewById(R.id.sat_id);
        sundayMealEditText = findViewById(R.id.sun_id);
        noteEditText = findViewById(R.id.note_id);
        saveBtn=findViewById(R.id.saveBtn);

        mondayMealEditText.addTextChangedListener(new MealTextWatcher("monday_meal"));
        tuesdayMealEditText.addTextChangedListener(new MealTextWatcher("tuesday_meal"));
        wednesdayMealEditText.addTextChangedListener(new MealTextWatcher("wednesday_meal"));
        thursdayMealEditText.addTextChangedListener(new MealTextWatcher("thursday_meal"));
        fridayMealEditText.addTextChangedListener(new MealTextWatcher("friday_meal"));
        saturdayMealEditText.addTextChangedListener(new MealTextWatcher("saturday_meal"));
        sundayMealEditText.addTextChangedListener(new MealTextWatcher("sunday_meal"));
        noteEditText.addTextChangedListener(new MealTextWatcher("note"));

        mondayMealEditText.setText(sharedPreferences.getString("monday_meal", ""));
        tuesdayMealEditText.setText(sharedPreferences.getString("tuesday_meal", ""));
        wednesdayMealEditText.setText(sharedPreferences.getString("wednesday_meal", ""));
        thursdayMealEditText.setText(sharedPreferences.getString("thursday_meal", ""));
        fridayMealEditText.setText(sharedPreferences.getString("friday_meal", ""));
        saturdayMealEditText.setText(sharedPreferences.getString("saturday_meal", ""));
        sundayMealEditText.setText(sharedPreferences.getString("sunday_meal", ""));
        noteEditText.setText(sharedPreferences.getString("note", ""));


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
    }
}