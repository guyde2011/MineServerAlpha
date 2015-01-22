package net.mineloader.inv;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.server.S2DPacketOpenWindow;
import net.minecraft.tileentity.TileEntityChest;
import net.mineloader.main.EventManager;
import net.mineloader.main.MineServer;
import net.mineloader.reader.NBTSaver;

public class CustomChestInventory {
	private String displayName;
	private int size;
	public ChestInv inv;
	
	public CustomChestInventory(String dName ,int Size){
		size = Size;
		displayName = dName;
		inv = new ChestInv(Size , dName);
	}
	public void openInventory(EntityPlayerMP player){
		 player.playerNetServerHandler.sendPacket(new S2DPacketOpenWindow(player.getCurrentWindowId(), "minecraft:container", inv.getDisplayName(), size));
         player.openContainer = new ContainerChest(player.inventory, inv, player);
         EventManager.openWindow(player);
	}
	
	public void saveInventory(String fileName){
		NBTTagCompound comp = new NBTTagCompound();
		comp.setInteger("size", size);
		comp.setString("name", displayName);
		NBTTagList list = new NBTTagList();
		int index = 0;
		for (ItemStack cur : inv.getStacks()){
			NBTTagCompound item = new NBTTagCompound();
			if (cur!=null){
				cur.writeToNBT(item);
			}
			item.setInteger("slot", index);
			list.appendTag(item);
			index++;
		}
		comp.setTag("inventory", list);
		NBTSaver.saveCompound(fileName, comp);
	}
	
	public static CustomChestInventory readFromNBT(String fileName){
		NBTTagCompound comp = NBTSaver.readCompound(fileName);
		ItemStack[] stacks = new ItemStack[comp.getInteger("size")];
		CustomChestInventory inv = new CustomChestInventory(comp.getString("name"),stacks.length);
		NBTTagList list = comp.getTagList("inventory",10);
		for (int i = 0; i<inv.size; i++){
			NBTTagCompound item = (NBTTagCompound) list.get(i);
			stacks[item.getInteger("slot")]=ItemStack.loadItemStackFromNBT(item);
		}
		inv.inv.setStacks(stacks);
		return inv;
	}
	
	public void saveInventoryIn(String fileName, String subfolder){
		NBTTagCompound comp = new NBTTagCompound();
		comp.setInteger("size", size);
		comp.setString("name", displayName);
		NBTTagList list = new NBTTagList();
		int index = 0;
		for (ItemStack cur : inv.getStacks()){
			NBTTagCompound item = new NBTTagCompound();
			if (cur!=null){
				cur.writeToNBT(item);
			}
			item.setInteger("slot", index);
			list.appendTag(item);
			index++;
		}
		comp.setTag("inventory", list);
		NBTSaver.saveCompoundAt(fileName,subfolder , comp);
	}
	
	public static CustomChestInventory readFromNBTIn(String fileName , String subfolder){
		NBTTagCompound comp = NBTSaver.readCompoundAt(fileName , subfolder);
		ItemStack[] stacks = new ItemStack[comp.getInteger("size")];
		CustomChestInventory inv = new CustomChestInventory(comp.getString("name"),stacks.length);
		NBTTagList list = comp.getTagList("inventory",10);
		for (int i = 0; i<inv.size; i++){
			NBTTagCompound item = (NBTTagCompound) list.get(i);
			stacks[item.getInteger("slot")]=ItemStack.loadItemStackFromNBT(item);
		}
		inv.inv.setStacks(stacks);
		return inv;
	}
}
