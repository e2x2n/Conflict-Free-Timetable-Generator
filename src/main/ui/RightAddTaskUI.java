package ui;

import model.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * The right panel user interface for adding a task in the TimeTableGenerator application.
 * It sets up the right panel and its components for the application.
 */
public class RightAddTaskUI extends JPanel {
    private JPanel rightPanel;
    private JLabel headline;
    private JLabel priorityLabel;
    private JPanel priorityPanel;
    private JLabel titleLabel;
    private JTextField titleField;
    private JLabel descriptionLabel;
    private JTextField descriptionField;
    private JLabel categoryLabel;
    private JTextField categoryField;
    private JLabel timeslotLabel;
    private JTextField timeslotField;
    private JButton addButton;
    private JButton clearButton;
    private ButtonGroup priorityGroup;
    private TimeTableApp timeTableApp;

    // MODIFIES: this
    // EFFECTS: Initializes a new RightAddTaskUI with a new TimeTableApp and JPanel.
    public RightAddTaskUI(TimeTableApp timeTableApp) {
        this.timeTableApp = timeTableApp;
        rightPanel = new JPanel();
        rightPanel.setLayout(new GridBagLayout());
        rightPanel.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        getButtons(gbc);

        this.add(rightPanel);
    }

    // MODIFIES: this
    // EFFECTS: Initializes the components for the right panel.
    private void getButtons(GridBagConstraints gbc) {
        getHeadline(gbc);
        getPriority(gbc);
        getTitle(gbc);
        getDescription(gbc);
        getCategory(gbc);
        getTimeslot(gbc);

        addButton = new JButton("Add");
        rightPanel.add(addButton, gbc);
        addButton.addActionListener(this::addTask);


        clearButton = new JButton("Clear");
        rightPanel.add(clearButton, gbc);
        clearButton.addActionListener(this::clearText);
    }

    // EFFECTS: Returns the selected priority from the priority panel.
    private int getSelectedPriority() {
        for (Component component : priorityPanel.getComponents()) {
            if (component instanceof JRadioButton) {
                JRadioButton radioButton = (JRadioButton) component;
                if (radioButton.isSelected()) {
                    return Integer.parseInt(radioButton.getText());
                }
            }
        }
        return -1;
    }

    // MODIFIES: this
    // EFFECTS: Initializes the headline component for the right panel.
    private void getHeadline(GridBagConstraints gbc) {
        headline = new JLabel("Add a New Task", SwingConstants.CENTER);
        rightPanel.add(headline, gbc);
        headline.setFont(new Font("Arial", Font.BOLD, 24));
    }

    // MODIFIES: this
    // EFFECTS: Initializes the priority components for the right panel.
    private void getTimeslot(GridBagConstraints gbc) {
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        timeslotLabel = new JLabel("Time Slots: ");
        rightPanel.add(timeslotLabel, gbc);
        timeslotLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        timeslotField = new JTextField("eg. Mon 13:00-15:00, Wed 07:00-09:20");
        rightPanel.add(timeslotField, gbc);
    }

    // MODIFIES: this
    // EFFECTS: Initializes the category components for the right panel.
    private void getCategory(GridBagConstraints gbc) {
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        categoryLabel = new JLabel("Category: ");
        rightPanel.add(categoryLabel, gbc);
        categoryLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        categoryField = new JTextField(30);
        rightPanel.add(categoryField, gbc);
    }

    // MODIFIES: this
    // EFFECTS: Initializes the description components for the right panel.
    private void getDescription(GridBagConstraints gbc) {
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        descriptionLabel = new JLabel("Description: ");
        rightPanel.add(descriptionLabel, gbc);
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        descriptionField = new JTextField(30);
        rightPanel.add(descriptionField, gbc);
    }

    // MODIFIES: this
    // EFFECTS: Initializes the title components for the right panel.
    private void getTitle(GridBagConstraints gbc) {
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        titleLabel = new JLabel("Title: ");
        rightPanel.add(titleLabel, gbc);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        titleField = new JTextField(30);
        rightPanel.add(titleField, gbc);
    }

    // MODIFIES: this
    // EFFECTS: Initializes the priority components for the right panel.
    private void getPriority(GridBagConstraints gbc) {
        priorityPanel = new JPanel(new GridLayout(1,5));
        priorityGroup = new ButtonGroup();
        for (int i = 1; i <= 5; i++) {
            JRadioButton priorityButton = new JRadioButton(Integer.toString(i));
            priorityGroup.add(priorityButton);
            priorityPanel.add(priorityButton);
        }

        gbc.gridwidth = GridBagConstraints.RELATIVE;
        priorityLabel = new JLabel("Priority: ");
        rightPanel.add(priorityLabel, gbc);
        priorityLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        rightPanel.add(priorityPanel, gbc);
    }

    // MODIFIES: this
    // EFFECTS: Adds a task to the schedule based on the user input.
    private void addTask(ActionEvent e) {
        Task task = new Task(titleField.getText(), descriptionField.getText(),
                getSelectedPriority(), categoryField.getText(), timeslotField.getText());
        timeTableApp.getSchedule().addTask(task);
    }

    // MODIFIES: this
    // EFFECTS: Clears the text fields in the right panel.
    private void clearText(ActionEvent e) {
        titleField.setText("");
        descriptionField.setText("");
        categoryField.setText("");
        timeslotField.setText("");
    }
}
