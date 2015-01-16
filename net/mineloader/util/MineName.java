package net.mineloader.util;

import net.minecraft.util.EnumChatFormatting;

public class MineName {
	private EnumChatFormatting color;
	private String name;
	
	public MineName(String col , String Name){
		color = EnumChatFormatting.valueOf(col.toUpperCase());
		name = Name;
	}
	
	public String toString(){
		return color + name;
	}
}
