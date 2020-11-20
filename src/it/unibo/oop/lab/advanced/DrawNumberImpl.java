package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

/**
 *
 */
public final class DrawNumberImpl implements DrawNumber {

    private int choice;
    private int min;
    private int max;
    private int attempts;
    private int remainingAttempts;
    private final Random random = new Random();
    private static final String PATH = "config.yml";

    public DrawNumberImpl() {
        final InputStream configsURL = ClassLoader.getSystemResourceAsStream(PATH);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(configsURL))) {
            this.min = Integer.valueOf(br.readLine().replaceAll("[^0-9]", ""));
            this.max = Integer.valueOf(br.readLine().replaceAll("[^0-9]", ""));
            this.attempts = Integer.valueOf(br.readLine().replaceAll("[^0-9]", ""));
            this.reset();
        } catch (IOException exc) {
            System.out.print(exc.getMessage());
        }
    }

    @Override
    public void reset() {
        this.remainingAttempts = this.attempts;
        this.choice = this.min + random.nextInt(this.max - this.min + 1);
    }

    @Override
    public DrawResult attempt(final int n) throws AttemptsLimitReachedException {
        if (this.remainingAttempts <= 0) {
            throw new AttemptsLimitReachedException();
        }
        if (n < this.min || n > this.max) {
            throw new IllegalArgumentException("The number is outside boundaries");
        }
        remainingAttempts--;
        if (n > this.choice) {
            return DrawResult.YOURS_HIGH;
        }
        if (n < this.choice) {
            return DrawResult.YOURS_LOW;
        }
        return DrawResult.YOU_WON;
    }

}
