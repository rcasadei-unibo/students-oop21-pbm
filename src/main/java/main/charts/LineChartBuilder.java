package main.charts;

import javafx.geometry.Insets;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.beans.binding.ObjectExpression;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.json.TransactionJson;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

public class LineChartBuilder {
    
    public static LineChart<String, Number> chartNumberCategory(TransactionJson[] transaction, String sDate1, String sDate2) throws ParseException {
        
        Date date1 = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(sDate1);
        Date date2 = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(sDate2);  
        
        //Defining the x an y axes
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        //Setting labels for the axes
        xAxis.setLabel("Date");
        yAxis.setLabel("Transaction");
        //Creating a line chart
        LineChart<String, Number> linechart = new LineChart<String, Number>(xAxis, yAxis);
        //Preparing the data points for the line1
        XYChart.Series series1 = new XYChart.Series();
        
        for(int i=0; i<transaction.length; i++) {
            Date dateTrans = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(transaction[i].getDate()+" "+transaction[i].getTime());
            if(dateTrans.after(date1) && dateTrans.before(date2)) {
                series1.getData().add(new XYChart.Data(transaction[i].getDate(), transaction[i].getAmount()));
            }
        }
        //Setting the name to the line (series)
        series1.setName(transaction[0].getCurrency());
        //Setting the data to Line chart
        linechart.getData().addAll(series1);
        
        linechart.setTitle("Transaction");
        
        return linechart;
    }
    
    public static AreaChart<String, Number> areaChartBuilder(TransactionJson[] transaction, String sDate1, String sDate2) throws ParseException {
        
        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate2);  
        
        //Defining the x an y axes
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        //Setting labels for the axes
        xAxis.setLabel("Date");
        yAxis.setLabel("Transaction");
        //Creating a line chart
        AreaChart<String, Number> areaChart = new AreaChart<String, Number>(xAxis, yAxis);
        areaChart.setTitle("Transaction durin " + sDate1 + "-" + sDate2); 
        //Preparing the data points for the line1
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        
        int days = getDaysBetween(date1, date2);
        double uscite = 0;
        double entrate = 0;
        
        
        for(int i=0; i<transaction.length; i++) {
            Date dateTrans = new SimpleDateFormat("dd/MM/yyyy").parse(transaction[i].getDate());
            
            if(dateTrans.after(date1) && dateTrans.before(date2)) {
                if(transaction[i].getAmount() < 0) {
                    uscite += -1*transaction[i].getAmount();
                    series2.getData().add(new XYChart.Data(transaction[i].getDate(), uscite));
                    series1.getData().add(new XYChart.Data(transaction[i].getDate(), entrate));
                } else {
                    entrate += transaction[i].getAmount();
                    series1.getData().add(new XYChart.Data(transaction[i].getDate(), entrate));
                    series2.getData().add(new XYChart.Data(transaction[i].getDate(), uscite));
                }
            } 
        }
            
        //Setting the name to the line (series)
        series1.setName("Guadagni (" + entrate + "$)");
        series2.setName("Spese (" + uscite + "$)");
        //Setting the data to Line chart
        areaChart.getData().addAll(series1, series2);
        
        return areaChart;
    }
    
    static int getDaysBetween(Date date1, Date date2) {
        int i = 0;
        while(date1.before(date2)) {
            i++;
            date1 = addDays(date1, 1);
        }
        return 0;
    }
    
    public static Date addDays(Date date, int days){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
    

}
