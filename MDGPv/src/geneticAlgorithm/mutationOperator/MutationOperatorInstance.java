package geneticAlgorithm.mutationOperator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import MDGP.Group;
import MDGP.Individual;
import MDGP.Instance;

public class MutationOperatorInstance implements MutationOperator<Individual> {
	
	private double probability;
	public MutationOperatorInstance(double probability){
		if (probability < 0 || probability > 1)
			throw new IllegalArgumentException();
		this.probability = probability;
	}
	@Override
	public Individual mutate(Individual parent) {
		if (Math.random() > probability)
			return parent;
		if(parent.getGroups().size() < 2)
			return parent;
		int group1 = (int) (Math.random() * (parent.getGroups().size()));
		int group2 = 0;
		while (group1 == group2){
			group2 = (int) (Math.random() * (parent.getGroups().size()));
		}
		Group groupOne = parent.getGroups().get(group1);
		Group groupTwo = parent.getGroups().get(group2);
		parent.getGroups().remove(groupOne);
		parent.getGroups().remove(groupTwo);

		int maxNrInstancesToSwitch = 0;
		if(groupOne.size() < groupTwo.size())
			maxNrInstancesToSwitch = groupOne.size() - 2;
		else
			maxNrInstancesToSwitch = groupTwo.size() - 2;
		if (maxNrInstancesToSwitch < 0)
			return parent;
		
		int instancesToSwitch = (int) (Math.random() * maxNrInstancesToSwitch + 0.5);
		Collection<Instance> individualsFromGroupOne = getRandomInstancesFrom(groupOne, instancesToSwitch);
		Collection<Instance> individualsFromGroupTwo = getRandomInstancesFrom(groupTwo, instancesToSwitch);
		
		for(Instance instance : individualsFromGroupTwo)
			groupOne.add(instance);
		
		for(Instance instance : individualsFromGroupOne)
			groupTwo.add(instance);
		parent.getGroups().add(groupOne);
		parent.getGroups().add(groupTwo);
		return parent;
	}

	private Collection<Instance> getRandomInstancesFrom(List<Instance> instances,int N){
		Collection<Instance> result = new ArrayList<Instance>(N);
		for (int i = 0 ; i != N ; i++){
			int index = (int) (Math.random() * instances.size());
			result.add(instances.remove(index));
		}
		return result;
	}
}
