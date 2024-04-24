package pl.gskuza;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Calendar {
    @JsonProperty("working_hours")
    private WorkingHours workinghours;
    @JsonProperty("planned_meeting")
    private List<Meeting> plannedMeeting;

    public WorkingHours getWorkingHours() {
        return workinghours;
    }

    public void setWorkingHours(WorkingHours working_hours) {
        this.workinghours = working_hours;
    }

    public List<Meeting> getPlannedMeeting() {
        return plannedMeeting;
    }

    public void setPlannedMeeting(List<Meeting> planned_meeting) {
        this.plannedMeeting = planned_meeting;
    }

    public List<Interval> getPlannedMeetingIntervals() {
        List<Interval> intervals = new ArrayList<>();
        for (Meeting meeting : plannedMeeting) {
            intervals.add(new Interval(meeting.getStart(), meeting.getEnd()));
        }
        return intervals;
    }
}
