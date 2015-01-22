package net.mineloader.event;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;

public class WindowCloseEvent extends PlayerEvent{
	public static int id = 13;
	public final Container container;
	public WindowCloseEvent(EntityPlayerMP Player , Container cont) {
		super(Player);
		container = cont;
	}

}
