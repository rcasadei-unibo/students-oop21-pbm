package main.view;

import java.util.List;
import java.util.Queue;

import javafx.scene.Scene;

public interface CustomScene {
    Scene getScene();
    
    void updateEverythingNeeded(Queue<List<?>> things);
}
