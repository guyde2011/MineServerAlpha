package net.mineloader.main;

import java.util.List;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S03PacketTimeUpdate;
import net.minecraft.network.play.server.S28PacketEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.mineloader.event.ChatMessageEvent;
import net.mineloader.event.Event;
import net.mineloader.event.EventHandler;
import net.mineloader.event.ItemCursorClickEvent;
import net.mineloader.event.PlayerJoinEvent;
import net.mineloader.event.TickEvent;
import net.mineloader.main.MineLoader;
import net.mineloader.network.CustomInventory;
import net.mineloader.network.PacketSender;
import net.mineloader.perms.PermissionsManager;

public class EventHandlerExample {
	
	@EventHandler
	public static void onJoin(PlayerJoinEvent ev){
		ev.player.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "Welcome " + EnumChatFormatting.RED + ev.player.getName() + EnumChatFormatting.YELLOW + " to the new MineServer Platform"));
	}
	
	@EventHandler
	public static void onChatMessage(ChatMessageEvent ev){	
		ev.Cancel();
		CustomInventory inv = new CustomInventory("tester-inv", 36);
		inv.openInventory(ev.player);
		inv.saveInventory("test");
		try{
			System.out.println(CustomInventory.readFromNBT("test").inv.getName());
		} catch(Exception e){
			e.printStackTrace();
		}
		String prefix = "";
		if (PermissionsManager.Instance.getPerm(ev.player.getName())!=null){
			prefix = "" + PermissionsManager.Instance.getPerm(ev.player.getName());
		}
		for (EntityPlayerMP player : (List<EntityPlayerMP>)MinecraftServer.getServer().getEntityWorld().playerEntities){
			String str = player.getName();
			player.addChatComponentMessage(new ChatComponentText(prefix + EnumChatFormatting.WHITE + "<" + ev.player.getName() + "> " + ev.message) );	
		}
	}
	
	

	
	
}
