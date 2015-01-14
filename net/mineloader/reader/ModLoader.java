package net.mineloader.reader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.mineloader.api.Mod;
import net.mineloader.api.ModDir;
import net.mineloader.main.MineLoader;

public class ModLoader {
	private ModLoader(){
		
	}
	
	public static List<Mod> LoadAllDirs(){
		List<Mod> mods = new ArrayList<Mod>();
		for (File file : new File(MineLoader.mod_path).listFiles()){
			if (file.isDirectory()){
				File[] files = new File[]{new File(MineLoader.mod_path),file};
				ModClassLoader load = new ModClassLoader(files);
				try {
					Mod mod = load.LoadMod();
					mod.setDir(new MineLoader(new ModLoader()), ModDir.DIR);
					mods.add(mod);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return mods;
	}
	
	public static List<Mod> LoadAllJars(){
		List<Mod> mods = new ArrayList<Mod>();
		for (File file : new File(MineLoader.mod_path).listFiles()){
			if (file.getName().endsWith(".jar")){
				File[] files = new File[]{new File(MineLoader.mod_path),file};
				ModClassLoader load = new ModClassLoader(files);
				try {
					Mod mod = load.LoadMod();
					mod.setDir(new MineLoader(new ModLoader()), ModDir.JAR);
					mods.add(mod);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return mods;
	}
	
	public static List<Mod> loadAllMods(){
		List<Mod> ret = LoadAllJars();
		ret.addAll(LoadAllDirs());
		return ret;
	}
	

}
