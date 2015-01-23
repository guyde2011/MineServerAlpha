package net.mineloader.wrap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.mineloader.util.Angle3D;
import net.mineloader.util.MinePos;

public abstract class EntityWrapper<T extends Entity> {
	protected final T owner;
	
	
	public static final EntityWrapper<Entity> Generic(Entity ent){
		return new EntityWrapper<Entity>(ent){};
		
	}
	public EntityWrapper(T ent){
		owner = ent;
	}
	
	public String getName(){
		return owner.getName();
	}
	
	public T getEntity(){
		return owner;
	}
	
	public MinePos getPosition(){
		return new MinePos(owner);
	}
	
	public MinePos getMotion(){
		return new MinePos(owner.motionX,owner.motionY,owner.motionZ);
	}
	
	public void Teleport(MinePos pos){
		owner.setPosition(pos.X , pos.Y , pos.Z);
	}
	
	public void TeleportBy(MinePos off){
		MinePos pos = getPosition();
		pos.add(off);
		Teleport(pos);
	}
	
	public void TeleportTo(Entity ent){
		Teleport(Generic(ent).getPosition());
	}
	
	public void RotateTo(Angle3D angle){
		MinePos a = getPosition();
		owner.setPositionAndRotation(a.X, a.Y, a.Z, angle.getYaw(), angle.getPitch());
	}
	
	public void RotateTo(MinePos to){
		MinePos a = getPosition();
		MinePos p =  to.copy();
		p.substract(a);
		Angle3D angle = p.toAngle();
		owner.setPositionAndRotation(a.X, a.Y, a.Z, angle.getYaw(), angle.getPitch());
	}
	
	public void setVelocity(MinePos vel){
		owner.motionX = vel.X;
		owner.motionY = vel.Y;
		owner.motionZ = vel.Z;
	}
	
	
	
	
	

	

	

}
