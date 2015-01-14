package net.mineloader.api;

import java.util.HashMap;
import java.util.Map;


import net.mineloader.main.MineLoader;

public enum MinecraftVersion {
	
	MC_1_7_2(-2 , "1.7.2"),
	MC_1_7_4(-2 , "1.7.4"),
	MC_1_7_10(-1,"1.7.10"),
	MC_1_8_0("1.8",0),
	MC_1_8_1(0 , "1.8.1");
	
	
	
	
	private int ver_id;
	private String ver_name;

	public static MinecraftVersion current;
	
	MinecraftVersion(int ver , String name){
		if (MineLoader.versions==null){
			MineLoader.versions = new HashMap<String,MinecraftVersion>();
		}
		ver_name = name;
		ver_id = ver;
		this.put();
	}
	
	MinecraftVersion(String name , int ver){
		ver_name = name;
		ver_id = ver;
		this.put();
		this.setCurrent();
	}
	
	private void put(){
		MineLoader.versions.put(ver_name, this);
	}
	
	private void setCurrent(){
		current = this;
	}
	
	public static MinecraftVersion get(String str){
		return MineLoader.versions.get(str);
	}
	
	public boolean Equals(Object obj){
		if (obj instanceof String){
			return equals(get((String)obj));
		}
		if (obj instanceof MinecraftVersion){
			return ((MinecraftVersion)obj).ver_id==ver_id;
		}
		return false;
	}

}
