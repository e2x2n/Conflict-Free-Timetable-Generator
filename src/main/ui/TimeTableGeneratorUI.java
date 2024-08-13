package ui;

import model.Event;
import model.EventLog;
import model.Schedule;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The user interface for the TimeTableGenerator application.
 * It sets up the main frame and panels for the application.
 */
public class TimeTableGeneratorUI extends JFrame {
    private TimeTableApp timeTableApp;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;
    private JFrame mainFrame;
    private JPanel mainPanel;
    private Schedule schedule;


    // MODIFIES: this
    // EFFECTS: Initializes a new TimeTableGeneratorUI with a new TimeTableApp and Schedule.
    public TimeTableGeneratorUI() throws FileNotFoundException {
        timeTableApp = new TimeTableApp();
        schedule = new Schedule();

        init();

        LeftMenuUI leftMenuUI = new LeftMenuUI(timeTableApp);
        mainPanel.add(leftMenuUI, BorderLayout.WEST);
        RightPanelsUI rightPanelsUI = new RightPanelsUI(timeTableApp);
        mainPanel.add(rightPanelsUI, BorderLayout.CENTER);

        leftMenuUI.addObserver(rightPanelsUI);
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);

        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                printLogs();
            }
        });
    }


    // MODIFIES: this
    // EFFECTS: Sets up the main frame and panel with the specified layout, size, and border.
    private void init() {
        mainFrame = new JFrame("Conflict-free Time Table Generator");
        mainFrame.setLayout(new GridLayout(1, 2));

        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainPanel = new JPanel(new BorderLayout(60, 60));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(60, 60, 60, 60));
    }

    private void printLogs() {
        EventLog eventLog = EventLog.getInstance();
        for (Event event : eventLog) {
            System.out.println(event + "\n");
        }
    }

    // EFFECTS: Creates a new instance of the TimeTableGeneratorUI.
    public static void main(String[] args) throws FileNotFoundException {
        new TimeTableGeneratorUI();
    }
}