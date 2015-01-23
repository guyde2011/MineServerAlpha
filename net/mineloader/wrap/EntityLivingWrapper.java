package net.mineloader.wrap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.mineloader.attributes.AttributeList;
import net.mineloader.attributes.EntityAttributeList;

public class EntityLivingWrapper<T extends EntityLivingBase> extends EntityWrapper<T> {

	public EntityLivingWrapper(T ent) {
		super(ent);

	}
	
	public float getHealth(){
		return owner.getHealth();
	}
	
	public void setHealth(float amount){
		owner.setHealth(amount);
	}
	
	public void damage(float amount , DamageSource src){
		owner.attackEntityFrom(src, amount);
	}
	
	public void damage(float amount){
		damage(amount , DamageSource.generic);
	}
	
	public void kill(){
		owner.setDead();
	}
	
	public EntityAttributeList getAttributes(){
		return new EntityAttributeList(owner.getAttributeMap());
	}
	
	public void setAttributes(EntityAttributeList list){
		NBTTagCompound comp = new NBTTagCompound();
		owner.writeEntityToNBT(comp);
		comp.setTag("Attributes", SharedMonsterAttributes.writeBaseAttributeMapToNBT(owner.getAttributeMap()));
		SharedMonsterAttributes.func_151475_a(owner.getAttributeMap(), comp.getTagList("Attributes", 10));
	}
	
	public List<PotionEffect> getPotionEffects(){
		return new ArrayList(owner.getActivePotionEffects());
	}
	
	public void addPotionEffect(PotionEffect effect){
		owner.addPotionEffect(effect);
	}
	
	public void removePotionEffect(PotionEffect effect){
		owner.removePotionEffect(effect.getPotionID());
	}
	
	public void removePotionEffect(int id){
		owner.removePotionEffect(id);
	}
	

	

	
	

}
