package net.mineloader.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityClickEvent extends PlayerEvent{
	public static int id=8;
	public Entity clickedEntity;

	public EntityClickEvent(EntityPlayer Player , Entity ent) {
		super((EntityPlayerMP)Player);
		clickedEntity = ent;
	}
}
