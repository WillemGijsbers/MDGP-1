/**
 * 
 */
package geneticAlgorithm.FitnessFunction;

import java.util.Collection;

/**
 * @author Willem
 *
 */
public abstract class FitnessFunction<T> {
	public abstract double fitness(T individual);
	public T getBestIndividual(Collection<T> individuals){
		T best = null;
		double bestFitness = 0;
		for(T individual : individuals)
		{
			if (fitness(individual) > bestFitness)
				best = individual;
		}
		return best;
	}
}
