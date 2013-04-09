package TabuSearch;
import java.util.List;

import MDGP.Group;
import MDGP.Individual;
import MDGP.Instance;

class Move implements Tabu{

	protected int fromGroup;
	protected int toGroup;
	protected int fromGroupNr;

	public Move(int fromGroup, int toGroup, int fromGroupnr) {
		this.fromGroup = fromGroup;
		this.toGroup = toGroup;
		this.fromGroupNr = fromGroupnr;
	}

	public Individual doTheMove(Individual ind) {
		ind = ind.clone();
		List<Group> groups = ind.getGroups();
		if ((groups.size() < this.fromGroup) || (groups.size() < this.toGroup)) {
			throw new IllegalArgumentException();
		}
		Group from = groups.get(this.fromGroup);
		Group to = groups.get(this.toGroup);
		if (from.size() < this.fromGroupNr)
			throw new IllegalArgumentException();
		Instance mover = from.remove(this.fromGroupNr);
		to.add(mover);
		if (ind.check())
			return ind;
		else
			return null;
	}
}