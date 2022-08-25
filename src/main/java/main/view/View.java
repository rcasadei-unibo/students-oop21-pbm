package main.view;

import java.util.List;
import java.util.Queue;

import com.google.common.base.Optional;

import main.control.Controller;

/**
 * This interface models an independent implementation of GUI.
 *
 */
public interface View {

    /**
     * @param observer the controller to attach
     */
    void setObserver(Controller observer);

    /**
     * Show the message box.
     * 
     * @param message the message to show.
     */
    void showMessage(String message);

    /**
     * Update every components to sub customScenes.
     * @param queue you check deliverable package, that get infinite element of arguments with arbitrary type.
     * It's my best solution :)
     * @param pageState The state to show which page should be updated.
     */
    void updateView(Optional<Queue<List<?>>> queue, PageState pageState);

}
