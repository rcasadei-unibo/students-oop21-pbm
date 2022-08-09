package main.view.Investment;

import java.awt.Menu;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.control.Controller;
import main.util.DeliverablePackage;
import main.view.BaseScene;

public class InvestmentScene extends BaseScene {

    private static final String BUY = "Buy";
    private static final String SELL = "Sell";
    private static final String STOCKTITLE = "          Market";
    private static final String SYMBOL = "Symbol    ";
    private static final String SHARE = "Share  ";
    private static final String PRICE = "Price  ";
    private static final String VALUE = "Value";

    private static final int TITLEFONTSIZE = 20;
    private static final int HEADERFONTSIZE = 10;
    private static final int TEXTFONTSIZE = 5;

    private final List<String> desc;

    private final BorderPane root;
    private Queue<List<?>> marketHoldings;
    private final Pane menuBar;
    private final Scene scene;

    public InvestmentScene(final Scene mainScene, final Stage primaryStage,
            final Pane menuBar, final double screenWidth, final double screenHeight) {
        super(mainScene, primaryStage, screenWidth, screenHeight);
        desc = new ArrayList<>();
        desc.add(SYMBOL);
        desc.add(PRICE);
        desc.add(SHARE);
        desc.add(VALUE);
        root = new BorderPane();
        scene = getGadgets().createScene(root);
        this.menuBar = menuBar;
        createMenu();
    }

    // interface and functionalities
    private void createMenu() {
        final Pane bottomBar = getGadgets().createHorizontalPanel();
        final Button buy = getGadgets().createButton(BUY), sell = getGadgets().createButton(SELL);
        bottomBar.getChildren().addAll(buy, sell);
        root.setBottom(bottomBar);
        root.setTop(this.menuBar);
    }

    // content display that are updateble
    private void createContentDisplay() {
        final Iterator<List<?>> iter = marketHoldings.iterator();

        // maybe createScheda should have been built differently, but since it's for
        // GUI, not computational model,
        // I think a bit redundancy can't be avoided without losing flexibility;
        @SuppressWarnings("unchecked")
        final Node n = getGadgets().createBlockScheda(getGadgets().createText(STOCKTITLE, TITLEFONTSIZE),
                getGadgets().transformStringIntoText(desc, HEADERFONTSIZE),
                getGadgets().transformStringIntoText(iter.next(), TEXTFONTSIZE),
                getGadgets().transformStringIntoText(iter.next(), TEXTFONTSIZE),
                getGadgets().transformStringIntoText(iter.next(), TEXTFONTSIZE),
                getGadgets().transformStringIntoText(iter.next(), TEXTFONTSIZE));

        root.setCenter(n);
    }

    public void setMarketHoldings(final Queue<List<?>> marketHoldings) {
        this.marketHoldings = marketHoldings;
    }

    @Override
    public Scene getScene() {
        createContentDisplay();
        System.out.println("hii");
        return scene;
    }

    @Override
    public void updateEverythingNeeded(final Queue<List<?>> marketHoldings) {
       setMarketHoldings(marketHoldings);
    }

}
