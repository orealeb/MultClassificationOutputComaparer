import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Comparer {
	
	public static void main(String[] args) {
	
		  Scanner scan = new Scanner(System.in);

		  
		  System.out.println("Enter # of implementations: ");
		  int numImplementations = scan.nextInt();
		  
		  int currImplementation = 0;
		  
		  ArrayList<ArrayList<String>> implementationOuput= null;

		  while(currImplementation < numImplementations)
		  {
			  System.out.println("Enter ouput file path {" + currImplementation+1 + "} :");
			  String filePath = scan.next();
			  implementationOuput.add(readFile(filePath));
			  
			  currImplementation++;
		  }

		  String[] labels = new String[implementationOuput.size()];
		  Map dictionary = new HashMap();
		  ArrayList<ArrayList<Integer>> implementationMissedCases= null;

		  int i =0, j =0;
		  while(i < implementationOuput.size())
		  {
			  while(j < implementationOuput.get(i).size())
			  {
				  if(dictionary.containsKey(implementationOuput.get(i).get(j))) {
					              Integer val = (Integer) dictionary.get(implementationOuput.get(i).get(j));
					          dictionary.put(implementationOuput.get(i).get(j), val + 1);
					                  }
					                  else
					                      dictionary.put(implementationOuput.get(i).get(j), 1);
				  
				  labels[j] = implementationOuput.get(i).get(j);
				  j++;
			  }
			  int count = 0;
			  String commonPrediction = " ";
			  for(Object label: dictionary.keySet())
			  {
				        if((int)dictionary.get(label) > count )
				        {
				        	count = (int) dictionary.get(label);
				        	commonPrediction = (String) label;
				        }				      
			  }
			  
			  ArrayList<Integer> missedCase = null;

			  for(int k = 0; k < labels.length; k++)
			  {
				  if(!labels[k].equals(commonPrediction))
				  {
					  missedCase.add(k);
				  }
			  }
			  implementationMissedCases.add(missedCase);
			  i++;
		  }
		  
		  
		  
	//input: number of implementations, location of output file for different implementations, 
	//optional param: print output comparison result to new txt file

	//input file format- classification labels listed vertically in file
	
	
	//Number of disparities in rows of input files
	//input file with most number of disparities
	//input file with least number of disparities

	
	//output: 
	
	
	  }
	  
	  
	  
	  static ArrayList<String> readFile(String filePath)
	  {
		  
		  BufferedReader br = null;
		  ArrayList<String> labels = null;
		  try {
	            br = new BufferedReader(new FileReader(filePath));

	            String line = br.readLine();
	            labels = new  ArrayList<String> ();
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
	


