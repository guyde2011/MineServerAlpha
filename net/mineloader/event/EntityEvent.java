package net.mineloader.event;

import net.minecraft.entity.Entity;

public class EntityEvent extends Event {
	public final Entity entity;
	public EntityEvent(Entity ent){
		entity = ent;
	}
}
