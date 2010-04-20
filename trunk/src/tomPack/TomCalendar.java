package tomPack;

import java.util.Calendar;

public class TomCalendar {

    @SuppressWarnings("nls")
    public static String getTime() {
	String time = "";
	time += getYear();
	time += "-" + getMonth();
	time += "-" + getDay();
	time += "-" + getHour();
	time += "-" + getMinute();
	time += "-" + getSecond();
	time += "-" + getMillisecond();
	return time;
    }

    public static int getYear() {
	return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static int getMonth() {
	return Calendar.getInstance().get(Calendar.MONTH + 1);
    }

    public static int getDay() {
	return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    public static int getHour() {
	return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinute() {
	return Calendar.getInstance().get(Calendar.MINUTE);
    }

    public static int getSecond() {
	return Calendar.getInstance().get(Calendar.SECOND);
    }

    public static int getMillisecond() {
	return Calendar.getInstance().get(Calendar.MILLISECOND);
    }

}
