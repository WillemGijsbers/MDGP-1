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
		while(sc.stop(currentGeneration)){
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
		}
		return currentGeneration;
	}
}
