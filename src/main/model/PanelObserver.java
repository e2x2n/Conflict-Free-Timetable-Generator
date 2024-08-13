package model;

import ui.TimeTableApp;

public interface PanelObserver {
    void update(String panelName, TimeTableApp timeTableApp);
}
