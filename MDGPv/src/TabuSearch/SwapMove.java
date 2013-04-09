package TabuSearch;

import java.util.List;

import MDGP.Group;
import MDGP.Individual;
import MDGP.Instance;


class SwapMove extends Move {
	protected int toGroupNr;

	public SwapMove(int fromGroup, int toGroup, int fromGroupNr,
			int toGroupNr) {
		super(fromGroup, toGroup, fromGroupNr);
		this.toGroupNr = toGroupNr;
	}

	public Individual doTheMove(Individual ind) {
		ind = ind.clone();
		List<Group> groups = ind.getGroups();
		if ((groups.size() < fromGroup) || (groups.size() < toGroup)) {
			throw new IllegalArgumentException();
		}
		Group from = groups.get(fromGroup);
		Group to = groups.get(toGroup);
		if (from.size() < fromGroupNr || to.size() < this.toGroupNr) {
			throw new IllegalArgumentException();
		}
		Instance a = from.remove(this.fromGroupNr);
		Instance b = to.remove(this.toGroupNr);
		from.add(b);
		to.add(a);
		if (ind.check())
			return ind;
		else
			return null;
	}
}