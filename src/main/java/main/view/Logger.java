package main.view;

import java.util.List;
import java.util.Queue;

import com.google.common.base.Optional;

import main.control.Controller;

public class Logger implements View {

    @Override
    public void setObserver(Controller observer) {
        // TODO Auto-generated method stub

    }

    @Override
    public void showMessage(String message) {
        System.out.println("NOTHING TO SAY ABOUT..");
    }

    @Override
    public void updateView(Optional<Queue<List<?>>> queue, PageState pageState) {
        System.out.println("Updating views.....");
        System.out.println("Here should save important changes to local or jason or whatever... please someone does it.");
    }

}
