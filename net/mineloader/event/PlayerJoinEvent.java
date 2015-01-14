package net.mineloader.event;

import net.minecraft.entity.player.EntityPlayerMP;

public class PlayerJoinEvent extends PlayerEvent{
	public static int id = 1;
	
	public PlayerJoinEvent(EntityPlayerMP player){
		super(player);
	}
}
