package net.mineloader.reader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.mineloader.api.Plugin;
import net.mineloader.api.PluginDir;
import net.mineloader.main.MineServer;

public class PluginLoader {
	private PluginLoader(){
		
	}
	
	public static List<Plugin> LoadAllDirs(){
		List<Plugin> Plugins = new ArrayList<Plugin>();
		for (File file : new File(MineServer.mod_path).listFiles()){
			if (file.isDirectory()){
				File[] files = new File[]{new File(MineServer.mod_path),file};
				PluginClassLoader load = new PluginClassLoader(files);
				try {
					Plugin Plugin = load.LoadPlugin();
					Plugin.setDir(new MineServer(new PluginLoader()), PluginDir.DIR);
					Plugins.add(Plugin);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return Plugins;
	}
	
	public static List<Plugin> LoadAllJars(){
		List<Plugin> Plugins = new ArrayList<Plugin>();
		for (File file : new File(MineServer.mod_path).listFiles()){
			if (file.getName().endsWith(".jar")){
				File[] files = new File[]{new File(MineServer.mod_path),file};
				PluginClassLoader load = new PluginClassLoader(files);
				try {
					Plugin Plugin = load.LoadPlugin();
					Plugin.setDir(new MineServer(new PluginLoader()), PluginDir.JAR);
					Plugins.add(Plugin);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return Plugins;
	}
	
	public static List<Plugin> loadAllPlugins(){
		List<Plugin> ret = LoadAllJars();
		ret.addAll(LoadAllDirs());
		return ret;
	}
	

}
