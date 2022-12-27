package com.example.dolfin_expenese_tracker_app;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import com.google.firebase.database.Exclude;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;

public class Expenses implements Serializable {
    @Exclude
    private String key;
    private String category;
    private String name;
    private String  amount;
    private String date;

    private String invoice;


    public Expenses() {
    }

    public Expenses(String category, String name, String amount, String  date) {
        this.category = category;
        this.name = name;
        this.amount = amount;
        this.date = date;
    }

    //Additional constructor for invoice bcuz it's not necessary
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Expenses(String category, String name, String amount, String  date, String invoice) {
        this.category = category;
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.invoice = invoice;
    }

    public String getKey() {
        return key;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }

    public String  getDate() {
        return date;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setDate(String  date) {
        this.date = date;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }
}
