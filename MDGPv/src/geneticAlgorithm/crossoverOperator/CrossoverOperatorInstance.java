package geneticAlgorithm.crossoverOperator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import MDGP.Distances;
import MDGP.Group;
import MDGP.Individual;
import MDGP.Instance;

public class CrossoverOperatorInstance implements CrossoverOperator<Individual> {

	@Override
	public List<Individual> crossover(Individual firstUncloned, Individual secondUncloned) {
		// IDee:
		/*
		 * 1) Kies de beste groep van een parent.. steek in kind 1 
		 * 2) doe zo door, maar verwijder de overlappende instances 
		 * 3) vul random de missende instances in.
		 */
		Individual first = firstUncloned.clone();
		Individual second = secondUncloned.clone();
		List<Instance> instances = getInstances(first);
		List<Group> groups = new ArrayList<Group>();
		int nrGroups = first.getGroups().size();
		for (int i = 0; i != nrGroups; ++i) {
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
		Group currentBest = new Group();
		double bestFitness = 0;
		Set<Group> allGroups = new HashSet<Group>(first.getGroups());
		allGroups.addAll(second.getGroups());
		int counter = 0;
		for(Group group : groups){
			allGroups.remove(group);
		}
		for (Group group : allGroups) {
			double fitness = fitnessAfterOverlap(groups, group);
			group = group.removeOverlap(groups);
			counter ++;
			if (fitness > bestFitness) {				
				currentBest = group;
				bestFitness = fitness;
			}
		}
		return currentBest;
	}

	private double fitnessAfterOverlap(List<Group> groups, Group fitnessGroup) {
		boolean contains = groups.contains(fitnessGroup);
		if(contains)
			return 0;
		for (Group group : groups) {
			if(group != fitnessGroup && group != null)
			for (Instance instance : group) {
				fitnessGroup.remove(instance);
			}
		}
		return fitnessGroup.score();
	}
}
