package com.example.olioht;

import android.content.Intent;

public class PersonalData {
    String BMI;
    float heighti, weighti, height2;
    double BMIi;


    //Calculates BMI, rounds it to 2 decimals
    public String getBMI(String height,String weight) {
        heighti = Float.parseFloat(height);
        weighti = Float.parseFloat(weight);
        height2 = (heighti * heighti);
        BMIi = (weighti / height2) * 10000;
        BMIi = Math.round(BMIi * 100.0) / 100.0;
        BMI = String.valueOf(BMIi);
        return BMI;
    }
}
