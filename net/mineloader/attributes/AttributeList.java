package net.mineloader.attributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.ai.attributes.ServersideAttributeMap;

public class AttributeList{
	private List<Attribute> list;
  
	public AttributeList()
	{
		this.list = new ArrayList();
	}
  
	public AttributeList(List<Attribute> l)
	{
		this.list = l;
	}
  


	public void add(Attribute att){
		if (this.list.contains(att)) {
			this.list.remove(this.list.indexOf(att));
		}
		this.list.add(att);
	}
  
	public net.minecraft.entity.ai.attributes.ServersideAttributeMap toMap()
	{
		net.minecraft.entity.ai.attributes.ServersideAttributeMap map = new net.minecraft.entity.ai.attributes.ServersideAttributeMap();
		for (Attribute att : this.list) {
			map.getAttributeInstanceByName(att.toModifier().getName()).applyModifier(att.newModifier());
		}
		return map;
	}
  
	public void merge(AttributeList l){
		AttributeList temp = l;
		for (Attribute att : this.list) {
			temp.add(att);
		}
		this.list = temp.list;
	}
}
