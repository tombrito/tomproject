package tomPack;

import java.util.Calendar;

public class TomCalendar {

    public static String getDateAndTime() {
	StringBuilder time = new StringBuilder();
	time.append(getDate());
	time.append("_"); //$NON-NLS-1$
	time.append(getTimeMilliseconds());
	return time.toString();
    }

    @SuppressWarnings("boxing")
    public static String getTime() {

	int hour = getHour();
	int minute = getMinute();
	int second = getSecond();

	StringBuilder time = new StringBuilder();
	time.append((hour <= 9) ? ("0" + hour) : hour); //$NON-NLS-1$
	time.append("-"); //$NON-NLS-1$
	time.append((minute <= 9) ? ("0" + minute) : minute); //$NON-NLS-1$
	time.append("-"); //$NON-NLS-1$
	time.append((second <= 9) ? ("0" + second) : second); //$NON-NLS-1$

	return time.toString();
    }

    public static String getTimeMilliseconds() {

	StringBuilder millis = new StringBuilder();
	millis.append(getMillisecond());
	while (millis.length() < 3) {
	    millis.insert(0, "0"); //$NON-NLS-1$
	}

	StringBuilder timeMillis = new StringBuilder();
	timeMillis.append(getTime());
	timeMillis.append("_"); //$NON-NLS-1$
	timeMillis.append((millis));

	return timeMillis.toString();
    }

    @SuppressWarnings("boxing")
    public static String getDate() {

	StringBuilder date = new StringBuilder();
	int year = getYear();
	int month = getMonth();
	int day = getDay();

	date.append(year);
	date.append("-"); //$NON-NLS-1$
	date.append((month <= 9) ? ("0" + month) : month); //$NON-NLS-1$
	date.append("-"); //$NON-NLS-1$
	date.append((day <= 9) ? ("0" + day) : day); //$NON-NLS-1$

	return date.toString();
    }

    public static int getYear() {
	return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static int getMonth() {
	return (Calendar.getInstance().get(Calendar.MONTH) + 1);
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

    public static void main(String[] args) {
	System.out.println(TomCalendar.getDateAndTime());
    }

}
