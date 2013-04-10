package geneticAlgorithm.FitnessFunction;

import MDGP.Distances;
import MDGP.Group;
import MDGP.Individual;

public class IndividualFitnessFunction extends FitnessFunction<Individual>{

	@Override
	public double fitness(Individual individual) {
		double fitness = 0;
		return individual.score();
	}
	

}
