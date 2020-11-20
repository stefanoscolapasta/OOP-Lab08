package it.unibo.oop.lab.advanced;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    private final DrawNumber model;
    private final DrawNumberView view;

    /**
     * @throws IOException 
     * @throws URISyntaxException 
     * 
     */
    public DrawNumberApp() throws IOException, URISyntaxException {
        this.model = new DrawNumberImpl();
        this.view = new DrawNumberViewImpl();
        this.view.setObserver(this);
        this.view.start();
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            this.view.result(result);
        } catch (IllegalArgumentException e) {
            this.view.numberIncorrect();
        } catch (AttemptsLimitReachedException e) {
            view.limitsReached();
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     * @throws URISyntaxException 
     */
    public static void main(final String... args) throws URISyntaxException {
        try {
            new DrawNumberApp();
            new DrawNumberViewImpl();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
