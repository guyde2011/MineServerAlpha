package net.mineloader.util;

import net.minecraft.util.EnumChatFormatting;

public class MineName {
	private EnumChatFormatting color;
	private String name;
	private boolean dis;
	
	public MineName(String col , String Name){
		color = EnumChatFormatting.valueOf(col.toUpperCase());
		name = Name;
		dis =  false;
	}
	
	static MineName forEntry(String s){
		MineName name = new MineName("WHITE" , s);
		name.dis = true;
		return name;
	}
	
	public String toString(){
		if (dis){
			return name;
		}
		return color + name;
	}
}
