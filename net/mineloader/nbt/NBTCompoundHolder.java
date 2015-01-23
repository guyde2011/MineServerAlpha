package net.mineloader.nbt;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class NBTCompoundHolder {
	
	public NBTCompoundHolder(){
		comp = new NBTTagCompound();
	}
	
	public NBTCompoundHolder(NBTTagCompound compound){
		comp = compound;
	}
	
	public NBTTagCompound getTag(){
		return comp;
	}
	
	public void setTag(NBTTagCompound compound){
		comp = compound;
	}
	
	private NBTTagCompound comp;
	
	public String getString(String entry){
		return comp.getString(entry);
	}
	
	public int getInt(String entry){
		return comp.getInteger(entry);
	}
	
	public int[] getIntArray(String entry){
		return comp.getIntArray(entry);
	}
	
	public byte[] getByteArray(String entry){
		return comp.getByteArray(entry);
	}
	
	public NBTBase getTag(String entry){
		return comp.getTag(entry);
	}
	
	public double getDouble(String entry){
		return comp.getDouble(entry);
	}
	
	public float getFloat(String entry){
		return comp.getFloat(entry);
	}
	
	public boolean getBoolean(String entry){
		return comp.getBoolean(entry);
	}
	
	public byte getByte(String entry){
		return comp.getByte(entry);
	}
	
	public void setString(String entry , String value){
		comp.setString(entry, value);
	}
	
	public void setInt(String entry , int value){
		comp.setInteger(entry, value);
	}
	
	public void setIntArray(String entry , int[] value){
		comp.setIntArray(entry, value);
	}
	
	public void setByteArray(String entry , byte[] value){
		comp.setByteArray(entry, value);
	}
	
	public void setTag(String entry , NBTBase value){
		comp.setTag(entry, value);
	}
	
	public void setDouble(String entry , double value){
		comp.setDouble(entry, value);
	}
	
	public void setFloat(String entry , float value){
		comp.setFloat(entry, value);
	}
	
	public void setBoolean(String entry , boolean value){
		comp.setBoolean(entry, value);
	}
	
	public void setByte(String entry , byte value){
		comp.setByte(entry, value);
	}

	public NBTTagCompound getCompoundTag(String key) {
		return comp.getCompoundTag(key);
	}
	
	
	

}
