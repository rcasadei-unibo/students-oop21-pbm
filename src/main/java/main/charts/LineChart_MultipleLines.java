package main.charts;
import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class LineChart_MultipleLines extends Application {
   public void start(Stage stage) {
      //Defining the x an y axes
      CategoryAxis xAxis = new CategoryAxis();
      NumberAxis yAxis = new NumberAxis();
      //Setting labels for the axes
      xAxis.setLabel("Month");
      yAxis.setLabel("Temperature(°C)");
      //Creating a line chart
      LineChart<String, Number> linechart = new LineChart<String, Number>(xAxis, yAxis);
      //Preparing the data points for the line1
      XYChart.Series series1 = new XYChart.Series();
      series1.getData().add(new XYChart.Data("Jan", 7.0));
      series1.getData().add(new XYChart.Data("Feb", 6.9));
      series1.getData().add(new XYChart.Data("March", 9.5));
      series1.getData().add(new XYChart.Data("April", 14.5));
      series1.getData().add(new XYChart.Data("May", 18.2));
      series1.getData().add(new XYChart.Data("June", 21.5));
      series1.getData().add(new XYChart.Data("July", 25.2));
      series1.getData().add(new XYChart.Data("August", 26.5));
      series1.getData().add(new XYChart.Data("Sep", 23.3));
      series1.getData().add(new XYChart.Data("Oct", 18.3));
      series1.getData().add(new XYChart.Data("Nov", 13.9));
      series1.getData().add(new XYChart.Data("Dec", 9.6));
      //Preparing the data points for the line2
      XYChart.Series series2 = new XYChart.Series();
      series2.getData().add(new XYChart.Data("Jan", -0.2));
      series2.getData().add(new XYChart.Data("Feb", 0.8));
      series2.getData().add(new XYChart.Data("March", 5.7));
      series2.getData().add(new XYChart.Data("April", 11.3));
      series2.getData().add(new XYChart.Data("May", 17.0));
      series2.getData().add(new XYChart.Data("June", 22.0));
      series2.getData().add(new XYChart.Data("July", 24.8));
      series2.getData().add(new XYChart.Data("August", 24.1));
      series2.getData().add(new XYChart.Data("Sep", 20.1));
      series2.getData().add(new XYChart.Data("Oct", 14.1));
      series2.getData().add(new XYChart.Data("Nov", 8.6));
      series2.getData().add(new XYChart.Data("Dec", 2.5));
      //Preparing the data points for the line3
      XYChart.Series series3 = new XYChart.Series();
      series3.getData().add(new XYChart.Data("Jan", 3.9));
      series3.getData().add(new XYChart.Data("Feb", 4.2));
      series3.getData().add(new XYChart.Data("March", 5.7));
      series3.getData().add(new XYChart.Data("April", 8.5));
      series3.getData().add(new XYChart.Data("May", 11.9));
      series3.getData().add(new XYChart.Data("June", 15.2));
      series3.getData().add(new XYChart.Data("July", 17.0));
      series3.getData().add(new XYChart.Data("August", 16.6));
      series3.getData().add(new XYChart.Data("Sep", 14.2));
      series3.getData().add(new XYChart.Data("Oct", 10.3));
      series3.getData().add(new XYChart.Data("Nov", 6.6));
      series3.getData().add(new XYChart.Data("Dec", 4.8));
      //Setting the name to the line (series)
      series1.setName("Tokyo");
      series2.setName("New York");
      series3.setName("London");
      //Setting the data to Line chart
      linechart.getData().addAll(series1, series2, series3);
      //Creating a stack pane to hold the chart
      StackPane pane = new StackPane(linechart);
      pane.setPadding(new Insets(15, 15, 15, 15));
      pane.setStyle("-fx-background-color: BEIGE");
      //Setting the Scene
      Scene scene = new Scene(pane, 595, 350);
      stage.setTitle("Line Chart");
      stage.setScene(scene);
      stage.show();
      
      /*
       * final Pane profilePage = new StackPane();
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        //Setting labels for the axes
        xAxis.setLabel("Month");
        yAxis.setLabel("increment % $");
        //Creating a line chart
        LineChart<String, Number> linechart = new LineChart<String, Number>(xAxis, yAxis);
        //Preparing the data points for the line1
        XYChart.Series series1 = new XYChart.Series();
        series1.getData().add(new XYChart.Data("Jan", 36.41));
        series1.getData().add(new XYChart.Data("Feb", 30.11));
        series1.getData().add(new XYChart.Data("March", -1.78));
        series1.getData().add(new XYChart.Data("April", -35.38));
        series1.getData().add(new XYChart.Data("May", -6.09));
        series1.getData().add(new XYChart.Data("June", 18.63));
        series1.getData().add(new XYChart.Data("July", 13.42));
        series1.getData().add(new XYChart.Data("August", -7.02));
        series1.getData().add(new XYChart.Data("Sep", 39.9));
        series1.getData().add(new XYChart.Data("Oct", -7.22));
        series1.getData().add(new XYChart.Data("Nov", -18.75));
        series1.getData().add(new XYChart.Data("Dec", -16.7));
        //Preparing the data points for the line2
        XYChart.Series series2 = new XYChart.Series();
        series2.getData().add(new XYChart.Data("Jan", 78.37));
        series2.getData().add(new XYChart.Data("Feb", 8.08));
        series2.getData().add(new XYChart.Data("March", 35.19));
        series2.getData().add(new XYChart.Data("April", 44.57));
        series2.getData().add(new XYChart.Data("May", -2.32));
        series2.getData().add(new XYChart.Data("June", -16.05));
        series2.getData().add(new XYChart.Data("July", 11.36));
        series2.getData().add(new XYChart.Data("August", 35.49));
        series2.getData().add(new XYChart.Data("Sep", -12.54));
        series2.getData().add(new XYChart.Data("Oct", 42.89));
        series2.getData().add(new XYChart.Data("Nov", 7.96));
        series2.getData().add(new XYChart.Data("Dec", -20.55));
        //Preparing the data points for the line3
        XYChart.Series series3 = new XYChart.Series();
        series3.getData().add(new XYChart.Data("Jan", 4.22));
        series3.getData().add(new XYChart.Data("Feb", 27.6));
        series3.getData().add(new XYChart.Data("March", 19.1));
        series3.getData().add(new XYChart.Data("April", 37.84));
        series3.getData().add(new XYChart.Data("May", -30.59));
        series3.getData().add(new XYChart.Data("June", -23.39));
        series3.getData().add(new XYChart.Data("July", 0.38));
        series3.getData().add(new XYChart.Data("August", 18.52));
        series3.getData().add(new XYChart.Data("Sep", -10.59));
        series3.getData().add(new XYChart.Data("Oct", 25.05));
        series3.getData().add(new XYChart.Data("Nov", 8.53));
        series3.getData().add(new XYChart.Data("Dec", -29.7));
        //Setting the name to the line (series)
        series1.setName("Bitcoin");
        series2.setName("Ethereum");
        series3.setName("Litecoin");
        //Setting the data to Line chart
        linechart.getData().addAll(series1, series2, series3);
        //Creating a stack pane to hold the chart
        StackPane pane = new StackPane(linechart);
        pane.setPadding(new Insets(15, 15, 15, 15));
        pane.setStyle("-fx-background-color: BEIGE");
        //Setting the Scene
        final Pane topBar = guiFactory.createHorizontalPanel();
        topBar.getChildren().addAll(pane);
        root.setBottom(topBar);*/
      
   }
}