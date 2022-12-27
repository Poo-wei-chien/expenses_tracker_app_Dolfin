package com.example.dolfin_expenese_tracker_app;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ExpensesVH extends RecyclerView.ViewHolder {
    public TextView txt_name, txt_date, txt_amount,txt_option;

    public ExpensesVH(@NonNull View itemView){
        super(itemView);
        txt_name = itemView.findViewById(R.id.txt_name);
        txt_date = itemView.findViewById(R.id.txt_date);
        txt_amount = itemView.findViewById(R.id.txt_amount);
        txt_option = itemView.findViewById(R.id.txt_option);
    }
}
