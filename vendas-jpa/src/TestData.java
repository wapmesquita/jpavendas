import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TestData {
	public static void main(String[] args) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		cal.getWeeksInWeekYear();
		Date time = cal.getTime();

	}
}
