package pl.gskuza;

public class Time {
    private final int hour;
    private final int minute;
    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }
    public int getHour() {
        return hour;
    }
    public int getMinute() {
        return minute;
    }
    @Override
    public String toString() {
        return String.format("%02d:%02d", hour, minute);
    }
}
