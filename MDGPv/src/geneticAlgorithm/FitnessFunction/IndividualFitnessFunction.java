package geneticAlgorithm.FitnessFunction;

import MDGP.Distances;
import MDGP.Group;
import MDGP.Individual;

public class IndividualFitnessFunction extends FitnessFunction<Individual>{

	@Override
	public double fitness(Individual individual) {
		double fitness = 0;
		for(Group group : individual.getGroups()){
			for(int i = 0 ; i != group.size()-1 ;++i){
				for (int j = i+1 ; j != group.size() ;++j){
					fitness += Distances.getDistance(group.get(i),group.get(j));
				}
			}
		}
		return 0;
	}
	

}
