package geneticAlgorithm.mutationOperator;

public interface MutationOperator<T> {
	public T mutate(T parent);
}
