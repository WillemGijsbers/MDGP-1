package TabuSearch;

import java.util.List;

import MDGP.Individual;

public class TabuSearchIndividual extends TabuSearch {
	private boolean stopCondition() {
		//TODO
		return false;
	}
	
	private void updateTabu(Individual bestcan, Individual best){
		this.tabulist.add(best);
	}
	
	private void pruneCandidates(List<Individual> candidates) {
		for (Individual ind : candidates) {
			for (Tabu tabu: this.tabulist){
				tabu = (Individual) tabu;
				if (ind.equals(tabu)){
					candidates.remove(ind);
				}
			}
		}
	}
}
