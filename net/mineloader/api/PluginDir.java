package net.mineloader.api;

public enum PluginDir {
	ZIP(0),
	RAR(1),
	DIR(2),
	JAR(3);
	
	private int type;
	PluginDir(int id){
		type = id;
	}
	
	public int getId(){
		return type;
	}
	
}
