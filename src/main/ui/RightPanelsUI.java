package ui;

import model.PanelObserver;

import javax.swing.*;
import java.awt.*;

/**
 * The right panel user interface for the TimeTableGenerator application.
 * It sets up the right panel and its components for the application.
 */
public class RightPanelsUI extends JPanel implements PanelObserver {
    private TimeTableApp timeTableApp;
    private CardLayout cardLayout;
    private JPanel rightPanel;
    private RightCheckTasksUI checkTasksUI;
    private RightGenerateUI generateUI;


    // MODIFIES: this
    // EFFECTS: Initializes a new RightPanelsUI with a new TimeTableApp, CardLayout, and JPanel.
    public RightPanelsUI(TimeTableApp maintimeTableApp) {
        this.timeTableApp = maintimeTableApp;
        cardLayout = new CardLayout();
        rightPanel = new JPanel(cardLayout);

        RightAddTaskUI rightAddTaskUI = new RightAddTaskUI(timeTableApp);

        rightPanel.add(rightAddTaskUI, "Add Task");

        this.add(new JScrollPane(rightPanel));
    }


    // MODIFIES: this
    // EFFECTS: Updates the right panel based on the given panel name and TimeTableApp instance.
    @Override
    public void update(String panelName, TimeTableApp timeTableApp) {
        if ("Check Tasks".equals(panelName)) {
            if (checkTasksUI == null) {
                checkTasksUI = new RightCheckTasksUI(timeTableApp);
                rightPanel.add(checkTasksUI, "Check Tasks");
            } else {
                checkTasksUI.refreshTaskList();
            }
        }
        if ("Generate Time Table".equals(panelName)) {
            if (generateUI == null) {
                generateUI = new RightGenerateUI(timeTableApp);
                rightPanel.add(generateUI, "Generate Time Table");
            } else {
                generateUI.refreshTimeTable();
            }
        }
        cardLayout.show(rightPanel, panelName);
    }
}