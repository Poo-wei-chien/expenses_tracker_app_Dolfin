package com.example.dolfin_expenese_tracker_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.FirebaseFirestore;


public class MainActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Expenses");
        EditText edit_category = findViewById(R.id.txt_category);
        EditText edit_name = findViewById(R.id.txt_name);
        EditText edit_amount = findViewById(R.id.txt_amount);
        EditText edit_date = findViewById(R.id.txt_date);
        EditText edit_invoice = findViewById(R.id.txt_invoice);
        button = findViewById(R.id.btn_submit);

/*        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Statistic.class);
            startActivity(intent);
        });*/

        //Set up edit button in menu bar
        DAOExpenses dao = new DAOExpenses();
        Expenses exp_edit = (Expenses) getIntent().getSerializableExtra("EDIT");
        if (exp_edit != null) {
            button.setText("UPDATE");
            edit_category.setText(exp_edit.getCategory());
            edit_name.setText(exp_edit.getName());
            edit_amount.setText(exp_edit.getAmount());
            edit_date.setText((CharSequence) exp_edit.getDate());
            edit_invoice.setText(exp_edit.getInvoice());
        } else {
            button.setText("SUBMIT");
        }

        button.setOnClickListener(v ->
        {
            Intent intent = new Intent(MainActivity.this, Statistic_Activity.class);
            startActivity(intent);
            //Insert database
            Expenses exp = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                exp = new Expenses(edit_category.getText().toString(), edit_name.getText().toString(), edit_amount.getText().toString(), edit_date.getText().toString(), edit_invoice.getText().toString());
            }
            if (exp_edit == null) {
                dao.add(exp).addOnSuccessListener(suc -> {
                    Toast.makeText(this, "Record is inserted", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er -> {
                    Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            } else {
                //Update db manually
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("category", edit_category.getText().toString());
                hashMap.put("name", edit_name.getText().toString());
                hashMap.put("amount", edit_amount.getText().toString());
                hashMap.put("date", edit_date.getText().toString());
                hashMap.put("invoice", edit_invoice.getText().toString());
                dao.update(exp_edit.getKey(), hashMap).addOnSuccessListener(suc -> {
                    Toast.makeText(this, "Record is updated", Toast.LENGTH_SHORT).show();
                    finish();
                }).addOnFailureListener(er -> {
                    Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }

        });

    }
}