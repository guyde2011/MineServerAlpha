package net.mineloader.event;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;

public class EntityKilledEvent extends EntityEvent{
	public static int id = 11;
	public EntityKilledEvent(Entity ent){
		super(ent);
	}
}
