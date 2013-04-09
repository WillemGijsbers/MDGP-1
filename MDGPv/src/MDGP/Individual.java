package MDGP;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import TabuSearch.Tabu;

public class Individual implements Tabu{
	// contains Groups
	private List<Group> groups = new ArrayList<Group>();
	private int minSize;
	private int maxSize;
	private int instances;

	public Individual(int minSize, int maxSize, List<Group> groups) {
		this.minSize = minSize;
		this.maxSize = maxSize;
		this.groups = groups;
		if (!this.check()) {
			throw new IllegalArgumentException();
		}
	}

	public boolean check() {
		int acc = 0;
		for (Group group : this.groups) {
			if (!group.check(this.minSize, this.maxSize))
				return false;
			acc += group.size();
		}
		return acc == this.instances;
	}

	public List<Group> getGroups() {
		return groups;
	}

	@Override
	public Individual clone() {
		List<Group> groups = new ArrayList<Group>();
		for (Group g : this.groups) {
			groups.add((Group) g.clone());
		}
		return new Individual(this.minSize, this.maxSize, groups);
	}

	public int getMinSize() {
		return minSize;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public double score() {
		double score = 0;
		for (Group group : groups) {
			score += group.score();
		}
		return score;
	}
}