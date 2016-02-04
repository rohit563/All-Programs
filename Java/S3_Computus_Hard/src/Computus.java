import java.util.Scanner;

public class Computus {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.print("Enter Year:"); // ask user for input
		Scanner scan = new Scanner(System.in);
		int yearOfEaster = Integer.parseInt(scan.nextLine());
		// add check for invalid input
		Easter e = new Easter();
		EasterTest test=new EasterTest();
		e.calc(yearOfEaster);
		System.out.println(e.edate);
		e.easterMed();
		test.test();
		scan.close();

	}

}
