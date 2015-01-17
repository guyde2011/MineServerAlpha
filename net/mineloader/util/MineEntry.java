package net.mineloader.util;

import net.minecraft.nbt.NBTTagCompound;

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
	
	public static MineEntry<Integer> IntEntry(NBTTagCompound comp){
		MineEntry<Integer> ret = new MineEntry<Integer>(new MineName("WHITE",""), comp.getInteger("value"));
		ret.name = MineName.forEntry(comp.getString("name"));
		return ret;
	}
	
	public static MineEntry<String> StringEntry(NBTTagCompound comp){
		MineEntry<String> ret = new MineEntry<String>(new MineName("WHITE",""), comp.getString("value"));
		ret.name = MineName.forEntry(comp.getString("name"));
		return ret;
	}
}
