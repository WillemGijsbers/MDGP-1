/**
 * 
 */
package MDGP.solvers;

import geneticAlgorithm.FitnessFunction.FitnessFunction;
import geneticAlgorithm.FitnessFunction.IndividualFitnessFunction;
import geneticAlgorithm.StopCriterion.MaxGenerationsStopCriterion;
import geneticAlgorithm.core.GeneticAlgorithm;
import geneticAlgorithm.crossoverOperator.CrossoverOperatorInstance;
import geneticAlgorithm.mutationOperator.MutationOperatorInstance;
import geneticAlgorithm.selectionOperator.RouletteWheelSelection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import MDGP.Distances;
import MDGP.Group;
import MDGP.Individual;

/**
 * @author Willem
 * 
 */
public class GeneticSolver {

	private static IndividualFitnessFunction ff;
	private static MutationOperatorInstance mo;
	private static CrossoverOperatorInstance co;
	private static RouletteWheelSelection<Individual, IndividualFitnessFunction> so;
	private static MaxGenerationsStopCriterion<Individual> sc;
	private static List<Individual> initialPopulation;

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String datafile = args[0];
		Distances.loadDistances(datafile);
		init();
		solve();
	}

	private static void init() {
		ff = new IndividualFitnessFunction();
		mo = new MutationOperatorInstance(0.10);
		co = new CrossoverOperatorInstance();
		so = new RouletteWheelSelection<Individual, IndividualFitnessFunction>(ff);
		sc = new MaxGenerationsStopCriterion<Individual>(10000);
		initialPopulation = Distances.getRandomSolutions(1000);
	}

	private static void solve() {
		Individual bestFromInitial = findBest(initialPopulation);
		GeneticAlgorithm<Individual, IndividualFitnessFunction, MutationOperatorInstance, CrossoverOperatorInstance, RouletteWheelSelection<Individual, IndividualFitnessFunction>, MaxGenerationsStopCriterion<Individual>> geneticAlgorithm = new GeneticAlgorithm<Individual, IndividualFitnessFunction, MutationOperatorInstance, CrossoverOperatorInstance, RouletteWheelSelection<Individual, IndividualFitnessFunction>, MaxGenerationsStopCriterion<Individual>>(
				ff, mo, co, so, sc, initialPopulation);
		System.out.println("Solutions");
		List<Individual> solved = geneticAlgorithm.execute();
		Individual bestFromSolved = findBest(solved);
//		System.out.println("Best from initial: ");
//		System.out.println(bestFromInitial);
//		System.out.println("Best from solved: ");
//		System.out.println(bestFromSolved);
	}

	private static Individual findBest(List<Individual> initialPopulation2) {
		double bestScore =0;
		Individual result = null;
		for (Individual indv : initialPopulation2){
			if(indv.score()> bestScore)
				result = indv;
		}
		return result;
	}
}
