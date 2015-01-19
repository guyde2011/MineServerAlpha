package net.mineloader.util;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.HashMultimap;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.mineloader.attributes.Attribute;
import net.mineloader.attributes.AttributeList;

public class ItemUtil {
	
	public static ItemStack addAttributes(ItemStack stack , AttributeList map){
		List<Attribute> modifiers = new ArrayList<Attribute>();
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("AttributeModifiers", 9))
	    {
			NBTTagList list = stack.getTagCompound().getTagList("AttributeModifiers", 10);
			for (int i = 0; i < list.tagCount(); i++)
	        {
				NBTTagCompound temp = list.getCompoundTagAt(i);
	            AttributeModifier mod = SharedMonsterAttributes.readAttributeModifierFromNBT(temp);
	            modifiers.add(new Attribute(mod));
	        }
	    }
		AttributeList temp = map;
		temp.merge(new AttributeList(modifiers));
		NBTTagList attributes = SharedMonsterAttributes.writeBaseAttributeMapToNBT(temp.toMap());
		NBTTagCompound comp = new NBTTagCompound();
		if (stack.hasTagCompound()){
			comp = stack.getTagCompound();
		}
		comp.setTag("AttributeModifiers", attributes);
		ItemStack stack1 = stack;
		stack1.setTagCompound(comp);
		return stack1;
	}
	
	public static ItemStack setAttributes(ItemStack stack , AttributeList map){
		AttributeList temp = map;
		NBTTagList attributes = SharedMonsterAttributes.writeBaseAttributeMapToNBT(temp.toMap());
		NBTTagCompound comp = new NBTTagCompound();
		if (stack.hasTagCompound()){
			comp = stack.getTagCompound();
		}
		comp.setTag("AttributeModifiers", attributes);
		ItemStack stack1 = stack;
		stack1.setTagCompound(comp);
		return stack1;
	}
	
	public static ItemStack newStack(Item item , int amount , int meta , AttributeList map){
		return addAttributes(new ItemStack(item , amount , meta),map);
	}
	
	public static ItemStack newStack(Item item , int amount , AttributeList map){
		return addAttributes(new ItemStack(item , amount , 0),map);
	}
}
