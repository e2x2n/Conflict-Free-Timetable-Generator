package ui;

import javax.swing.*;
import java.awt.*;

/**
 * The right panel user interface for generating a timetable in the TimeTableGenerator application.
 * It sets up the right panel and its components for the application.
 */
public class RightGenerateUI extends JPanel {
    private TimeTableApp timeTableApp;
    private JTextArea timeTableTextArea;
    private JButton backButton;


    // MODIFIES: this
    // EFFECTS: Initializes a new RightGenerateUI with a new TimeTableApp, JTextArea, and JButton.

    public RightGenerateUI(TimeTableApp timeTableApp) {
        this.timeTableApp = timeTableApp;
        timeTableTextArea = new JTextArea();
        timeTableTextArea.setEditable(false);
        timeTableTextArea.setLineWrap(true);
        backButton = new JButton("Back");
        refreshTimeTable();

        this.setLayout(new BorderLayout());

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(new JScrollPane(timeTableTextArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        southPanel.add(buttonPanel, BorderLayout.NORTH);

        this.add(southPanel, BorderLayout.CENTER);

        backButton.addActionListener(e ->
                ((CardLayout)(this.getParent()).getLayout()).show(this.getParent(), "Add Task"));
    }


    // MODIFIES: this
    // EFFECTS: Generates a timetable and sets the text of the JTextArea to the generated timetable.
    public void generateTimeTable() {
        String timeTable = timeTableApp.generateTimeTables();
        timeTableTextArea.setText(timeTable);
    }


    // MODIFIES: this
    // EFFECTS: Sets the text of the JTextArea to an empty string and generates a new timetable.
    public void refreshTimeTable() {
        timeTableTextArea.setText("");
        generateTimeTable();
    }

    @Override
    public Dimension getPreferredSize() {
        if (getParent() != null) {
            return getParent().getSize();
        } else {
            return super.getPreferredSize();
        }
    }
}