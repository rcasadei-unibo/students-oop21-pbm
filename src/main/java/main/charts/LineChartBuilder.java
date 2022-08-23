package main.charts;

import javafx.geometry.Insets;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.json.TransactionJson;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

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
                series1.getData().add(new XYChart.Data(transaction[i].getDate()+" "+transaction[i].getTime(), transaction[i].getAmount()));
            }
        }
        //Setting the name to the line (series)
        series1.setName(transaction[0].getCurrency());
        //Setting the data to Line chart
        linechart.getData().addAll(series1);
        
        return linechart;
    }
    

}
