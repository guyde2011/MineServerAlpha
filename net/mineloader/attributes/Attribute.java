package net.mineloader.attributes;

import java.util.UUID;
import net.minecraft.entity.ai.attributes.AttributeModifier;

public class Attribute
{
  private final Operation operation;
  private final double value;
  private final String name;
  private final UUID id;
  public static String DAMAGE = "generic.attackDamage";
  public static String SPEED = "generic.movementSpeed";
  public static String KNOCKBACK_RESISTANCE = "generic.knockbackResistance";
  public static String FOLLOW_RANGE = "generic.followRange";
  public static String MAX_HEALTH = "generic.maxHealth";
  
  public Attribute(Operation op, double amount, String att, UUID uuid)
  {
    this.operation = op;
    this.value = amount;
    this.name = att;
    this.id = uuid;
  }
  
  public Attribute(AttributeModifier mod)
  {
    this.operation = Operation.Parse(mod.getOperation());
    this.value = mod.getAmount();
    this.name = mod.getName();
    this.id = mod.getID();
  }
  
  public AttributeModifier toModifier()
  {
    return new AttributeModifier(this.id, this.name, this.value, this.operation.getOperation());
  }
  
  public AttributeModifier newModifier()
  {
    return new AttributeModifier(this.name, this.value, this.operation.getOperation());
  }
  
  public int hashCode()
  {
    return this.name.hashCode();
  }
}