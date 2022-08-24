package main.view.expenditure;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.charts.LineChartBuilder;
import main.charts.PieChartBuilder;
import main.charts.TestChart;
import main.charts.TableBuilder;
import main.control.Controller;
import main.json.TransactionJson;
import main.view.BaseScene;
import main.view.MainScene;
import main.view.profile.LoginScene;

public class ExpenditureScene extends BaseScene{

    private static final int TEXT_DIM = 10;
    private static final int TITLE_DIM = 15;

    private final Scene scene;
    private final BorderPane root;
    private Queue<List<?>> updateables;
    private final DecimalFormat df = new DecimalFormat("###.##");

    public ExpenditureScene(final BorderPane root, final Stage primaryStage, final Controller controller) {
        super(primaryStage, controller);
        this.root = root;
        this.scene = getGadgets().createScene(root);
    }

    @Override
    public void updateEverythingNeeded(final Queue<List<?>> things) {
        this.updateables = things;
        super.updateScene();
        super.getPrimaryStage().setScene(this.scene);
    }


    @Override
    public Scene getScene() {
        return this.scene;
    }

    @Override
    protected void updateTop() {
        this.root.setTop(super.getMenuBar());
    }

    @Override
    protected void updateBottom() {
        // nothing to be done here
        final Pane bottomLayout = getGadgets().createHorizontalPanel();
        this.root.setBottom(bottomLayout);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void updateCenter() {
        //final Pane profilePage = new StackPane();
        
        /*LineChart linechart = null;
        try {
            linechart = LineChartBuilder.chartNumberCategory(TestChart.esempioTransaction(), "00/01/2022 00:00", "00/02/2022 00:00");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        PieChart pie = null;
        try {
            pie = PieChartBuilder.builderChart(TestChart.esempioTransaction(), "00/01/2022 00:00", "00/02/2022 00:00");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        /*
        AreaChart area = null;
        try {
            area = LineChartBuilder.areaChartBuilder(TestChart.esempioTransaction(), "00/01/2022", "00/02/2022");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        
        //Creating a stack pane to hold the chart
        StackPane pane = new StackPane(pie);
        pane.setPadding(new Insets(15, 15, 15, 15));
        pane.setStyle("-fx-background-color: BEIGE");
        

        final Pane centerLayout = getGadgets().createVerticalPanel();

        final Text titleInv = getGadgets().createText("Expenditure", TITLE_DIM);
        
        centerLayout.getChildren().addAll(titleInv, pie);
        this.root.setCenter(centerLayout);
    }

    @Override
    protected void updateLeft() {
        final Pane leftLayout = getGadgets().createVerticalPanel();
        
        AreaChart<String, Number> area = null;
        try {
            area = LineChartBuilder.areaChartBuilder(TestChart.esempioTransaction(), "00/01/2022", "00/02/2022");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        //Creating a stack pane to hold the chart
        StackPane pane = new StackPane(area);
        pane.setPadding(new Insets(15, 15, 15, 15));
        pane.setStyle("-fx-background-color: BEIGE");
        

        leftLayout.getChildren().addAll(area);
        this.root.setLeft(leftLayout);
    }

    @Override
    protected void updateRight() {
        final Pane rightLayout = getGadgets().createVerticalPanel();

        TableView tableView = null;
        try {
            tableView = TableBuilder.buildTable(TestChart.esempioTransaction(), "00/01/2022", "00/02/2022");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        final Text titleInv = getGadgets().createText("Expenditure", TITLE_DIM);
        
        rightLayout.getChildren().addAll(titleInv, tableView);
        this.root.setRight(rightLayout);
    }
}
