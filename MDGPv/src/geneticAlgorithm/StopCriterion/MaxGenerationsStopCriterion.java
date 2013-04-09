/**
 * 
 */
package geneticAlgorithm.StopCriterion;

import java.util.Collection;

/**
 * @author Willem
 *
 */
public class MaxGenerationsStopCriterion<T> implements StopCriterion<T> {

	private final int maxGenerations;
	private int currentGenerations;
	public MaxGenerationsStopCriterion(int maxGenerations){
		this.maxGenerations = maxGenerations;
		this.currentGenerations = 0;
	}
	@Override
	public boolean stop(Collection<T> population) {
		currentGenerations++;
		return currentGenerations >= maxGenerations;
	}

}
