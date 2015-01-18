package net.mineloader.api;

import net.mineloader.main.MineServer;

public abstract class Plugin {
	
	public abstract void init();
	
	public abstract void post_init();
	
	public abstract void pre_init();
	
	public abstract void run();
	
	public abstract void register();
		
	public final PluginInfo getInfo(){
		return new PluginInfo(this , Author , version, Path);
	}
	
	public abstract Plugin getInstance();
	
	public Plugin(String path){
		Path = path;
	}
	public static String version;
	public static String Author;
	public static String Path;
	public static String LocalizedName;
	
	public static String UnlocalizedName;
	private PluginDir dir;
	
	public final PluginDir getDir(){
		return dir;
	}
	
	public final void setDir(MineServer loader , PluginDir mdir){
		dir = mdir;
	}
	
	public int hashCode(){
		return UnlocalizedName.hashCode();
	}

	public abstract void autosave();
	
}
