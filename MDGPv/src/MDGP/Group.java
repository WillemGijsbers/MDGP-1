package MDGP;

import java.util.ArrayList;
import java.util.List;

public class Group extends ArrayList<Instance> {

	// contains instances

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean check(int minSize, int maxSize) {
		if (this.size() < minSize)
			return false;
		if (this.size() > maxSize)
			return false;
		return true;
	}
	
	@Override
	public Group clone(){
		Group g = new Group();
		for(Instance i: this){
			g.add(i.clone());
		}
		return g;
	}

	public double score() {
		double score = 0;
		if(size() < 2)
			return 0;
		for(int i = 0 ; i != this.size() -1 ; ++i){
			for(int j = i +1 ; j != this.size() ; ++j){
				try{
					score += Distances.getDistance(this.get(i), this.get(j));
				}catch(IndexOutOfBoundsException e){
					System.out.println("Index out of bounds: i = " + i + " j = " + j + "Size = " + this.size());
				}
			}
		}
		return score;
	}

	public Group removeOverlap(List<Group> groups) {
		for(Group group : groups){
			this.removeAll(group);
		}
		return this;
	}

}