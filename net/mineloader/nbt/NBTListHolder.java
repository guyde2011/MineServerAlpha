package net.mineloader.nbt;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagList;

public class NBTListHolder {
	
	public NBTListHolder(){
		list = new NBTTagList();
	}
	
	public NBTListHolder(NBTTagList list1){
		list = list1;
	}
	
	public NBTTagList getTag(){
		return list;
	}
	
	public void setTag(NBTTagList list1){
		list = list1;
	}
	
	private NBTTagList list;
	
	public String getString(int i){
		return list.getStringTagAt(i);
	}
	
	public NBTBase getTag(int i){
		return list.get(i);
	}
	
	public NBTCompoundHolder getCompound(int i){
		return new NBTCompoundHolder(list.getCompoundTagAt(i));
	}
	
	public int[] getIntArray(int entry){
		return list.getIntArray(entry);
	}
	
	
	public double getDouble(int entry){
		return list.getDouble(entry);
	}
	
	public float getFloat(int entry){
		return list.getFloat(entry);
	}
	
	public void appendTag(NBTBase tag){
		list.appendTag(tag);
	}
	
	public void setTag(NBTBase tag , int index){
		list.set(index, tag);
	}
	
	public void removeTag(int i){
		list.removeTag(i);
	}

	
	

}
