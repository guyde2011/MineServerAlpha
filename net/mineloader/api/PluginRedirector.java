package net.mineloader.api;

import java.util.Arrays;
import java.util.List;

public abstract class PluginRedirector{
	
	private static String modMain;
	private static String tex;
	public static final String redirect(){
		return modMain;
	}
	


	public PluginRedirector(String main){
		modMain = main;

	}
	
}
