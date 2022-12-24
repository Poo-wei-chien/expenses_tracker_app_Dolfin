package com.example.dolfin_expenese_tracker_app;

public class DataPoint {
    int xValue,yValue;


    public DataPoint(int xValue, int yValue){
        this.xValue = xValue;
        this.yValue = yValue;
    }

    public DataPoint() {
    }

    public int getxValue() {
        return xValue;
    }

    public int getyValue() {
        return yValue;
    }
}
