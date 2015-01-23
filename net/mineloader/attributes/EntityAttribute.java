package net.mineloader.attributes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;

public class EntityAttribute
{
  final IAttribute name;
  final List<EntityAttributeValue> values;
  final double baseValue;
  public static IAttribute DAMAGE = SharedMonsterAttributes.attackDamage;
  public static IAttribute SPEED = SharedMonsterAttributes.movementSpeed;
  public static IAttribute KNOCKBACK_RESISTANCE = SharedMonsterAttributes.knockbackResistance;
  public static IAttribute FOLLOW_RANGE = SharedMonsterAttributes.followRange;
  public static IAttribute MAX_HEALTH = SharedMonsterAttributes.maxHealth;
  
  public EntityAttribute(IAttribute att , double def)
  {
    this.name = att;
    this.values = new ArrayList<EntityAttributeValue>();
    this.baseValue = def;
  }
  
  public void addAttributeValue(EntityAttributeValue val){
	  values.add(val);
  }
  
  
  public int hashCode()
  {
    return this.name.getAttributeUnlocalizedName().hashCode();
  }
}