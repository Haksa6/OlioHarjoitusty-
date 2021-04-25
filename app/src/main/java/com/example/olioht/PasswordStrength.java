package com.example.olioht;

import android.widget.Toast;

import java.io.*;
import java.util.*;
public class PasswordStrength {

    public String getStrongNess(String first, String second)
    {
        // Checking lower alphabet in string
        int n = first.length();
        boolean hasLower = false, hasUpper = false,
                hasDigit = false, specialChar = false;
        Set<Character> set = new HashSet<Character>(
                Arrays.asList('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '+', '?', '=', '_'));
        for (char i : first.toCharArray())
        {
            if (Character.isLowerCase(i))
                hasLower = true;
            if (Character.isUpperCase(i))
                hasUpper = true;
            if (Character.isDigit(i))
                hasDigit = true;
            if (set.contains(i))
                specialChar = true;
        }

        // Strength of password
        if (n < 12){
            return "TOO SHORT";
        }
        else if (!hasLower){
           return "NO LOWER";
        }
        else if (!hasUpper){
            return "NO UPPER";
        }
        else if (!hasDigit){
            return "NO NUMBA";
        }
        else if (!specialChar){
            return "NO SPESSY";
        }
        else if (!first.equals(second)){
            return "NO MATCH";
        }else{
            return "GOOD";
        }
    }
}
