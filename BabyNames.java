import java.io.File;
import java.util.Scanner;

/**
 * This is BabyNames class that is responsible for actually running the program
 * It uses the arguments in the command line and then parses through for the
 * specified year.
 * 
 * @author NadiraDewji
 *
 */
public class BabyNames {
	public static void main(String[] args) {
		boolean cont = true;
		boolean isAllZero = true;
		StringBuilder myStringBuilder = new StringBuilder();
		/**
		 * If a year is not present then you should skip it. so if you are
		 * iterating from 2000 to 2014 and 2005 missing, handle it1
		 */

		if (args.length < 2 || args.length > 2) {
			throw new IllegalArgumentException("You are entering invalid arguments");
		}
		if (args[0] == "" || args[1] == "") {
			throw new IllegalArgumentException("You are entering invalid arguments");

			/**
			 * Try and convert the string into and integer if it is not possible
			 * then catch it and print illegal arguemnt
			 */
		}
		try {
			Integer.parseInt(args[0]);
			Integer.parseInt(args[1]);
		}

		catch (Exception e) {
			System.out.print("You have entered Illegal Arguments");
			System.exit(1);
		}

		/**
		 * Try and check whether the arguements are in the correct range
		 */
		try {
			if (Integer.parseInt(args[0]) < 1880 || Integer.parseInt(args[0]) >= 2015) {
				System.out.println(
						"You have entered invalid input in the commandline. Either a starting year less that 1880 or greater than 2015");
				System.exit(1);
			}
		} catch (Exception e) {
			System.out.println("You have entered invalid input in the commandline.");

			System.exit(1);
		}
		/**
		 * try and check whether the first arguement is less than the second
		 * argument.
		 */
		try {
			if (Integer.parseInt(args[0]) > Integer.parseInt(args[1])) {
				System.out.println("Your first argument is larger than your second argument");
				System.exit(1);
			}
		} catch (Exception e) {
			System.out.println("You have entered invalid input in the commandline.");
			System.exit(1);

		}
		/**
		 * If either of the arguments is not a valid year number in the range
		 * from 1880 to 2015, or if the second argument is smaller than the
		 * first, the program should print an error message and terminate.
		 */
		while (cont) {
			System.out.println(
					"Hello. Please enter a Baby Name and gender to get a historgram displaying statistics on this name or enter (Q) or (q) to quit");
			Scanner input = new Scanner(System.in);
			String myInput = input.nextLine();
			if (myInput.equals("q") || myInput.equals("Q")) {
				cont = false;
				System.out.println("Bye!");
			} else {
				System.out.println("Please specifiy the gender");
				Scanner inputGender = new Scanner(System.in);
				String myInputGender = input.nextLine();
				if (myInputGender.equalsIgnoreCase("F") || myInputGender.equalsIgnoreCase("M")) {
					int i = Integer.parseInt(args[0]);
					while (i <= Integer.parseInt(args[1])) {
						// System.out.println(i);
						YearNames myYearNames = new YearNames(i);
						File f = new File("data/yob" + i + ".txt");
						try {
							Scanner sc = new Scanner(f);
							/**
							 * For every line within a file, each line is turned
							 * into an array of strings using the comma to
							 * separate the components.
							 */
							while (sc.hasNextLine()) {
								String[] myNameComponents = sc.nextLine().split(",");

								try {
									Name myName = new Name(myNameComponents[0], myNameComponents[1],
											Integer.parseInt(myNameComponents[2]));
									if (myYearNames.exists(myName)) {
										continue;
									} else {
										myYearNames.add(myName);
									}
								} catch (Exception e) {
									continue;
								}

							}

							double percentage = 0;
							percentage = myYearNames.getFractionByName(myInput, myInputGender);
							if (percentage > 0) {
								isAllZero = false;
							}

							String myCalculation = String.format("%s    (%.4f) :  ", i, percentage);
							Double myAns = (Math.ceil(percentage / 0.01));
							// System.out.println(myYearNames.getCountByName(myInput,
							// myInputGender));

							/**
							 * This loop prints the histogram using "|" and it
							 * repeats the loop accordidng to the myAns variable
							 * length. WHich indicates how many lines to print.
							 */
							for (int k = 0; k < myAns; k++) {
								myCalculation += "|";
							}
							myStringBuilder.append(myCalculation + "\n");
							// System.out.println(myCalculation);

						} catch (Exception e) {
							i++;
							continue;
						}
						i++;

					}

				} else {
					System.out.println("Invalid Gender");
					// continue;
				}
			}
			if (isAllZero) {
				System.out.println("No such name in the dataset.");
			} else {
				System.out.println(myStringBuilder.toString());
				myStringBuilder = new StringBuilder();
				isAllZero = true;
			}

		}
	}
}
