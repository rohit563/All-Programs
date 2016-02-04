import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Easter {

	private int year;
	public String edate;
	public Date eTypeDate;
	private int month_e;
	private int day_e;

	public Easter() {

	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth_e() {
		return month_e;
	}

	public void setMonth_e(int month_e) {
		this.month_e = month_e;
	}

	public int getDay_e() {
		return day_e;
	}

	public void setDay_e(int day_e) {
		this.day_e = day_e;
	}

	public void calc(int input) {
		year = input;
		int a = year % 19;
		int b = year / 100;
		int c = year % 100;
		int d = b / 4;
		int e = b % 4;
		int f = (b + 8) / 25;
		int g = (b - f + 1) / 3;
		int h = (19 * a + b - d - g + 15) % 30;
		int i = c / 4;
		int k = c % 4;
		int l = (32 + 2 * e + 2 * i - h - k) % 7;
		int m = (a + 11 * h + 22 * l) / 451;
		int n = (h + l - 7 * m + 114) / 31;
		int p = (h + l - 7 * m + 114) % 31;
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.clear();
		calendar.set(year, n - 1, p + 1);
		SimpleDateFormat datee = new SimpleDateFormat("MMMM dd, yyyy");
		setMonth_e(n);
		setDay_e(p);
		eTypeDate = calendar.getTime();
		edate = datee.format(calendar.getTime());

	}

	public void easterMed() {
		int[] aprileaster = new int[31];
		int[] marcheaster = new int[32];

		for (int i = 0; i < 5700000; i++) {
			calc(i);
			if (getMonth_e() == 3) {
				marcheaster[getDay_e() + 1] = marcheaster[getDay_e() + 1] + 1;
			} else if (getMonth_e() == 4) {
				aprileaster[getDay_e() + 1] = aprileaster[getDay_e() + 1] + 1;
			} else {
				System.out.println("Error");
			}

		}

		for (int i = 0; i < 32; i++) {
			if (marcheaster[i] != 0) {
				System.out.println("March " + i + ", " + marcheaster[i]);
			}
		}

		System.out.println("--------------------------------------------------------");
		for (int i = 0; i < 31; i++) {
			if (aprileaster[i] != 0) {
				System.out.println("April " + i + ", " + aprileaster[i]);
			}
		}

	}

}