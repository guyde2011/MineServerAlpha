package net.mineloader.main;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.mineloader.event.BlockClickedEvent;
import net.mineloader.event.BlockMinedEvent;
import net.mineloader.event.ChatMessageEvent;
import net.mineloader.event.EntityClickEvent;
import net.mineloader.event.EntityDamagedEvent;
import net.mineloader.event.EntityKilledEvent;
import net.mineloader.event.EntitySpawnEvent;
import net.mineloader.event.Event;
import net.mineloader.event.EventHandler;
import net.mineloader.event.ItemClickEvent;
import net.mineloader.event.ItemCursorClickEvent;
import net.mineloader.event.PlayerJoinEvent;
import net.mineloader.event.WindowCloseEvent;
import net.mineloader.event.WindowOpenEvent;
import net.mineloader.scoreboard.ScoreboardHandler;
import net.mineloader.scoreboard.ScoreboardList;
import net.mineloader.scoreboard.ScoreboardOrderedList;
import net.mineloader.util.MineName;
import net.mineloader.util.Title;

public class EventManager {

	public static void player_joined(EntityPlayerMP player){
		MineServer.time.put(player.getName(), Time.synced());
		fireEvent(new PlayerJoinEvent(player));
	}
	
	public static void fireEvent(Event ev){
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

	public static void SubscribeHandler(Class cls){
		handlers.add(cls);
	}

	public static boolean chat_event(String msg, EntityPlayerMP player) {
		ChatMessageEvent ev = new ChatMessageEvent(msg , player);
		fireEvent(ev);
		return !ev.isCanceled();
	}

	public static boolean guiClick(C0EPacketClickWindow packet, EntityPlayerMP player) {
		int slot = packet.getSlotId();
		int click = packet.getMode();
		Container inv = player.openContainer;
		ItemCursorClickEvent event = new ItemCursorClickEvent(player, inv, slot, packet.getClickedItem(),click);
		fireEvent(event);
		return !event.isCanceled();
	}

	public static boolean itemClick(ItemStack stack, World world,EntityPlayer player) {
		ItemClickEvent event = new ItemClickEvent(stack, world, player);
		fireEvent(event);
		return !event.isCanceled();
	}

	public static boolean entityDamaged(Entity entity, DamageSource source,
			float amount) {
		EntityDamagedEvent ev = new EntityDamagedEvent(entity , source , amount);
		fireEvent(ev);
		return !ev.isCanceled();
	}

	public static boolean blockMined(Block block, BlockPos pos, EntityPlayerMP player) {
		BlockMinedEvent ev = new BlockMinedEvent(player , pos , block);
		fireEvent(ev);
		return !ev.isCanceled();
	}

	public static boolean clickEvent(EntityPlayer playerIn , Entity ent) {
		EntityClickEvent event = new EntityClickEvent(playerIn, ent);
		fireEvent(event);
		return !event.isCanceled();
	}

	public static boolean blockClicked(Block block, BlockPos pos, EntityPlayerMP player) {
		BlockClickedEvent ev = new BlockClickedEvent(player , pos , block);
		fireEvent(ev);
		return !ev.isCanceled();
	}

	static List<Class> handlers;

	public static boolean entitySpawned(Entity ent) {
		EntitySpawnEvent event = new EntitySpawnEvent(ent);
		fireEvent(event);
		return !event.isCanceled();
	}

	public static void entityKilled(Entity ent) {
		fireEvent(new EntityKilledEvent(ent));
		
	}

	public static void openWindow(EntityPlayerMP player) {
		fireEvent(new WindowOpenEvent(player , player.openContainer));
	}
	
	public static void closeWindow(EntityPlayerMP player) {
		fireEvent(new WindowCloseEvent(player , player.openContainer));
	}

}
