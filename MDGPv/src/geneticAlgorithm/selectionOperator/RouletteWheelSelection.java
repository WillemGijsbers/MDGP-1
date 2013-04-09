package geneticAlgorithm.selectionOperator;

import geneticAlgorithm.FitnessFunction.FitnessFunction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class RouletteWheelSelection<T,FitnessFunctionType extends FitnessFunction<T>> implements SelectionOperator<T> {
	private FitnessFunctionType fitnessFunction;
	public RouletteWheelSelection(FitnessFunctionType fitnessFunction){
		this.fitnessFunction = fitnessFunction;
	}
	public List<T> select(Collection<T> population,int N){
		List<T> selected = new ArrayList<T>();
		HashMap<T,Double> individualFitnessMap = createFitnessMap(population,fitnessFunction);
		while(selected.size() != N)
		{
			selected.add(SelectOne(individualFitnessMap));
		}
		return selected;
	}
	
	private HashMap<T, Double> createFitnessMap(Collection<T> population,
			FitnessFunctionType fitnessFunction) {
		HashMap<T,Double> result = new HashMap<T, Double>();
		double sum = 0;
		for(T instance : population){
			double fitness = fitnessFunction.fitness(instance);
			result.put(instance,fitness);
			sum += fitness;
		}
		for(T instance : result.keySet()){
			result.put(instance, result.get(instance)/sum);
		}
		return result;
	}

	private T SelectOne(HashMap<T,Double> population) {
		double rand  = Math.random();
		for (T individual : population.keySet()){
			rand -= population.get(individual);
			if (rand <= 0)
				return individual;
		}
		throw new IllegalStateException();
	}
}
