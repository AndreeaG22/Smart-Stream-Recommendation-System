import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.TimeZone;

public class DateAndTime {
    public String minutesSeconds = "%02d:%02d";
    public String hoursMinutesSeconds = "%02d:%02d:%02d";
    public String dateFormat = "dd-MM-yyyy";
    public String timeFormat(long time) {
        String finalFormat;
        Duration duration = Duration.ofSeconds(time);
        long hours = duration.toHours();
        duration = duration.minusHours(hours);
        long minutes = duration.toMinutes();
        duration = duration.minusMinutes(minutes);
        long seconds = duration.toSeconds();
        if(hours == 0)
            finalFormat = String.format(minutesSeconds, minutes, seconds);
        else
            finalFormat = String.format(hoursMinutesSeconds, hours, minutes, seconds);
        return finalFormat;
    }
    public String getDate(Long dateAdded) {
        DateFormat dataFormat = new SimpleDateFormat(dateFormat);
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        dataFormat.setTimeZone(timeZone);
        return dataFormat.format(dateAdded * 1000L);

    }
}
