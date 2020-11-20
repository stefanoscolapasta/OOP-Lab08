package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.unibo.oop.lab.mvcio.Controller;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {
    private final Controller contr;
    private SimpleGUIWithFileChooser() {
        this.contr = new Controller();
        final JFrame frame = new JFrame();
        final JPanel panel = new JPanel(new BorderLayout());
        final JPanel innerPanel = new JPanel(new BorderLayout());
        final JTextField text = new JTextField();
        text.setEditable(false);
        final JButton btBrowse = new JButton("Browse file");
        final JTextArea textArea = new JTextArea();
        /**
         * 
         */
        btBrowse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser jfc = new JFileChooser();
                final int ret = jfc.showSaveDialog(btBrowse);
                if (ret == JFileChooser.APPROVE_OPTION) {
                    SimpleGUIWithFileChooser.this.contr.setThisFileAsCurrent(jfc.getSelectedFile());
                    text.setText(contr.getCurrentFilePath());
                } else if (ret == JFileChooser.ERROR_OPTION) {
                    JOptionPane.showMessageDialog(btBrowse, "An error occured");
                }
            }
        });
        innerPanel.add(text, BorderLayout.CENTER);
        innerPanel.add(btBrowse, BorderLayout.LINE_END);
        panel.add(innerPanel, BorderLayout.NORTH);
        panel.add(textArea, BorderLayout.CENTER);
        frame.setContentPane(panel);
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        frame.setVisible(true);
    }
    /*
     * TODO: Starting from the application in mvcio:
     * 
     * 1) Add a JTextField and a button "Browse..." on the upper part of the
     * graphical interface.
     * Suggestion: use a second JPanel with a second BorderLayout, put the panel
     * in the North of the main panel, put the text field in the center of the
     * new panel and put the button in the line_end of the new panel.
     * 
     * 2) The JTextField should be non modifiable. And, should display the
     * current selected file.
     * 
     * 3) On press, the button should open a JFileChooser. The program should
     * use the method showSaveDialog() to display the file chooser, and if the
     * result is equal to JFileChooser.APPROVE_OPTION the program should set as
     * new file in the Controller the file chosen. If CANCEL_OPTION is returned,
     * then the program should do nothing. Otherwise, a message dialog should be
     * shown telling the user that an error has occurred (use
     * JOptionPane.showMessageDialog()).
     * 
     * 4) When in the controller a new File is set, also the graphical interface
     * must reflect such change. Suggestion: do not force the controller to
     * update the UI: in this example the UI knows when should be updated, so
     * try to keep things separated.
     */
    public static void main(final String[] args) {
        new SimpleGUIWithFileChooser();
    }
}
