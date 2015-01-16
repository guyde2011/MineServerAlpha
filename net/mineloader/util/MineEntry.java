package net.mineloader.util;

public class MineEntry<T> {
	private MineName name;
	private T value;
	
	public MineEntry(MineName entry , T val){
		name = entry;
		value = val;
	}
	
	public String getName(){
		return "" + name;
	}
	
	public T getValue(){
		return value;
	}
	
	public int hashCode(){
		return name.toString().hashCode();
	}
}
