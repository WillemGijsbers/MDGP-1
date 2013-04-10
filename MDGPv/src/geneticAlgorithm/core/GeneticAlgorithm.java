/**
 * 
 */
package geneticAlgorithm.core;

import geneticAlgorithm.FitnessFunction.FitnessFunction;
import geneticAlgorithm.StopCriterion.StopCriterion;
import geneticAlgorithm.crossoverOperator.CrossoverOperator;
import geneticAlgorithm.mutationOperator.MutationOperator;
import geneticAlgorithm.selectionOperator.SelectionOperator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GeneticAlgorithm<InstanceType,
						      FitnessFunctionType extends FitnessFunction<InstanceType>,
						      MutationOperatorType extends MutationOperator<InstanceType>,
							  CrossoverOperatorType extends CrossoverOperator<InstanceType>,
							  SelectionOperatorType extends SelectionOperator<InstanceType>,
							  StopCriterionType extends StopCriterion<InstanceType>>{
	FitnessFunctionType ff;
	MutationOperatorType mo;
	CrossoverOperatorType co;
	SelectionOperatorType so;
	StopCriterionType sc;
	List<InstanceType> currentGeneration;
	public GeneticAlgorithm(FitnessFunctionType ff,MutationOperatorType mo,CrossoverOperatorType co,SelectionOperatorType so,StopCriterionType sc,List<InstanceType> initialPopulation)
	{
		this.ff = ff;
		this.mo = mo;
		this.co = co;
		this.so = so;
		this.sc = sc;
		this.currentGeneration = initialPopulation;
	}

	public List<InstanceType> execute(){
		int nrGeneration = 0;
		InstanceType bestSolution = findBest(currentGeneration,null);
		while(sc.stop(currentGeneration)){
			nrGeneration++;
			System.out.print("Current generation = " + nrGeneration);
			System.out.println(" ==> Best solution = " + ff.fitness(bestSolution));
			List<InstanceType> selectedIndividuals = so.select(currentGeneration,currentGeneration.size());
			List<InstanceType> nextGeneration = new ArrayList<InstanceType>();
			InstanceType reserve = selectedIndividuals.get(0);
				while(selectedIndividuals.size() >= 2){
				int first = (int) (selectedIndividuals.size() * Math.random());
				InstanceType parentOne = selectedIndividuals.remove(first);
				int second = (int) (selectedIndividuals.size() * Math.random());
				InstanceType parentTwo = selectedIndividuals.remove(second);
				List<InstanceType> children = co.crossover(parentOne, parentTwo);
				for (InstanceType child : children){
					nextGeneration.add(mo.mutate(child));
				}
			if (selectedIndividuals.size() != 0)
			{
				InstanceType parent = selectedIndividuals.remove(0);
				List<InstanceType> childrenReserve = co.crossover(reserve, parent);
				for (InstanceType child : childrenReserve){
					nextGeneration.add(mo.mutate(child));
				}
			}
			currentGeneration = nextGeneration;
			}
			//bestSolution = findBest(currentGeneration,bestSolution);
			currentGeneration.add(bestSolution);
		}
		bestSolution = findBest(currentGeneration,bestSolution);
		System.out.println(" ==> Best solution = " + ff.fitness(bestSolution));
		return currentGeneration;
	}

	private InstanceType findBest(List<InstanceType> currentGeneration2,
			InstanceType object) {
		double bestFitness = 0;
		InstanceType result = object;
		if (object != null)
			bestFitness = ff.fitness(object);
		for(InstanceType inst : currentGeneration2){
			if(ff.fitness(inst) > bestFitness)
				result = inst;
		}
		return result;
	}
}
