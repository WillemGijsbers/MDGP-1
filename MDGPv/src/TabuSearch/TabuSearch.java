package TabuSearch;

import java.util.ArrayList;
import java.util.List;

import MDGP.Group;
import MDGP.Individual;
import MDGP.Instance;

public class TabuSearch {

	protected List<Tabu> tabulist;
	protected final int tabusize = 500;
	
	private Individual prevbest = null;
	private Individual best = null;
	

	public Individual search(Individual ind) {
		best = ind;
		while (!stopCondition()) {
			List<Individual> candidates = this.generateCandidates(best);
			this.pruneCandidates(candidates);
			Individual bestcan = this.getBestCandidate(candidates);
			if (bestcan.score()>best.score()){
				this.updateTabu(bestcan, best);
				this.prevbest = best;
				best = bestcan;
				this.pruneTabu();  
			}
		}
		return best;
	}
	
	private void pruneTabu(){
		while(tabulist.size()>tabusize && tabusize>0){
			tabulist.remove(0);
		}
	}
	
	private Individual getBestCandidate(List<Individual> candidates){
		if (candidates.size() > 0) {
			Individual bestcan = candidates.get(0);
			for (Individual can : candidates) {
				if (can.score() > bestcan.score())
					bestcan = can;
			}
			return bestcan;
		}else{
			throw new NullPointerException();
		}
	}

	private List<Individual> generateCandidates(Individual ind) {
		List<Individual> candidates = new ArrayList<Individual>();
		List<Move> moves = generateMoves(ind);
		for (Move move : moves) {
			Individual newOne = move.doTheMove(ind);
			if (newOne != null)
				candidates.add(move.doTheMove(ind));
		}
		return candidates;
	}

	private List<Move> generateMoves(Individual ind) {
		List<Move> moves = new ArrayList<Move>();
		List<Group> groups = ind.getGroups();
		// generate swap moves
		for (int i = 0; i < groups.size(); i++) {
			for (int j = 0; j < groups.size(); j++) {
				if (i == j)
					break;
				Group groupA = groups.get(i);
				for (int a = 0; a < groupA.size(); a++) {
					Group groupB = groups.get(j);
					for (int b = 0; b < groupB.size(); b++) {
						SwapMove swapmove = new SwapMove(a, b, i, j);
						if (!tabulist.contains(swapmove)) {
							moves.add(swapmove);
						}
					}
				}
			}
		}
		return moves;
	}

	private boolean stopCondition() {
		return (best.equals(prevbest));
	}
	
	private void updateTabu(Individual bestcan, Individual best){
		//TODO FOR TABU
	}
	
	private void pruneCandidates(List<Individual> candidates) {
		for (Individual ind : candidates) {
			//TODO FOR TABU
		}
	}
}
