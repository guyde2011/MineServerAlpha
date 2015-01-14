package net.mineloader.event;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class ItemCursorClickEvent extends PlayerEvent{
	public static int id=5;
	public ItemStack stack;
	public Container inv;
	public int slot;
	public int clickType;
	public ItemCursorClickEvent(EntityPlayerMP player , Container inventory , int Slot , ItemStack Stack, int click){
		super(player);
		stack = Stack;
		inv = inventory;
		slot = Slot;
		clickType = click;
	}

}
