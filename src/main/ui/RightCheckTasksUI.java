package ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import model.Task;

/**
 * The right panel user interface for checking tasks in the TimeTableGenerator application.
 * It sets up the right panel and its components for the application.
 */
public class RightCheckTasksUI extends JPanel {
    private TimeTableApp timeTableApp;
    private JTextArea taskTextArea;
    private JButton backButton;
    private JButton removeButton;
    private JComboBox<Integer> filterComboBox;
    private JTextField indexField;
    private JLabel priorityLabel;

    // MODIFIES: this
    // EFFECTS: Initializes a new RightCheckTasksUI with a new TimeTableApp.
    public RightCheckTasksUI(TimeTableApp timeTableApp) {
        this.timeTableApp = timeTableApp;
        taskTextArea = new JTextArea();
        taskTextArea.setEditable(false);
        taskTextArea.setLineWrap(true);
        backButton = new JButton("Back");
        removeButton = new JButton("Remove");
        priorityLabel = new JLabel("Priority: ");
        filterComboBox = new JComboBox<>(new Integer[]{null, 1, 2, 3, 4, 5});
        filterComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value == null) {
                    setText("Show All");
                }
                return this;
            }
        });
        filterComboBox.setSelectedIndex(-1); // No selection at startup
        indexField = new JTextField(5);
        generateTaskList();

        addLayouts(timeTableApp);
    }

    // MODIFIES: this
    // EFFECTS: Initializes the layout for the right panel.
    private void addLayouts(TimeTableApp timeTableApp) {
        this.setLayout(new BorderLayout());

        JPanel southPanel = init();

        this.add(southPanel, BorderLayout.CENTER);

        buttonInit(timeTableApp);
    }

    // MODIFIES: this
    // EFFECTS: Initializes the buttons for the right panel.
    private void buttonInit(TimeTableApp timeTableApp) {
        backButton.addActionListener(e -> {
            ((CardLayout)(this.getParent()).getLayout()).show(this.getParent(), "Add Task");
        });

        removeButton.addActionListener(e -> {
            int index = Integer.parseInt(indexField.getText()) - 1;
            Task task = timeTableApp.getSchedule().getTasks().get(index);
            timeTableApp.getSchedule().removeTask(task);
            refreshTaskList();
        });

        filterComboBox.addActionListener(e -> {
            filterTasksByPriority((Integer) filterComboBox.getSelectedItem());
        });
    }

    // MODIFIES: this
    // EFFECTS: Initializes the layout for the south panel.
    private JPanel init() {
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(new JScrollPane(taskTextArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(removeButton);
        buttonPanel.add(indexField);
        buttonPanel.add(priorityLabel);
        buttonPanel.add(filterComboBox);
        buttonPanel.add(backButton);
        southPanel.add(buttonPanel, BorderLayout.NORTH);
        return southPanel;
    }

    // MODIFIES: this
    // EFFECTS: Generates a list of tasks and appends them to the JTextArea.
    private void generateTaskList() {
        int counter = 1;
        for (Task task : timeTableApp.getSchedule().getTasks()) {
            taskTextArea.append(counter + ". " + task.printTask() + "\n");
            counter++;
        }
    }

    // MODIFIES: this
    // EFFECTS: Sets the text of the JTextArea to an empty string and generates a new task list.
    public void refreshTaskList() {
        taskTextArea.setText("");
        generateTaskList();
    }

    // MODIFIES: this
    // EFFECTS: Filters the tasks by priority and appends them to the JTextArea.
    private void filterTasksByPriority(Integer priority) {
        taskTextArea.setText("");
        List<Task> sortedTasks = new ArrayList<>(timeTableApp.getSchedule().getTasks());
        sortedTasks.sort(Comparator.comparing(Task::getPriority));

        int counter = 1;
        for (Task task : sortedTasks) {
            if (priority == null || task.getPriority() == priority) {
                taskTextArea.append(counter + ". " + task.printTask() + "\n");
                counter++;
            }
        }
    }
}