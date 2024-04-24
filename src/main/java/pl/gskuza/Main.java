package pl.gskuza;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.time.Duration;

public class Main {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Calendar calendar1 = readCalendarFromJson("src/main/resources/calendar1.json", objectMapper);
            Calendar calendar2 = readCalendarFromJson("src/main/resources/calendar2.json", objectMapper);

            // Oczekiwany czas trwania spotkania
            LocalTime meetingDuration = LocalTime.of(0, 30);

            // Znajdź dostępne zakresy czasowe
            List<Interval> availableSlots = findAvailableSlots(calendar1, calendar2, meetingDuration);

            // Wyświetl wyniki
            System.out.println("Available time slots:");
            for (Interval interval : availableSlots) {
                System.out.println("[" + interval.getStart() + ", " + interval.getEnd() + "]");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Calendar readCalendarFromJson(String fileName, ObjectMapper objectMapper) throws IOException {
        return objectMapper.readValue(new File(fileName), Calendar.class);
    }
    public static List<Interval> findAvailableSlots(Calendar calendar1, Calendar calendar2, LocalTime meetingDuration) {
        List<Interval> availableSlots = new ArrayList<>();

        List<Interval> busySlots = mergeIntervals(calendar1.getPlannedMeetingIntervals(), calendar2.getPlannedMeetingIntervals());

        for (Interval busySlot : busySlots) {
            if (busySlot.duration().compareTo(Duration.ofMinutes(meetingDuration.getHour() * 60 + meetingDuration.getMinute())) >= 0) {
                LocalTime startOfSlot = busySlot.getStart();
                LocalTime endOfSlot = busySlot.getEnd();

                boolean isSlotAvailable = true;
                for (Interval otherSlot : busySlots) {
                    if (otherSlot.overlaps(new Interval(endOfSlot, endOfSlot.plus(Duration.ofMinutes(meetingDuration.getHour() * 60 + meetingDuration.getMinute()))))) {
                        isSlotAvailable = false;
                        break;
                    }
                }
                if (isSlotAvailable) {
                    availableSlots.add(new Interval(endOfSlot, endOfSlot.plus(Duration.ofMinutes(meetingDuration.getHour() * 60 + meetingDuration.getMinute()))));
                }
            }
        }
        return availableSlots;
    }
    public static List<Interval> mergeIntervals(List<Interval> intervals1, List<Interval> intervals2) {
        List<Interval> merged = new ArrayList<>();
        merged.addAll(intervals1);
        merged.addAll(intervals2);

        merged.sort((a, b) -> a.getStart().compareTo(b.getStart()));

        List<Interval> result = new ArrayList<>();
        Interval current = null;

        for (Interval interval : merged) {
            if (current == null || !current.overlaps(interval)) {
                current = interval;
                result.add(current);
            } else {
                current = current.merge(interval);
                result.set(result.size() - 1, current);
            }
        }
        return result;
    }
}

