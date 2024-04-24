package pl.gskuza;

import java.time.LocalTime;

public class WorkingHours {
    private String start;
    private String end;

    public LocalTime getStart() {
        return LocalTime.parse(start);
    }
    public void setStart(String start) {
        this.start = start;
    }
    public LocalTime getEnd() {
        return LocalTime.parse(end);
    }
    public void setEnd(String end) {
        this.end = end;
    }
}
