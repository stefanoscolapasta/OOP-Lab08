package it.unibo.oop.lab.advanced;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;


/**
 *
 */
public final class DrawNumberImpl implements DrawNumber {

    private int choice;
    private final int min;
    private final int max;
    private final int attempts;
    private int remainingAttempts;
    private final Random random = new Random();
    private final List<String> configs; 
    private final Map<String, Integer> configurations;

    public DrawNumberImpl() throws IOException, URISyntaxException{
        this.configurations = new HashMap<>();
        //this.configs = Files.readAllLines(Path.of(ClassLoader.getSystemResource("config.yml").getPath().replace(":", "")));
        final URI uri = this.getClass().getResource("config.yml").toURI();
        this.configs = Files.readAllLines(Paths.get(uri));
        this.loadConfigs();
        this.min = this.configurations.get("min");
        this.max = this.configurations.get("max");
        this.attempts = this.configurations.get("attempts");
        System.out.println(min + " " + max + " " + attempts);
    }

    private void loadConfigs() {
        for (final String line : this.configs) {
            final StringTokenizer tokenizer = new StringTokenizer(line, ": ");
            this.configurations.put(tokenizer.nextToken().trim(), Integer.valueOf(tokenizer.nextToken().trim()));
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
