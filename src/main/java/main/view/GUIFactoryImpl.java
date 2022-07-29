package main.view;

import com.google.common.base.Function;

import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import main.util.Pair;

public final class GUIFactoryImpl implements GUIFactory {
	
	private Pair<Double, Double> buttonSize;
	private Insets panelInsets;
	private double panelSpacing;
	private Pair<Double, Double> sceneSize;
	
	

	private GUIFactoryImpl(final Pair<Double, Double> buttonSize, final Insets panelInsets, final double panelSpacing,
			final Pair<Double, Double> sceneSize) {
		super();
		this.buttonSize = buttonSize;
		this.panelInsets = panelInsets;
		this.panelSpacing = panelSpacing;
		this.sceneSize = sceneSize;
	}

	@Override
	public Button createButton(final String name) {
		final Button b = new Button(name);
		b.setPrefSize(buttonSize.getX(), buttonSize.getY());
		return b;
	}

	@Override
	public Pane createHorizontalPanel() {
		final HBox hbox = new HBox();
		hbox.setPadding(panelInsets);
		hbox.setSpacing(panelSpacing);
		return hbox;
	}
	

	@Override
	public Scene createScene(final Pane panel) {
		return new Scene(panel, sceneSize.getX(), sceneSize.getY());
	}

	public static class Builder {
		// these things should be read from configuration file.
		// i need to settle it better later.
		// che spachetti code, non ce l'ha faccio piu'
		//private static final double X = Screen.getPrimary().getBounds().getWidth();
		//private static final double Y = Screen.getPrimary().getBounds().getHeight();
		private static final double BUTTONWIDTH = 0.15;
		private static final double BUTTONHEIGHT = 0.05;
		private static final double SCENEWIDTH = 0.681;
		private static final double SCENEHEIGHT = 0.681;
		private static final double INSETSX = 0.015;
		private static final double INSETSY = 0.015;
		private static final double PANELSPACING = 0.001;

		private Pair<Double, Double> buttonSize = new Pair<>(BUTTONWIDTH, BUTTONHEIGHT);
		private Pair<Double, Double> sceneSize = new Pair<>(SCENEWIDTH, SCENEHEIGHT);
		private Insets panelInsets;
		private double panelSpacing;
		private final double x;
		private final double y;

		private final Function<Double, Double> fx;
		private final Function<Double, Double> fy;
		
		public Builder(final double x, final double y) {
			super();
			fx = p -> x * p;
			fy = p -> y * p;
			this.buttonSize = new Pair<>(fx.apply(BUTTONWIDTH), fy.apply(BUTTONHEIGHT));
			this.sceneSize = new Pair<>(fx.apply(SCENEWIDTH), fy.apply(SCENEHEIGHT));
			this.panelInsets = new Insets(fx.apply(INSETSX), fy.apply(INSETSY), fx.apply(INSETSX), fy.apply(INSETSY));
			final double h = Math.sqrt(x * x + y * y);
			this.panelSpacing = h * PANELSPACING;
			this.x = x;
			this.y = y;
		}

		public Builder buttonSize(final double pX, final double pY) {
			this.buttonSize = new Pair<Double, Double>(fx.apply(pX), fy.apply(pY));
			return this;
		}

		public Builder sceneSize(final double p) {
			this.sceneSize = new Pair<Double, Double>(fx.apply(p), fy.apply(p));
			return this;
		}

		public Builder panelInsets(final double p) {
			this.panelInsets = new Insets(fx.apply(p), fy.apply(p), fx.apply(p), fy.apply(p));
			return this;
		}

		public Builder panelSpacing(final double p) {
			final double h = Math.sqrt(x * x + y * y);
			this.panelSpacing = h * p;
			return this;
		}
		
		public final GUIFactoryImpl build() {
			return new GUIFactoryImpl(buttonSize, panelInsets, panelSpacing, sceneSize);
		}
		

	}

	@Override
	public Alert createInformationBox(final String message) {
		final Alert a = new Alert(AlertType.INFORMATION);
		a.setTitle("Information box");
		a.setHeaderText("A lovely tip!");
		a.setContentText(message);
		return a;
	}

}
