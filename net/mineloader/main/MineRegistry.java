package net.mineloader.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.command.ICommand;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.mineloader.api.Mod;

public class MineRegistry {
	
	private static List<ICommand> commands = new ArrayList<ICommand>();
	public static void addRecipe(ItemStack result , Object... objs){
		CraftingManager.getInstance().addRecipe(result, objs);
	}
	
	public static void addRecipe(IRecipe rec){
		CraftingManager.getInstance().addCustomRecipe(rec);
	}
	
	public static void addShapelessRecipe(ItemStack result , Object... objs){
		CraftingManager.getInstance().addShapelessRecipe(result, objs);
	}
	
	public static void addCommand(ICommand cmd){
			commands.add(cmd);
	}
	
	public static List<ICommand> getAllCommands(MineLoader l){
		if (l!=null){
			return commands;
		}
		return new ArrayList<ICommand>();
	}
}
