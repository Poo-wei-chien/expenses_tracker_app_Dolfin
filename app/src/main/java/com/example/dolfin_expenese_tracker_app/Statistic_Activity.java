package com.example.dolfin_expenese_tracker_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Statistic_Activity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    TextView title_day, title_week, title_month, title_year;
    DAOExpenses dao;
    FirebaseRecyclerAdapter adapter;
    boolean isLoading = false;
    String key = null;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Statistics");
        swipeRefreshLayout = findViewById(R.id.swip);
        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        dao = new DAOExpenses();
        FirebaseRecyclerOptions<Expenses> option = new FirebaseRecyclerOptions.Builder<Expenses>()
                .setQuery(dao.get(), new SnapshotParser<Expenses>() {
                    @NonNull
                    @Override
                    public Expenses parseSnapshot(@NonNull DataSnapshot snapshot) {
                        Expenses exp = snapshot.getValue(Expenses.class);
                        exp.setKey(snapshot.getKey());
                        return exp;
                    }
                }).build();

        adapter = new FirebaseRecyclerAdapter(option) {
            @Override
            protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull Object model) {
                ExpensesVH vh = (ExpensesVH) holder;
                Expenses emp = (Expenses) model;
                vh.txt_name.setText(emp.getName());
<<<<<<< HEAD
                vh.txt_date.setText((CharSequence) emp.getDate());
=======
                vh.txt_date.setText(emp.getDate());
>>>>>>> 7bc07af (little bit update)
                vh.txt_amount.setText(emp.getAmount());
                vh.txt_option.setOnClickListener(v->{
                    PopupMenu popupMenu = new PopupMenu(Statistic_Activity.this, vh.txt_option);
                    popupMenu.inflate(R.menu.option_menu_rv);
                    popupMenu.setOnMenuItemClickListener(item -> {
                        switch (item.getItemId()){
                            case  R.id.menu_edit:
                                Intent intent = new Intent(Statistic_Activity.this, MainActivity.class);
                                intent.putExtra("EDIT", emp);
                                startActivity(intent);
                                break;
                            case R.id.menu_remove:
                                //Remove database manually
                                DAOExpenses dao = new DAOExpenses();
                                dao.remove(emp.getKey()).addOnSuccessListener(suc ->{
                                    Toast.makeText(Statistic_Activity.this, "Record is removed", Toast.LENGTH_SHORT).show();
                                }).addOnFailureListener(er -> {
                                    Toast.makeText(Statistic_Activity.this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
                                });
                                break;

                        }
                        return false;
                    });
                    popupMenu.show();
                });
            }

            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(Statistic_Activity.this).inflate(R.layout.layout_item_rv, parent, false);
                return new ExpensesVH(view);
            }
<<<<<<< HEAD
=======

            @Override
            public void onDataChanged() {
                Toast.makeText(Statistic_Activity.this, "Data Changed", Toast.LENGTH_SHORT);
            }
>>>>>>> 7bc07af (little bit update)
        };
        recyclerView.setAdapter(adapter);


        //Graph start here
        pieChart = findViewById(R.id.pieChart);
        setupPieChart();
        loadPieChart(0.8f,0.7f,0.1f,0.25f, 0.76f);

        //Button changed
        title_day = findViewById(R.id.title_day);
        title_week = findViewById(R.id.title_week);
        title_month = findViewById(R.id.title_month);
        title_year = findViewById(R.id.title_year);

        title_day.setOnClickListener(new Clik());
        title_week.setOnClickListener(new Clik());
        title_month.setOnClickListener(new Clik());
        title_year.setOnClickListener(new Clik());

    }
    
    //Methods for pie chart
    private void setupPieChart(){
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Expenses Category");
        pieChart.setCenterTextSize(20);
        pieChart.getDescription().setEnabled(false);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(true);
        l.setFormSize(5f);
    }

    public void loadPieChart(float f1, float f2, float f3, float f4, float f5){
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(f1,"Food & Dining"));
        entries.add(new PieEntry(f2,"Medical"));
        entries.add(new PieEntry(f3,"Entertainment"));
        entries.add(new PieEntry(f4,"Electricity & Gas"));
        entries.add(new PieEntry(f5,"Housing"));
        
        ArrayList<Integer> colors = new ArrayList<>();
        for (int color: ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }

        for (int color: ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries,  " ");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.setEntryLabelTextSize(10f);
        pieChart.invalidate();

<<<<<<< HEAD

=======
>>>>>>> 7bc07af (little bit update)
        pieChart.animateY(1400, Easing.EaseInOutQuad);
    }
    

    //Multiple click of the button
    public class Clik implements View.OnClickListener{
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.title_day:
                    title_day.setTextColor(getResources().getColor(R.color.white));
                    title_day.setBackground(getResources().getDrawable(R.drawable.blue_button_bg));
                    title_week.setTextColor(getResources().getColor(R.color.black));
                    title_week.setBackground(null);
                    title_month.setTextColor(getResources().getColor(R.color.black));
                    title_month.setBackground(null);
                    title_year.setTextColor(getResources().getColor(R.color.black));
                    title_year.setBackground(null);
                    setupPieChart();
                    loadPieChart(0.8f,0.7f,0.1f,0.25f, 0.76f);
                    break;
                case R.id.title_week:
                    title_day.setTextColor(getResources().getColor(R.color.black));
                    title_day.setBackground(null);
                    title_week.setTextColor(getResources().getColor(R.color.white));
                    title_week.setBackground(getResources().getDrawable(R.drawable.blue_button_bg));
                    title_month.setTextColor(getResources().getColor(R.color.black));
                    title_month.setBackground(null);
                    title_year.setTextColor(getResources().getColor(R.color.black));
                    title_year.setBackground(null);
                    setupPieChart();
                    loadPieChart(0.7f,0.3f,0.15f,0.5f, 0.36f);
                    break;
                case R.id.title_month:
                    title_day.setTextColor(getResources().getColor(R.color.black));
                    title_day.setBackground(null);
                    title_week.setTextColor(getResources().getColor(R.color.black));
                    title_week.setBackground(null);
                    title_month.setTextColor(getResources().getColor(R.color.white));
                    title_month.setBackground(getResources().getDrawable(R.drawable.blue_button_bg));
                    title_year.setTextColor(getResources().getColor(R.color.black));
                    title_year.setBackground(null);
                    setupPieChart();
                    loadPieChart(0.7f,0.3f,0.19f,0.53f, 0.43f);
                    break;
                case R.id.title_year:
                    title_day.setTextColor(getResources().getColor(R.color.black));
                    title_day.setBackground(null);
                    title_week.setTextColor(getResources().getColor(R.color.black));
                    title_week.setBackground(null);
                    title_month.setTextColor(getResources().getColor(R.color.black));
                    title_month.setBackground(null);
                    title_year.setTextColor(getResources().getColor(R.color.white));
                    title_year.setBackground(getResources().getDrawable(R.drawable.blue_button_bg));
                    setupPieChart();
                    loadPieChart(0.7f,0.23f,0.3f,0.35f, 0.45f);
                    break;
                default:
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.startListening();
    }



}