package ui;

import model.PanelObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The left menu user interface for the TimeTableGenerator application.
 * It sets up the left menu and its components for the application.
 */
public class LeftMenuUI extends JPanel {
    private TimeTableApp timeTableApp;
    private List<PanelObserver> observers;

    // MODIFIES: this
    // EFFECTS: Initializes a new LeftMenuUI with a new TimeTableApp and List of PanelObservers.
    public LeftMenuUI(TimeTableApp maintimeTableApp) {
        this.timeTableApp = maintimeTableApp;
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        JButton[] buttons = getButtons();
        observers = new ArrayList<>();

        Dimension buttonPanelSize = new Dimension(400, 200);

        for (JButton button : buttons) {
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setMaximumSize(buttonPanelSize);
            button.setFont(new Font("Arial", Font.PLAIN, 18));
            button.setBackground(Color.LIGHT_GRAY);
            leftPanel.add(button);
            leftPanel.add(Box.createRigidArea(new Dimension(0, 75)));
        }

        this.add(leftPanel);
    }

    // EFFECTS: Returns an array of JButtons for the left menu.
    private JButton[] getButtons() {
        JButton checkTasksButton = new JButton("Check Tasks");
        checkTasksButton.addActionListener(e -> notifyObservers("Check Tasks"));

        JButton generateTimeTableButton = new JButton("Generate Time Table");
        generateTimeTableButton.addActionListener(e -> notifyObservers("Generate Time Table"));

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveSchedule());

        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(e -> loadSchedule());

        JButton[] buttons = {checkTasksButton, generateTimeTableButton, saveButton, loadButton};
        return buttons;
    }

    // MODIFIES: this
    // EFFECTS: Saves the schedule to a file.
    private void saveSchedule() {
        timeTableApp.saveSchedule();
    }

    // MODIFIES: this
    // EFFECTS: Loads the schedule from a file.
    private void loadSchedule() {
        timeTableApp.loadSchedule();
    }

    // MODIFIES: this
    // EFFECTS: Adds a PanelObserver to the list of observers.
    public void addObserver(PanelObserver observer) {
        observers.add(observer);
    }

    // MODIFIES: this
    // EFFECTS: Notifies all observers in the list of observers.
    private void notifyObservers(String panelName) {
        for (PanelObserver observer : observers) {
            observer.update(panelName, timeTableApp);
        }
    }
}