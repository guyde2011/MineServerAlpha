package net.mineloader.wrap.entity;

import net.minecraft.entity.player.EntityPlayerMP;
import net.mineloader.wrap.EntityLivingWrapper;
import net.mineloader.wrap.EntityWrapper;

public class PlayerWrapper extends EntityLivingWrapper<EntityPlayerMP> {

	public PlayerWrapper(EntityPlayerMP ent) {
		super(ent);
	}

}
