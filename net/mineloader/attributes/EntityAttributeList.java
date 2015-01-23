package net.mineloader.attributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.ai.attributes.ServersideAttributeMap;

public class EntityAttributeList{
	private List<EntityAttribute> list;
  
	public EntityAttributeList()
	{
		this.list = new ArrayList();
	}
  
	public EntityAttributeList(List<EntityAttribute> l)
	{
		this.list = l;
	}
  
	public EntityAttributeList(BaseAttributeMap atts){
		this();
		ServersideAttributeMap map = (ServersideAttributeMap) atts;
		for (IAttributeInstance cur : (Set<IAttributeInstance>)map.getAttributeInstanceSet()){
			EntityAttribute att = new EntityAttribute(cur.getAttribute(), cur.getBaseValue());
			for (int i = 0; i< 2 ;i++){ 
				for (AttributeModifier mod : new ArrayList<AttributeModifier>(cur.getModifiersByOperation(i))){
					att.addAttributeValue(new EntityAttributeValue(Operation.Parse(i),mod.getAmount() , mod.getID()));
				}
			}
			list.add(att);
		}
	}

	public void add(EntityAttribute att){
		if (this.list.contains(att)) {
			this.list.remove(this.list.indexOf(att));
		}
		this.list.add(att);
	}
  
	public net.minecraft.entity.ai.attributes.ServersideAttributeMap toMap()
	{
		net.minecraft.entity.ai.attributes.ServersideAttributeMap map = new net.minecraft.entity.ai.attributes.ServersideAttributeMap();
		for (EntityAttribute att : this.list) {
			IAttributeInstance inst = map.getAttributeInstance(att.name);
			for (EntityAttributeValue val : att.values){
				inst.applyModifier(new AttributeModifier(att.name.getAttributeUnlocalizedName(), val.getValue(), val.getOperation().getOperation()));
			}
			inst.setBaseValue(att.baseValue);
		}
		return map;
	}
  
	public void merge(EntityAttributeList l){
		EntityAttributeList temp = l;
		for (EntityAttribute att : this.list) {
			temp.add(att);
		}
		this.list = temp.list;
	}
}
