import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

/**
 * 
 */

/**
 * @author rohit
 *
 */
public class EasterTest {

	@Test
	public void test() {
		try {
			FileReader reader = new FileReader("Dates.txt");
			BufferedReader bufferedReader = new BufferedReader(reader);
			ArrayList<Date> list = new ArrayList<>();
			String line;
			Date easterDate;
			Date calcDate = null;
			DateFormat formatter;
			String placehold;
			String eArray[];
			int eYear;

			formatter = new SimpleDateFormat("yyyy MMMM dd");
			while ((line = bufferedReader.readLine()) != null) {
				try {
					easterDate = (Date) formatter.parse(line);
					list.add(easterDate);

				} catch (ParseException e) {
					System.out.println("Exception :" + e);
				}
			}

			Easter e = new Easter();

			for (int i = 0; i < list.size(); i++) {

				placehold = formatter.format(list.get(i));
				eArray = placehold.split(" ");
				eYear = Integer.parseInt(eArray[0]);
				e.calc(eYear);
				calcDate = e.eTypeDate;
				assertEquals("Error in date test",list.get(i), calcDate);

			}

			reader.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
