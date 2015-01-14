package main;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.mineloader.api.Mod;
import net.mineloader.api.ModInfo;
import net.mineloader.main.MineLoader;
import net.mineloader.main.MineRegistry;

public class mo extends Mod{

	public mo(String path){
		super(path);
		LocalizedName = "MineLoader Test Plugin";
		UnlocalizedName = "ml_test";
		Author = "guyde2011";
		version = "1.0.0";
		Test hi = new Test();
		Logger.getAnonymousLogger().log(Level.INFO, ": " + Boolean.toString((hi!=null)));
		mod =this;
	}
	
	
	public static mo mod; 
	public static Block b;
	@Override
	public void init() {
		// TODO Auto-generated method stub
	}

	@Override
	public void post_init() {
		// TODO Auto-generated method stub
		MineLoader.SubscribeHandler(MyEventHandler.class);

	}

	@Override
	public void pre_init() {
		// TODO Auto-generated method stub
		MineRegistry.addCommand(new BlahCommand());
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void register() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Mod getInstance() {
		// TODO Auto-generated method stub
		return mod;
	}
	
	
}
