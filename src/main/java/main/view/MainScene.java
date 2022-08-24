package main.view;

import java.text.ParseException;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import main.charts.LineChartBuilder;
import main.charts.PieChartBuilder;
import main.charts.TestChart;
import main.control.Controller;
import main.json.OperationJSONUtente;

public class MainScene {

    private final Controller controller;
    private GUIFactory guiFactory;
    private final BorderPane root;
    private final Stage stage;

    public MainScene(final Stage stage, final Controller controller) {
        final GUIFactoryImpl.Builder b = new GUIFactoryImpl.Builder(Screen.getPrimary().getBounds().getWidth(),
                Screen.getPrimary().getBounds().getHeight());
        this.guiFactory = b.build();
        this.controller = controller;
        this.root = new BorderPane();
        this.stage = stage;

        this.root.setTop(createMenuBar());
    }

    public Scene getScene() {
        return this.guiFactory.createScene(this.root);
    }

    private Pane createMenuBar() {
        final Pane menuBar = guiFactory.createHorizontalPanel();
        final Button investment = guiFactory.createButton("Investmenti");
        final Button profilo = guiFactory.createButton("Profilo");
        final Button bankAccount = guiFactory.createButton("Conti Bancari");
        final Button expenses = guiFactory.createButton("Spese");
        final Button savings = guiFactory.createButton("Salvadanai");

        investment.setOnAction(e -> getInvestmentPage());
        profilo.setOnAction(e -> getProfilePage());
        bankAccount.setOnAction(e -> getBankAccountPage());
        expenses.setOnAction(e -> getExpenditurePage());
        savings.setOnAction(e -> getSavingPage());
        menuBar.getChildren().addAll(profilo, investment, expenses, bankAccount, savings);
        return menuBar;
    }

    private void getInvestmentPage() {
        this.controller.updateMarketInfo();

    }

    private void getProfilePage() {
        this.controller.showProfile();
    }

    private void getBankAccountPage() {
        this.guiFactory.createInformationBox("da implementare giulio").showAndWait();
    }

    private void getExpenditurePage() {
        this.controller.showExpenditure();
        /*final Pane profilePage = new StackPane();
        
        LineChart linechart = null;
        try {
            linechart = LineChartBuilder.chartNumberCategory(TestChart.esempioTransaction(), "00/01/2022 00:00", "00/02/2022 00:00");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        PieChart pie = null;
        try {
            pie = PieChartBuilder.builderChart(TestChart.esempioTransaction(), "00/01/2022 00:00", "00/02/2022 00:00");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        AreaChart area = null;
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
        //Setting the Scene
        final Pane topBar = guiFactory.createHorizontalPanel();
        topBar.getChildren().addAll(pane);
        root.setCenter(topBar);*/
    }

    private void getSavingPage() {
        this.guiFactory.createInformationBox("da implementare giulio").showAndWait();
    }

    public final Pane getMenuBar() {
        return createMenuBar();
    }
}
