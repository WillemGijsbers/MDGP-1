/**
 * 
 */
package MDGP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Willem
 * 
 */
public class Distances {
	public static int MIN_SIZE = 10;
	public static int MAX_SIZE = 10;
	public static int numberOfElements;
	public static int numberOfGroups;
	private static double[][] data;
	/**
	 * True = v2, false = v1;
	 */
	private static boolean type = false;

	public static void loadDistances(String fileString) throws IOException {
		File file = new File(fileString);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		line = br.readLine();
		parseFirstLine(line);
		data = new double[numberOfElements][numberOfElements];
		while ((line = br.readLine()) != null) {
			String[] args = line.split(" ");
			data[Integer.parseInt(args[0])][Integer.parseInt(args[1])] = Double
					.parseDouble(args[2]);
			data[Integer.parseInt(args[1])][Integer.parseInt(args[0])] = Double
					.parseDouble(args[2]);
		}
		br.close();
	}

	private static void parseFirstLine(String line) {
		String[] args = line.split(" ");
		/*
		 * M: Integer indicating the number of elements G: Integer indicating
		 * the The number of groups Group Type: The value can be "ss" or "ds"
		 * and represent "same size group" or "different size group" Group
		 * limits: The last numbers of the line correspond to the lower and
		 * upper limits of each group
		 */
		numberOfElements = Integer.parseInt(args[0]);
		numberOfGroups = Integer.parseInt(args[1]);
		type = args[2] == "ds";
		MIN_SIZE = Integer.parseInt(args[3]);
		MAX_SIZE = Integer.parseInt(args[4]);
	}

	public static double getDistance(Instance one, Instance two) {
		return data[one.number][two.number];
	}

	public static void main(String[] args) throws IOException {
		loadDistances(args[0]);
		System.out.println(numberOfElements);
		for (int i = 0; i != numberOfElements; ++i) {
			for (int j = 0; j != numberOfElements; ++j)
				System.out.print(data[i][j] + " ");
			System.out.println("");
		}
	}

	private static List<Instance> individuals;

	public static List<Instance> getInstances(){
		if(individuals != null)
			return individuals;
		else{
			individuals = new ArrayList<Instance>();
			for (int i = 0 ; i != numberOfElements ; ++i){
				individuals.add(new Instance(i));
			}
		}
		return individuals;
	}
	
	public static Collection<Individual> getRandomSolutions(int numberOfIndividuals) {
		Collection<Individual> result = new ArrayList<Individual>();
		for(int nr = 0; nr != numberOfIndividuals ; ++nr){
			List<Group> groups = new ArrayList<Group>();
			for(int i = 0 ; i != Distances.numberOfGroups; ++i){
				Group group = new Group();
				while (group.size() !=  Distances.MAX_SIZE)
				{
				}
			}
			
			
		}
		return null;
	}
}
