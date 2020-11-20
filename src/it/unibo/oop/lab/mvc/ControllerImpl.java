package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.List;

public class ControllerImpl implements Controller {
    private final List<String> stringHistory;
    private String lastString;

    public ControllerImpl() {
        this.stringHistory = new ArrayList<>();
    }

    /**
     * @param string
     *                   the string to add to stringsHistory
     */
    public void setStringToPrint(final String string) {
        this.stringHistory.add(string);
        this.lastString = string;
    }

    /**
     * @return String the string to add to stringsHistory
     */
    public String getStringToPrint() {
        return this.lastString;
    }

    /**
     * @return List<String> the stringsHistory
     */
    public List<String> getHistoryStrings() {
        return new ArrayList<String>(this.stringHistory);
    }

    /**
     * Prints the currentString.
     */
    public void printCurrentString() {
        if (this.lastString != null) {
            System.out.println(String.valueOf(this.lastString));
        } else {
            throw new IllegalStateException();
        }
    }

}
