package MDGP;

import java.util.ArrayList;
import java.util.List;

public final class Instance {

	private static List<Instance> instances = new ArrayList<Instance>();

	public final int number;

	public Instance() {
		this.number = Instance.instances.size();
	}
	
	public Instance(int nr){
		this.number = nr;
	}
	
	@Override
	public Instance clone(){
		return new Instance(this.number);
	}
	
	@Override
	public String toString(){
		return "" + number;
	}

	@Override
	public boolean equals(Object target){
		if(!(target instanceof Instance))
			return false;
		return this.number == ((Instance)target).number;
	}
}