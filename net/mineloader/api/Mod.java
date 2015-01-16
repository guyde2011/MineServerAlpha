package net.mineloader.api;

import net.mineloader.main.MineServer;

public abstract class Mod {
	
	public abstract void init();
	
	public abstract void post_init();
	
	public abstract void pre_init();
	
	public abstract void run();
	
	public abstract void register();
		
	public final ModInfo getInfo(){
		return new ModInfo(this , Author , version, Path);
	}
	
	public abstract Mod getInstance();
	
	public Mod(String path){
		Path = path;
	}
	public static String version;
	public static String Author;
	public static String Path;
	public static String LocalizedName;
	
	public static String UnlocalizedName;
	private ModDir dir;
	
	public final ModDir getDir(){
		return dir;
	}
	
	public final void setDir(MineServer loader , ModDir mdir){
		dir = mdir;
	}
	
	public int hashCode(){
		return UnlocalizedName.hashCode();
	}
	
}
