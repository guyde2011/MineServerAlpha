package net.mineloader.wrap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.world.World;
import net.mineloader.util.Angle3D;
import net.mineloader.util.MinePos;
import net.mineloader.util.WrapperCastException;

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
	
	public World getWorld(){
		return owner.getEntityWorld();
	}
	
	public EntityWrapper<Entity> getRidingEntity(){
		return Generic(owner.ridingEntity);
	}
	
	public EntityWrapper<Entity> getRiddenByEntity(){
		return Generic(owner.riddenByEntity);
	}
	
	public boolean hasRidingEntity(){
		return owner.ridingEntity!=null;
	}
	
	public boolean hasRiddenByEntity(){
		return owner.riddenByEntity!=null;
	}
	
	public boolean isLiving(){
		return owner instanceof EntityLiving;
	}
	
	private boolean isChildClass(Class parent , Class child){
		return !parent.equals(child) && parent.isAssignableFrom(child);
	}
	
	private boolean isInstanceOf(Class parent , Class child){
		return parent.isAssignableFrom(child);
	}
	
	public <F extends EntityLivingBase> EntityLivingWrapper<F> toLivingWrapper(Class<F> castTo){
		if (isChildClass(EntityLivingBase.class , castTo) && isInstanceOf(owner.getClass() , castTo)){
			return new EntityLivingWrapper<F>((F) owner);
		}
		throw new WrapperCastException("Could not cast EntityWrapper of type " + owner.getClass() + " to type " + castTo );
	}
	
	
	
	
	
	
	
	

	

	

}
