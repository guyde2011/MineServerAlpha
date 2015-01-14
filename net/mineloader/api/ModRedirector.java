package net.mineloader.api;

import java.util.Arrays;
import java.util.List;

public abstract class ModRedirector{
	
	private static String modMain;
	private static String tex;
	public static final String redirect(){
		return modMain;
	}
	


	public ModRedirector(String main){
		modMain = main;

	}
	
}
