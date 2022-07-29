package main.view;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public interface GUIFactory {
	
	
	Pane createHorizontalPanel();
	
	Button createButton(String name);
	
	Scene createScene(Pane panel);
	
	Alert createInformationBox(String message);
}
