package net.mineloader.main;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.mineloader.api.*;
import net.mineloader.event.BlockMinedEvent;
import net.mineloader.event.ChatMessageEvent;
import net.mineloader.event.EntityDamagedEvent;
import net.mineloader.event.Event;
import net.mineloader.event.EventHandler;
import net.mineloader.event.ItemClickEvent;
import net.mineloader.event.ItemCursorClickEvent;
import net.mineloader.event.PlayerJoinEvent;
import net.mineloader.event.TickEvent;
import net.mineloader.perms.Permissions;
import net.mineloader.perms.PermissionsManager;
import net.mineloader.reader.ModClassLoader;
import net.mineloader.reader.ModLoader;
import net.mineloader.util.Title;

public class MineLoader {
	
	public static final int Version = 1;
	public static String MC_Path;
	public static String MC_Version;
	public static String mod_path;
	public static Map<String,MinecraftVersion> versions;
	public static List<Mod> mods;
	private static List<Class> handlers;
	private static boolean Init = false;
	private static org.apache.logging.log4j.Logger logger = LogManager.getLogger();
	private static String getMinecraftPath(){
		return MC_Path;
	}
	
	private MineLoader(){
		
	}
	
	public MineLoader(ModLoader loader){
		
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
		handlers = new ArrayList<Class>();
		MC_Version = Ver;
		mods = new ArrayList<Mod>();
		if (MinecraftVersion.current.Equals(Ver)){
			Path currentRelativePath = Paths.get("");
			String s = currentRelativePath.toAbsolutePath().toString();
			MC_Path =  s;
			PermissionsManager.init();
		    mod_path = MC_Path + "\\plugins";
			mods = ModLoader.loadAllMods();
		}
		for (Mod mod : mods){
			mod.pre_init();
		}

	}
	
	public static void run(){

		
		for (Mod mod : mods){
			mod.init();
		}
		
		for (Mod mod : mods){
			mod.post_init();
		}
		
		for (Mod mod : mods){
			mod.register();
		}
		logger.info("Succesfully loaded "+mods.size()+" plugins");
		Init  = true;
		
	}
	
	public static void queue(){
		
	}
	

	
	public static void player_joined(EntityPlayerMP player){
		time.put(player.getName(), Time.synced());
		runEvent(new PlayerJoinEvent(player));
		Title.timedTitle(EnumChatFormatting.GREEN + "Welcome", 1, 20, 1).sendTo(player);
		Title.subtitle(EnumChatFormatting.YELLOW + "to the MSS Platform").sendTo(player);;
	}
	
	public static void setTime(EntityPlayerMP player , long Time){
		time.put(player.getName() , new Time(Time));
	}
	
	public static Map<String , 	Time> getTimes(){
		return time;
	}
	
	private static void runEvent(Event ev){
		for (Class cur : handlers){
			for (Method method : cur.getDeclaredMethods()) {
				if (method.isAnnotationPresent(EventHandler.class)){
					try {
						if (ev.getClass().equals(method.getParameterTypes()[0])){
							method.invoke(null, ev);
						}
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println(e.getCause());
					}
				}
			}
		}
	}
	
	private static Map<String , Time> time = new HashMap<String , Time>();
	
	public static boolean isPlayerOnline(String playerName){
		return Arrays.asList(MinecraftServer.getServer().getAllUsernames()).contains(playerName);
	}
	

	
	public static void SubscribeHandler(Class cls){
		handlers.add(cls);
	}
	
	public static void queue_tick(){
		if (Init){
			runEvent(new TickEvent());
		}

	}
	private static MineLoader loader = new MineLoader();
	
	private class ReloadPluginsCommand implements ICommand{

		@Override
		public int compareTo(Object arg0) {
			// TODO Auto-generated method stub
			return 11110;
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
				MineLoader.load(MC_Version, MC_Path);
				MineLoader.run();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				success = false;
				e.printStackTrace();
			}
			if (success){
				((EntityPlayerMP)sender.getCommandSenderEntity()).addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "Succesfully reloaded " + mods.size() + " plugins to the MineLoader server"));
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

	public static boolean chat_event(String msg, EntityPlayerMP player) {
		ChatMessageEvent ev = new ChatMessageEvent(msg , player);
		runEvent(ev);
		return !ev.isCanceled();
	}

	public static boolean guiClick(C0EPacketClickWindow packet, EntityPlayerMP player) {
		int slot = packet.getSlotId();
		int click = packet.getMode();
		Container inv = player.openContainer;
		ItemCursorClickEvent event = new ItemCursorClickEvent(player, inv, slot, packet.getClickedItem(),click);
		runEvent(event);
		return !event.isCanceled();
	}

	public static boolean itemClick(ItemStack stack, World world,EntityPlayer player) {
		ItemClickEvent event = new ItemClickEvent(stack, world, player);
		runEvent(event);
		return !event.isCanceled();
	}

	public static boolean entityDamaged(Entity entity, DamageSource source,
			float amount) {
		EntityDamagedEvent ev = new EntityDamagedEvent(entity , source , amount);
		runEvent(ev);
		return !ev.isCanceled();
	}

	public static boolean blockMined(Block block, BlockPos pos, EntityPlayerMP player) {
		BlockMinedEvent ev = new BlockMinedEvent(player , pos , block);
		runEvent(ev);
		return !ev.isCanceled();
	}
}
