package geneticAlgorithm.crossoverOperator;

import java.util.ArrayList;
import java.util.List;

import MDGP.Distances;
import MDGP.Group;
import MDGP.Individual;
import MDGP.Instance;

public class CrossoverOperatorInstance implements CrossoverOperator<Individual> {

	@Override
	public List<Individual> crossover(Individual first, Individual second) {
		// IDee:
		/*
		 * 1) Kies de beste groep van een parent.. steek in kind 1 
		 * 2) doe zo door, maar verwijder de overlappende instances 
		 * 3) vul random de missende instances in.
		 */
		List<Instance> instances = getInstances(first);
		List<Group> groups = new ArrayList<Group>();
		for (int i = 0; i != first.getGroups().size(); ++i) {
			Group group = findBestGroupAfterOverlap(groups, first, second);
			groups.add(group);
		}
		repair(groups, instances);
		Individual result = new Individual(Distances.MIN_SIZE, Distances.MAX_SIZE,
				groups);
		List<Individual> resultList = new ArrayList<Individual>();
		resultList.add(result);
		return resultList;
	}

	private List<Instance> getInstances(Individual first) {
		List<Instance> result = new ArrayList<Instance>();
		for (Group group : first.getGroups()) {
			for (Instance instance : group) {
				result.add(instance);
			}
		}
		return result;
	}

	private void repair(List<Group> groups, List<Instance> allInstances) {
		for (Group group : groups) {
			for (Instance instance : group) {
				allInstances.remove(instance);
			}
		}
		for (Instance instance : allInstances) {
			for (Group group : groups) {
				if(group.size() != Distances.MAX_SIZE){
					group.add(instance);
					break;
				}
			}
		}
	}

	private Group findBestGroupAfterOverlap(List<Group> groups,
			Individual first, Individual second) {
		Group currentBest = null;
		double bestFitness = 0;
		List<Group> allGroups = first.getGroups();
		allGroups.addAll(second.getGroups());
		for (Group group : allGroups) {
			double fitness = fitnessAfterOverlap(groups, group);
			if (fitness > bestFitness) {
				currentBest = group;
				fitness = bestFitness;
			}
		}
		return currentBest;
	}

	private double fitnessAfterOverlap(List<Group> groups, Group fitnessGroup) {
		for (Group group : groups) {
			for (Instance instance : group) {
				fitnessGroup.remove(instance);
			}
		}
		return fitnessGroup.score();
	}
}
