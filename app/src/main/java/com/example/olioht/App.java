package com.example.olioht;

import android.content.Context;
import java.io.Serializable;
import java.util.concurrent.ExecutionException;

public class App implements Serializable {

    float floatAmount, finalcalories;
    int statusA, statusH, status, caloriesA;
    String bHolder;
    String jsonCode;
    String dateA;
    String nameA, dataAll, datalast;
    String fcaloriesString, dataCal, BMI;

    Context context;
    Date date = new Date();

    public App(Context context) {
        this.context = context;

    }



    public int SaveEntry(String bconv, String aconv) {
        try { // check if amount has been given or is empty
            floatAmount = Float.parseFloat(aconv);
            statusA = 1;
        } catch (NumberFormatException e) {
            statusA = 0;
        }
        bHolder = "https://world.openfoodfacts.org/api/v0/product/" + bconv + ".json"; // the full json header
        Async product = new Async(); // connects to the internet
        try {
            jsonCode = product.execute(bHolder).get(); // get the json code, couldn't get the async to work properly
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        statusH = product.checkExist(jsonCode); // check if the barcode exists/is on the database
        if (statusH == 1 && statusA == 1) { // if product exists and amount has been given, logs it and returns 1
            Log log = new Log(context);
            dateA = date.getNow();
            caloriesA = product.GetCalories(jsonCode);
            nameA = product.GetName(jsonCode);
            finalcalories = (floatAmount/100) * caloriesA;
            finalcalories = Math.round(finalcalories);
            fcaloriesString = String.valueOf(finalcalories);
            log.add(dateA, nameA, fcaloriesString);
            status = 1;
        } else if (statusH == 0 || statusA == 0) { // if product doesn't exist or amount has not been given returns 0
            status = 0;
        }

        return status;
    }


    public String readEntries() {
        Log log = new Log(context);
        dataAll = log.readAll();

        return dataAll;
    }

    public String readLast() {
        Log log = new Log(context);
        datalast = log.readlast();
        return datalast;
    }

    public String getAllCal() {
        Log log = new Log(context);
        dataCal = log.getCal();
        return dataCal;
    }

    public String getBMI(String height, String weight) {
        PersonalData pd = new PersonalData();
        BMI = pd.getBMI(height, weight);
        return BMI;
    }

}