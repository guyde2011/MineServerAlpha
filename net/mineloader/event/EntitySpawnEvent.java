package net.mineloader.event;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;

public class EntitySpawnEvent extends EntityEvent {
	public static int id = 10;
	public EntitySpawnEvent(Entity ent){
		super(ent);

	}
	
	
}
