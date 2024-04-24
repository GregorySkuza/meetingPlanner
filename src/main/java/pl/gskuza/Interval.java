package pl.gskuza;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Interval {
    private final LocalTime start;
    private final LocalTime end;

    public Interval(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
    }
    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public boolean overlaps(Interval other) {
        return this.start.isBefore(other.getEnd()) && other.getStart().isBefore(this.end);
    }
    public Interval merge(Interval other) {
        LocalTime newStart = this.start.isBefore(other.getStart()) ? this.start : other.getStart();
        LocalTime newEnd = this.end.isAfter(other.getEnd()) ? this.end : other.getEnd();
        return new Interval(newStart, newEnd);
    }
    public List<Interval> splitBy(LocalTime startTime, LocalTime endTime) {
        List<Interval> intervals = new ArrayList<>();
        LocalTime current = this.start;

        while (current.isBefore(this.end)) {
            LocalTime next = current.plusMinutes(30); // zakładając, że spotkania są co pół godziny
            if (next.isAfter(this.end)) {
                next = this.end;
            }
            if (current.isAfter(startTime) && next.isBefore(endTime)) {
                intervals.add(new Interval(current, next));
            }
            current = next;
        }

        return intervals;
    }
    public Duration duration() {
        return Duration.between(start, end);
    }
}
