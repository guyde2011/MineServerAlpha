package net.mineloader.api;

public enum ModDir {
	ZIP(0),
	RAR(1),
	DIR(2),
	JAR(3);
	
	private int type;
	ModDir(int id){
		type = id;
	}
	
	public int getId(){
		return type;
	}
	
}
