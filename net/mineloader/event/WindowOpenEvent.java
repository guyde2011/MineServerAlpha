package net.mineloader.event;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;

public class WindowOpenEvent extends PlayerEvent{
	public static int id = 12;
	public final Container container;
	public WindowOpenEvent(EntityPlayerMP Player , Container cont) {
		super(Player);
		container = cont;
	}

}
