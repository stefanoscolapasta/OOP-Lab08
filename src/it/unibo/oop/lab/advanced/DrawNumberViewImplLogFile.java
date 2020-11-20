package it.unibo.oop.lab.advanced;

import java.io.PrintStream;

public class DrawNumberViewImplLogFile implements DrawNumberView {

    private final PrintStream ps;
    public DrawNumberViewImplLogFile(final PrintStream stream) {
        this.ps = stream;
    }

    @Override
    public void setObserver(final DrawNumberViewObserver observer) {
        // TODO Auto-generated method stub

    }

    @Override
    public void start() {
        // TODO Auto-generated method stub

    }

    @Override
    public void numberIncorrect() {
        ps.println("You must enter a number");

    }

    @Override
    public void result(final DrawResult res) {
        ps.println(res.getDescription());

    }

    @Override
    public void displayError(final String message) {
        ps.println(message);

    }

    @Override
    public void limitsReached() {
        ps.print("You lost");

    }

}
