package geneticAlgorithm.StopCriterion;

import java.util.Collection;

public interface StopCriterion<T> {
	public boolean stop(Collection<T> population);
}
