package net.mineloader.event;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.IChatComponent;

public class ChatMessageEvent extends PlayerEvent{
	public static int id = 2;
	public final String message;
	public ChatMessageEvent(String msg , EntityPlayerMP player){
		super(player);
		message = msg;
	}

}
