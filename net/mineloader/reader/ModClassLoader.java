package net.mineloader.reader;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;


import net.mineloader.api.Mod;
import net.mineloader.api.ModRedirector;
import net.mineloader.main.MineLoader;

public class ModClassLoader {
	
	private String path;
	private File url;
	private URLClassLoader loader;
	
	public ModClassLoader(File f) {
		try {
			loader = URLClassLoader.newInstance(new URL[]{f.toURI().toURL()} , getClass().getClassLoader());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		url = f;
		path = url.getPath();
	}
	
	public ModClassLoader(File[] f) {
		URL[] urls = new URL[f.length];
		int r = 0;
		for (File fi : f){
			r++;
			try {
				urls[r-1] = fi.toURI().toURL();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		loader = URLClassLoader.newInstance(urls);
		url = f[1];
		path = url.getPath();
	}
	
	public Mod LoadMod() throws Exception{
		Class<? extends ModRedirector> mod = LoadClass("main.redirect");
		return (Mod) New(mod.newInstance().redirect());
	}
	
	public Class LoadClass(String str) throws Exception{
		Class clazz = Class.forName(str, true, loader);
		// Avoid Class.newInstance, for it is evil.
		return clazz;
		
	}
	
	public Object New(String str) throws Exception{
		Class<? extends Mod> clazz = (Class<? extends Mod>) Class.forName(str, true, loader);
		return clazz.getConstructor(String.class).newInstance(path);
	}

}