package MDGP;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import TabuSearch.Tabu;

public class Individual implements Tabu {
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
			System.out.println(this);
			throw new IllegalArgumentException();
		}
	}

	public boolean check() {
		for(int i = 0; i != groups.size()-1;++i){
			Group first = groups.get(i);
			for(int j = i + 1 ; j != groups.size(); ++j)
			{
				Group second = groups.get(j);
				for(Instance instance : first){
					if(second.contains(instance))
						return false;
				}
			}
		}
		return true;
	}

	public List<Group> getGroups() {
		return groups;
	}
	
	@Override
	public Individual clone(){
		List<Group> groups = new ArrayList<Group>();
		for(Group g: this.groups){
			groups.add((Group) g.clone());
		}
		return new Individual(this.minSize, this.maxSize, groups);
	}
	
	public int getMinSize(){
		return minSize;
	}
	public int getMaxSize(){
		return maxSize;
	}
	
	public double score(){
		double score = 0;
		for(Group group : groups){
			score += group.score();
		}
		return score;
	}
	
	@Override
	public String toString(){
		String result = "";
		int count =0;
		for(Group group : groups){
			count++;
			result = result + "[ Group " + count + ": ";
			for(Instance inst : group){
				result = result + inst.number + " ";
			}
			result = result + "] \n";
		}
		result = result + "Score: " + this.score();
		return result;
	}
}