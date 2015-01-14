package net.mineloader.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemClickEvent extends PlayerEvent{
	public static int id=4;
	public ItemStack stack;
	public World world;

	public ItemClickEvent(ItemStack stack2, World worldIn, EntityPlayer Player) {
		super((EntityPlayerMP)Player);
		stack = stack2;
		world = worldIn;
	}
}
