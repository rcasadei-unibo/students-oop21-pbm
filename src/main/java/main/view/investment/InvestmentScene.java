package main.view.investment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.control.Controller;
import main.util.AutoCompleteTextField;
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
    private Queue<List<?>> updateables;
    private final ObservableList<String> accountBox;
    private final Scene scene;
    private final AutoCompleteTextField symbolName;
    private final ComboBox<String> accountComboBox;

    public InvestmentScene(final BorderPane root, final Stage primaryStage, final Controller controller) {
        super(primaryStage, controller);
        desc = new ArrayList<>();
        desc.add(SYMBOL);
        desc.add(PRICE);
        desc.add(SHARE);
        desc.add(VALUE);
        this.root = root;
        scene = getGadgets().createScene(root);
        this.accountBox = FXCollections.observableArrayList();
        accountComboBox = new ComboBox<>(accountBox);
        symbolName = new AutoCompleteTextField();
    }

    /**
     * {@inheritDoc}
     */
    public void setMarketHoldings(final Queue<List<?>> updates) {
        this.updateables = updates;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Scene getScene() {
        return scene;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateEverythingNeeded(final Queue<List<?>> updates) {
        setMarketHoldings(updates);
        updateScene();
        super.getPrimaryStage().setScene(scene);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateLeft() {
        root.setLeft(super.getGadgets().createVerticalPanel());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateRight() {
        root.setRight(super.getGadgets().createVerticalPanel());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBottom() {
        final Pane bottomBar = getGadgets().createHorizontalPanel();
        final Button buy = getGadgets().createButton(BUY), sell = getGadgets().createButton(SELL);
        final TextField numberShare = new TextField("1.0");

        symbolName.setPromptText("symbol name");
        numberShare.setPromptText("share number");

        // force the field to be numeric only
        // some Regex expression. This solution was found on stack overflow.
        numberShare.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable, final String oldValue,
                    final String newValue) {
                if (!newValue.matches("(-?\\d+\\.?\\d*)")) {
                    numberShare.setText(newValue.replaceAll("[^\\d*]", ""));
                }
            }
        });

        symbolName.setTextFormatter(new TextFormatter<>((change) -> {
            change.setText(change.getText().toUpperCase());
            return change;
        }));

        buy.setOnAction(e -> {
            getController().buyStocks(symbolName.getText(), Double.parseDouble(numberShare.getText()),
                    accountComboBox.getValue());
        });

        sell.setOnAction(e -> {
            getController().sellStocks(symbolName.getText(), Double.parseDouble(numberShare.getText()),
                    accountComboBox.getValue());
        });

        bottomBar.getChildren().addAll(accountComboBox, symbolName, numberShare, buy, sell);
        root.setBottom(bottomBar);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void updateCenter() {
        final Iterator<List<?>> iter = updateables.iterator();
        final List<String> symbols = (List<String>) iter.next();

        // maybe createScheda should have been built differently, but since it's for
        // GUI, not computational model,
        // I think a bit redundancy can't be avoided without losing flexibility;
        @SuppressWarnings("unchecked")
        final Node n = getGadgets().createBlockScheda(
                getGadgets().createText(STOCKTITLE, TITLEFONTSIZE),
                getGadgets().transformStringIntoText(desc, HEADERFONTSIZE),
                getGadgets().transformStringIntoText(symbols, TEXTFONTSIZE),
                getGadgets().transformStringIntoText(iter.next(), TEXTFONTSIZE),
                getGadgets().transformStringIntoText(iter.next(), TEXTFONTSIZE),
                getGadgets().transformStringIntoText(iter.next(), TEXTFONTSIZE));

        accountBox.clear();
        accountBox.addAll((Collection<? extends String>) iter.next());
        accountComboBox.getSelectionModel().selectFirst();
        symbolName.getEntries().clear();
        symbolName.getEntries().addAll(symbols);

        root.setCenter(n);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void updateTop() {
       root.setTop(super.getMenuBar());
    }
}
