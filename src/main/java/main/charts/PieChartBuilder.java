package main.charts;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import main.json.TransactionJson;

public class PieChartBuilder {
    
    public static PieChart builderChart(TransactionJson[] transaction, String sDate1, String sDate2) throws ParseException {
        
        Date date1 = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(sDate1);
        Date date2 = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(sDate2);
        
        double ricavi = 0;
        double spese = 0;
        
        for(int i=0; i<transaction.length; i++) {
            Date dateTrans = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(transaction[i].getDate()+" "+transaction[i].getTime());
            if(dateTrans.after(date1) && dateTrans.before(date2)) {
                if(transaction[i].getAmount() < 0) {
                    spese += (-1*transaction[i].getAmount());
                } else {
                    ricavi += transaction[i].getAmount();
                };
            }
        }
        
      //Preparing ObservbleList object         
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
           new PieChart.Data("Ricavi " + ricavi + "$", ricavi), 
           new PieChart.Data("Spese " + spese + "$", spese)); 
         
        //Creating a Pie chart 
        PieChart pieChart = new PieChart(pieChartData); 
                
        //Setting the title of the Pie chart 
        pieChart.setTitle("Entrate e Uscite"); 
         
        //setting the direction to arrange the data 
        pieChart.setClockwise(true); 
         
        //Setting the length of the label line 
        pieChart.setLabelLineLength(50); 

        //Setting the labels of the pie chart visible  
        pieChart.setLabelsVisible(true); 
         
        //Setting the start angle of the pie chart  
        pieChart.setStartAngle(180);     
           
        return pieChart;
        
    }

}
