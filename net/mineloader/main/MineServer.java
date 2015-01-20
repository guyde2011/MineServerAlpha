package net.mineloader.main;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.swing.filechooser.FileFilter;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import net.minecraft.block.material.Material;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.WorldServer;
import net.mineloader.api.*;
import net.mineloader.event.TickEvent;
import net.mineloader.perms.Permissions;
import net.mineloader.perms.PermissionsManager;
import net.mineloader.reader.PluginClassLoader;
import net.mineloader.reader.PluginLoader;
import net.mineloader.scoreboard.ScoreboardHandler;
import net.mineloader.scoreboard.ScoreboardList;
import net.mineloader.util.MineName;

public class MineServer {
	
	public static final int Version = 1;
	public static String MC_Path;
	public static String MC_Version;
	public static String mod_path;
	public static Map<String,MinecraftVersion> versions;
	public static List<Plugin> plugins;
	private static boolean Init = false;
	public static org.apache.logging.log4j.Logger logger = LogManager.getLogger();
	private static String getMinecraftPath(){
		return MC_Path;
	}
	
	private MineServer(){
		
	}
	
	public MineServer(PluginLoader pluginLoader){
		
	}
	
	public static EntityPlayerMP getPlayer(String name){
		for (WorldServer world : MinecraftServer.getServer().worldServers){
			if (world.getPlayerEntityByName(name)!=null){
				return (EntityPlayerMP) world.getPlayerEntityByName(name);
			}
		}
		return null;
	}
	
	public static void load(String Ver , String path) throws Exception{
		MC_Path = path;
		EventManager.handlers = new ArrayList<Class>();
		MC_Version = Ver;
		plugins = new ArrayList<Plugin>();
		if (MinecraftVersion.current.Equals(Ver)){
			Path currentRelativePath = Paths.get("");
			String s = currentRelativePath.toAbsolutePath().toString();
			MC_Path =  s;
			PermissionsManager.init();
		    mod_path = MC_Path + "\\plugins";
			plugins = PluginLoader.loadAllPlugins();
		}
		for (Plugin p : plugins){
			p.pre_init();
		}
		//EventManager.SubscribeHandler(TestingEventHandler.class);
		EventManager.SubscribeHandler(MSEventHandler.class);

	}
	
	public static void run(){

		
		for (Plugin p : plugins){
			p.init();
		}
		
		for (Plugin p : plugins){
			p.post_init();
		}
		
		for (Plugin p : plugins){
			p.register();
		}
		logger.info("Succesfully loaded "+plugins.size()+" plugins");
		Init  = true;
		
	}
	
	public static void queue(){
		
	}
	

	
	public static void setTime(EntityPlayerMP player , long Time){
		time.put(player.getName() , new Time(Time));
	}
	
	public static Map<String , 	Time> getTimes(){
		return time;
	}
	
	static Map<String , Time> time = new HashMap<String , Time>();
	
	public static boolean isPlayerOnline(String playerName){
		return Arrays.asList(MinecraftServer.getServer().getAllUsernames()).contains(playerName);
	}
	

	
	public static void queue_tick(){
		if (Init){
			EventManager.fireEvent(new TickEvent());
		}

	}
	private static MineServer loader = new MineServer();
	
	private class ReloadPluginsCommand implements ICommand{

		@Override
		public int compareTo(Object arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String getCommandName() {
			// TODO Auto-generated method stub
			return "reload";
		}

		@Override
		public String getCommandUsage(ICommandSender sender) {
			// TODO Auto-generated method stub
			return "/re OR /reload";
		}

		@Override
		public List getCommandAliases() {
			// TODO Auto-generated method stub
			return Arrays.asList("re");
		}

		@Override
		public void processCommand(ICommandSender sender, String[] args)
				throws CommandException {
			// TODO Auto-generated method stub
			boolean success = true;
			try {
				MineServer.load(MC_Version, MC_Path);
				MineServer.run();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				success = false;
				e.printStackTrace();
			}
			if (success){
				((EntityPlayerMP)sender.getCommandSenderEntity()).addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "Succesfully reloaded " + plugins.size() + " plugins to the MineLoader server"));
			} else {
				((EntityPlayerMP)sender.getCommandSenderEntity()).addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Could not reload all plugins to the MineLoader server"));
			}
		}

		@Override
		public boolean canCommandSenderUseCommand(ICommandSender sender) {
			// TODO Auto-generated method stub
			return sender.canCommandSenderUseCommand(4, "reload");
		}

		@Override
		public List addTabCompletionOptions(ICommandSender sender,
				String[] args, BlockPos pos) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isUsernameIndex(String[] args, int index) {
			// TODO Auto-generated method stub
			return false;
		}
		
	}
	
	public static List<ICommand> registerCommands(){
		List<ICommand> l = MineRegistry.getAllCommands(loader);
		l.add(loader.new ReloadPluginsCommand());
		return l;
	}

	public static void autosave() {
		for (Plugin p : plugins){
			p.autosave();
		}
	}
}
