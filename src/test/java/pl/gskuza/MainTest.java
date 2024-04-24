package pl.gskuza;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    public void testFindAvailableSlots() throws IOException {
        // Dane wejściowe
        ObjectMapper objectMapper = new ObjectMapper();
        Calendar calendar1 = Main.readCalendarFromJson("src/main/resources/calendar1.json", objectMapper);
        Calendar calendar2 = Main.readCalendarFromJson("src/main/resources/calendar2.json", objectMapper);

        LocalTime meetingDuration = LocalTime.of(0, 30);

        // Oczekiwany wynik
        List<Interval> expectedSlots = List.of(
                new Interval(LocalTime.of(11, 30), LocalTime.of(12, 0)),
                new Interval(LocalTime.of(15, 0), LocalTime.of(15, 30)),
                new Interval(LocalTime.of(18, 0), LocalTime.of(18, 30))
        );

        // Test
        List<Interval> result = Main.findAvailableSlots(calendar1, calendar2, meetingDuration);

        // Sprawdzenie, czy wynik jest zgodny z oczekiwaniami
        assertEquals(expectedSlots.size(), result.size());
        for (int i = 0; i < expectedSlots.size(); i++) {
            assertEquals(expectedSlots.get(i).getStart(), result.get(i).getStart());
            assertEquals(expectedSlots.get(i).getEnd(), result.get(i).getEnd());
        }
    }
}
