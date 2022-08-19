package main.view;

import java.util.List;
import java.util.Queue;

import javafx.scene.Scene;

/**
 * This class should be a base scene for everyone who creates their sub scenes.
 *
 */
public interface CustomScene {
    Scene getScene();

    void updateScene();

    void updateEverythingNeeded(Queue<List<?>> things);
}
