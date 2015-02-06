import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Comparer {

	//poker dataset stats variables
	static HashMap<String, Integer> stats;


	public static void main(String[] args) throws IOException {

		Scanner scan = new Scanner(System.in);
		stats = new HashMap<String, Integer>();
		// System.out.println("Enter # of implementations: ");
		int numImplementations = 9;// scan.nextInt();
		int printImplementation = 1;
		int currImplementation = 0;

		ArrayList<ArrayList<String>> implementationOuput = new ArrayList<ArrayList<String>>();
		//PrintWriter writer = new PrintWriter("C:\\Users\\alebios2\\Desktop\\Poker Dataset\\comparison_result", "UTF-8");
		PrintWriter writer2 = new PrintWriter("C:\\Users\\alebios2\\Desktop\\Poker Dataset\\comparison_result4", "UTF-8");

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
				.add(readFile("C:\\Users\\alebios2\\Desktop\\Poker Dataset\\bayesian_poker.txt"));
		implementationOuput
				.add(readFile("C:\\Users\\alebios2\\Desktop\\Poker Dataset\\naivebayesminer_poker"));
		implementationOuput
				.add(readFile("C:\\Users\\alebios2\\Desktop\\Poker Dataset\\rapidminer_poker.csv"));
		implementationOuput
				.add(readFile("C:\\Users\\alebios2\\Desktop\\Poker Dataset\\sharpclassifier_poker.csv"));
		implementationOuput
				.add(readFile("C:\\Users\\alebios2\\Desktop\\Poker Dataset\\naivebayes5_poker"));
		implementationOuput
			.add(readFile("C:\\Users\\alebios2\\Desktop\\Poker Dataset\\knime_poker2.csv"));
		implementationOuput
			.add(readFile("C:\\Users\\alebios2\\Desktop\\Poker Dataset\\naivebayes1_poker.txt"));
		implementationOuput
			.add(readFile("C:\\Users\\alebios2\\Desktop\\Poker Dataset\\naivebayesclassifier_poker.txt"));
		implementationOuput
			.add(readFile("C:\\Users\\alebios2\\Desktop\\Poker Dataset\\weka_poker2"));
	//implementationOuput
				//.add(readFile("C:\\Users\\alebios2\\Desktop\\Adult Dataset output\\knime_adult"));

		String[] labels = new String[implementationOuput.size()];
		ArrayList<ArrayList<Integer>> implementationMissedCases = new ArrayList<ArrayList<Integer>>();
		HashMap<String, Integer> dictionary;
		String printcommonPredictions = "";
		String printMissedCases = "";
		//int truePos = 0, trueNeg = 0;
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
			{
				printcommonPredictions += commonPrediction.get(l)+ " ";
				//writer.print(commonPrediction.get(l) + " ");
			}
			//writer.println();
			printcommonPredictions += "\n";
			
			ArrayList<Integer> missedCases = new ArrayList<Integer>();

			for (int k = 0; k < labels.length; k++) {

				if (!commonPrediction.contains(labels[k])) {
					missedCases.add(k);
				}

			}

			//check if implementation selected is fp or tp for binary class labels
			/** if (commonPrediction.contains("+1")) {
			if (!commonPrediction.contains(labels[printImplementation])) {
				
				truePos++;
				//missedCases.add(k);
			}
			else
				trueNeg++;
			 }
			 if (commonPrediction.contains("-1")) {
					if (!commonPrediction.contains(labels[printImplementation])) {
						
						trueNeg++;
						//missedCases.add(k);
					}
					else
						truePos++;
			 }**/
			 
			 
			 //poker data set obtain fp or tp for class labels  0 to 9
			updatePokerDatasetStats(commonPrediction,labels[printImplementation],10);
			 
			 
			implementationMissedCases.add(missedCases);
			i++;
		}

		for (int l = 0; l < implementationMissedCases.size(); l++) {
			for (int k = 0; k < implementationMissedCases.get(l).size(); k++) {
				//writer.print((implementationMissedCases.get(l).get(k) + 1)
				//		+ " ");
				printMissedCases += implementationMissedCases.get(l).get(k) + 1 + " ";
				
				 /** if(implementationMissedCases.get(l).get(k)+1 ==
				  printImplementation) {
				 
					  
					  
				  }**/
				 
			}
			//writer.println();
			printMissedCases += "\n";

		}
		//writer.close();
		BufferedReader bufReader = new BufferedReader(new StringReader(printcommonPredictions));
		BufferedReader bufReader2 = new BufferedReader(new StringReader(printMissedCases));

		String line = null;
		String line2 = null;

		while((line = bufReader.readLine()) != null)
		{
			if((line2 = bufReader2.readLine()) != null)
			writer2.println(line  + " " + line2);
			else
				writer2.println(line);

			
		}
		
		writer2.println("Implementation " + printImplementation + " Details:");
		for (String name: stats.keySet()){

            String key =name.toString();
            String value = stats.get(name).toString();  
            writer2.println(key + " " + value);  


		}
		writer2.close();

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
	
	
	static void updatePokerDatasetStats(ArrayList<String>commonPrediction, String implementationPrediction, int numLabels)
	{
	
		for(int i = 0; i < numLabels; i++)
		{
			 if (commonPrediction.size() < 2 && commonPrediction.contains(Integer.toString(i))) {
				
				/** if(i ==7)
				 {
					 i=7;
				 }**/
				 
				 if (commonPrediction.contains(implementationPrediction)) {
						
						if (stats.containsKey(i+"correct")) {
							Integer val = (Integer) stats.get(i+"correct");
							stats.put(i+"correct", val + 1);
						} else
							stats.put(i+"correct", 1);
					}
					else
						if (stats.containsKey(i+"misclassify")) {
							Integer val = (Integer) stats.get(i+"misclassify");
							stats.put(i+"misclassify", val + 1);
						} else
							stats.put(i+"misclassify", 1);
					}
			
			
		}
		
	
	}
}
