package geneticAlgorithm.selectionOperator;

import java.util.Collection;
import java.util.List;

public interface SelectionOperator<T> {
	public List<T> select(Collection<T> population,int N);
}
