package main.view;

import java.util.List;
import java.util.Queue;

import javafx.scene.Scene;

/**
 * This class should be a base scene for everyone who creates their sub scenes.
 *
 */
public interface CustomScene {
    /**
     * get Scene.
     * @return Scene
     */
    Scene getScene();

    /**
     * update every components on the GUI.
     */
    void updateScene();

    /**
     * update everything that need results from model.
     * @param things everything you can pass from controller.
     */
    void updateEverythingNeeded(Queue<List<?>> things);
}
