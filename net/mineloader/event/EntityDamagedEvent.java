package net.mineloader.event;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;

public class EntityDamagedEvent extends EntityEvent {
	public final DamageSource source;
	public static int id = 7;
	public final float damage;
	public EntityDamagedEvent(Entity ent , DamageSource src, float amount){
		super(ent);
		source = src;
		damage = amount;
	}
	
	
}
