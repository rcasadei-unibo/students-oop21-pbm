package main.view;

import java.util.List;
import java.util.Queue;

import com.google.common.base.Optional;

import main.control.Controller;

public class WriteToFile implements View {

    @Override
    public void setObserver(final Controller observer) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showMessage(final String message) {
        System.out.println("NOTHING TO SAY ABOUT..");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateView(final Optional<Queue<List<?>>> queue, final PageState pageState) {
        System.out.println("Updating views.....");
        System.out.println("Here should save important changes to local or jason or whatever... please someone does it.");
    }

}
