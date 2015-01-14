package net.mineloader.event;

import net.minecraft.entity.player.EntityPlayerMP;

public class PlayerEvent extends Event{
	public final EntityPlayerMP player;
	
	public PlayerEvent(EntityPlayerMP Player){
		player = Player;
	}
}
