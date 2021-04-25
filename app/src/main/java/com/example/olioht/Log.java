package com.example.olioht;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Log  {
    String filename = "log.csv";
    Context context;
    String[] dataAll;
    String s, sdataAll = "Time, Product, Total calories \n";
    int count = 0, counter = 0;
    float caloryTracker = 0;


    public  Log (Context context) {
        this.context = context;
    }



    public void add(String time, String product, String calories) {
        try {
            FileOutputStream os = context.openFileOutput(filename, Context.MODE_APPEND);
            OutputStreamWriter csvWriter = new OutputStreamWriter(os);


            csvWriter.append(time);
            csvWriter.append(";");
            csvWriter.append(product);
            csvWriter.append(";");
            csvWriter.append(calories);
            csvWriter.append("\n");

            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readAll() {
        try {
            InputStream ins = context.openFileInput(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            s = "";

            while ((s = br.readLine()) != null) { // go through the file to see how many entries
                count++;
            }
            InputStream in = context.openFileInput(filename);
            BufferedReader bre = new BufferedReader(new InputStreamReader(in));

            while ((s = bre.readLine()) != null) { // get the last 10
                dataAll = s.split(";");
                counter++;
                if (count < 10 || (count > 10 && counter > (count - 10))) {
                    sdataAll += dataAll[0] + ", " + dataAll[1] + ", " + dataAll[2] + "\n";
                }

            }
            in.close();
            ins.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        counter = 0;
        count = 0;
        return sdataAll;
    }

    public String readlast() {
        try {
            InputStream ins = context.openFileInput(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            s = "";

            while ((s = br.readLine()) != null) { // go through the file to see how many entries
                count++;
            }
            InputStream in = context.openFileInput(filename);
            BufferedReader bre = new BufferedReader(new InputStreamReader(in));

            while ((s = bre.readLine()) != null) { // get the last
                dataAll = s.split(";");
                counter++;
                if ((count - counter) == 0) {
                    sdataAll += dataAll[0] + ", " + dataAll[1] + ", " + dataAll[2] + "\n";
                }

            }
            in.close();
            ins.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        counter = 0;
        count = 0;
        return sdataAll;
    }

    public String getCal() {
        try {
            InputStream ins = context.openFileInput(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            s = "";

            while ((s = br.readLine()) != null) {
                dataAll = s.split(";");
                caloryTracker += Float.parseFloat(dataAll[2]);
            }
            sdataAll = String.valueOf(caloryTracker);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sdataAll;
    }
}
