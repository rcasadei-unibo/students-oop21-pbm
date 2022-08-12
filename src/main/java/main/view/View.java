package main.view;

import java.util.List;
import java.util.Queue;

import main.control.Controller;

/**
 * This interface models an independent implementation of GUI.
 *
 */
public interface View {

    void marketUpdates(Queue<List<?>> queue);

    /**
     * @param observer the controller to attach
     */
    void setObserver(Controller observer);

    void show(String[] args);
    
    void showMessage(String message);

}
