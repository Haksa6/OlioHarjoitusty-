package com.example.olioht;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

import javax.xml.transform.Result;

// Not a real AsyncTask class

public class Async extends AsyncTask<String, Void, String> {

    int calories, status;
    String response = null;
    String name;

    @Override
    protected String doInBackground(String... strings) {
        String MyWeb = strings[0];
        try {
            URL url = new URL(MyWeb);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String line = "";
            while((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            response = sb.toString();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public int checkExist (String e) {
        try {
            JSONObject jsonObject = new JSONObject(e);
            status = jsonObject.getInt("status");

        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
        return status;
    }

    public int GetCalories(String c) {
        try {
            JSONObject jsonObject = new JSONObject(c);
            calories = jsonObject.getJSONObject("product").getJSONObject("nutriments").getInt("energy-kcal_100g");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return calories;
    }

    public String GetName (String n) {
        try {
            JSONObject jsonObject = new JSONObject(n);
            name = jsonObject.getJSONObject("product").getString("product_name");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return name;
    }

}
