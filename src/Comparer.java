import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Comparer {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

		Scanner scan = new Scanner(System.in);

		// System.out.println("Enter # of implementations: ");
		int numImplementations = 4;// scan.nextInt();
		int printImplementation = 2;
		int currImplementation = 0;

		ArrayList<ArrayList<String>> implementationOuput = new ArrayList<ArrayList<String>>();
		PrintWriter writer = new PrintWriter("C:\\Users\\alebios2\\Desktop\\Adult Dataset output\\comparison_result", "UTF-8");
		//writer.println("The first line");
		//writer.println("The second line");
		//writer.close();
		/**
		 * while(currImplementation < numImplementations) {
		 * System.out.println("Enter ouput file path {" + (currImplementation+1)
		 * + "} :"); String filePath = scan.next();
		 * implementationOuput.add(readFile(filePath));
		 * 
		 * currImplementation++; }
		 **/
		implementationOuput
				.add(readFile("C:\\Users\\alebios2\\Desktop\\Adult Dataset output\\knime_adult.csv"));
		implementationOuput
				.add(readFile("C:\\Users\\alebios2\\Desktop\\Adult Dataset output\\naivebayesdataminer_adult.csv"));
		implementationOuput
				.add(readFile("C:\\Users\\alebios2\\Desktop\\Adult Dataset output\\rapidminer_adult.csv"));
		implementationOuput
				.add(readFile("C:\\Users\\alebios2\\Desktop\\Adult Dataset output\\sharpclassifier_adult.csv"));
		//implementationOuput
				//.add(readFile("C:\\Users\\alebios2\\Desktop\\Adult Dataset output\\knime_adult"));
		//implementationOuput
				//.add(readFile("C:\\Users\\alebios2\\Desktop\\Adult Dataset output\\knime_adult"));

		String[] labels = new String[implementationOuput.size()];
		ArrayList<ArrayList<Integer>> implementationMissedCases = new ArrayList<ArrayList<Integer>>();
		HashMap<String, Integer> dictionary;
		int i = 0, j = 0;
		while (i < implementationOuput.get(0).size()) {
			dictionary = new HashMap<String, Integer>();
			j = 0;
			while (j < numImplementations){//implementationOuput.get(i).size()) {
				
				//System.out.print(j + " " + " " + implementationOuput.get(j).get(i).toString() + " " + " " + implementationOuput.get(i).size());

				if (dictionary.containsKey(implementationOuput.get(j).get(i))) {
					Integer val = (Integer) dictionary.get(implementationOuput
							.get(j).get(i));
					dictionary.put(implementationOuput.get(j).get(i), val + 1);
				} else
					dictionary.put(implementationOuput.get(j).get(i), 1);

				labels[j] = implementationOuput.get(j).get(i);
				j++;
			}
			ArrayList<String> commonPrediction = new ArrayList<String>();
			int maxValueInMap = (Collections.max(dictionary.values())); // This will return max value in the Hashmap
			for (Object label : dictionary.keySet()) { // Iterate through hashmap
				if ((int) dictionary.get(label) == maxValueInMap) {
					commonPrediction.add((String) label);
				}
			}

			for (int l = 0; l < commonPrediction.size(); l++)
				writer.print(commonPrediction.get(l) + " ");
			writer.println();

			ArrayList<Integer> missedCases = new ArrayList<Integer>();

			for (int k = 0; k < labels.length; k++) {

				if (!commonPrediction.contains(labels[k])) {
					missedCases.add(k);
				}

			}

			implementationMissedCases.add(missedCases);
			i++;
		}

		for (int l = 0; l < implementationMissedCases.size(); l++) {
			for (int k = 0; k < implementationMissedCases.get(l).size(); k++) {
				writer.print((implementationMissedCases.get(l).get(k) + 1)
						+ " ");
				/**
				 * if(implementationMissedCases.get(l).get(k)+1 ==
				 * printImplementation) {
				 * 
				 * }
				 **/
			}
			writer.println();

		}
		writer.close();
		// input: number of implementations, location of output file for
		// different implementations,
		// optional param: print output comparison result to new txt file

		// input file format- classification labels listed vertically in file

		// Number of disparities in rows of input files
		// input file with most number of disparities
		// input file with least number of disparities

		// output:

	}

	static ArrayList<String> readFile(String filePath) {

		BufferedReader br = null;
		ArrayList<String> labels = null;
		try {
			br = new BufferedReader(new FileReader(filePath));

			String line = br.readLine();
			labels = new ArrayList<String>();
			while (line != null) {
				if (!line.trim().isEmpty()) {
					labels.add(line.trim());
				}
				line = br.readLine();
			}
		} catch (IOException ex) {
			System.out.println("File not found");
		}
		return labels;
	}
}
