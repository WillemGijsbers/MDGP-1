package geneticAlgorithm.crossoverOperator;

import java.util.List;

public interface CrossoverOperator<T> {
	public List<T> crossover(T first, T second);
}
