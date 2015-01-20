package net.mineloader.reader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;









import net.mineloader.api.Plugin;
import net.mineloader.main.MineServer;

public class PluginClassLoader {
	
	private String path;
	private File url;
	private URLClassLoader loader;
	
	public PluginClassLoader(File f) {
		try {
			loader = URLClassLoader.newInstance(new URL[]{f.toURI().toURL()} , getClass().getClassLoader());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		url = f;
		path = url.getPath();
	}
	
	public PluginClassLoader(File[] f) {
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

	public Plugin LoadPlugin(){
		for (String f : getFolderClasses(url,url)){
			Class c;
			try {
				c = loader.loadClass(f.substring(0 , f.length()-6));
				if (c != Plugin.class && Plugin.class.isAssignableFrom(c)){
					return (Plugin) c.getConstructor(String.class).newInstance(url.getPath() + "/" + f.replaceAll(".","/") + ".class");
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private List<String> getFolderClasses(File f , File mainDir){
		List<String> files = new ArrayList<String>();
		for(File file : f.listFiles()){
			if (file.isDirectory()){
				files.addAll(getFolderClasses(file , mainDir));
			} else if (file.getPath().endsWith(".class")){
				files.add(mainDir.toURI().relativize(file.toURI()).getPath().replaceAll("/",".").replaceAll("\\\\", "."));
			}
		}
		return files;
	}
	
	private List<String> getJarClasses(File mainDir){
		List<String> files = new ArrayList<String>();
		JarFile jarFile = null;
		try {
			jarFile = new JarFile(url);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Enumeration<JarEntry> e = jarFile.entries();
		try {
			URL[] urls = { new URL("jar:file:" +url.getPath()+"!/") };
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (e.hasMoreElements()) {
	        JarEntry je = e.nextElement();
	        if (!je.isDirectory()){
	        	if (je.getName().endsWith(".class")){
	        	    String className = je.getName().substring(0,je.getName().length()-6);
	        	    className = className.replace('/', '.');
	        	    files.add(className);
	        	}
	        }
		}
		return files;
	}

	public Plugin LoadJarPlugin() {
		for (String f : getJarClasses(url)){
			Class c;
			try {
				c = loader.loadClass(f.substring(0 , f.length()-6));
				if (c != Plugin.class && Plugin.class.isAssignableFrom(c)){
					return (Plugin) c.getConstructor(String.class).newInstance(url.getPath() + "/" + f.replaceAll(".","/") + ".class");
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}


}
