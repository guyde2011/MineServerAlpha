package net.mineloader.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class ChestInv implements IInventory{

	private ItemStack[] stacks;
	private String Name;
	public ChestInv(int size , String name){
		Name = name;
		stacks = new ItemStack[size];
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return Name;
	}

	@Override
	public boolean hasCustomName() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public IChatComponent getDisplayName() {
		// TODO Auto-generated method stub
		return new ChatComponentText(Name);
	}

	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return stacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int slotIn) {
		// TODO Auto-generated method stub
		return stacks[slotIn];
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		// TODO Auto-generated method stub
		ItemStack stack = stacks[index];
		stack.stackSize -= count;
		if (stack.stackSize<0){
			stacks[index] = null;
			return null;
		}
		stacks[index] = stack;
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int index) {
		// TODO Auto-generated method stub
		return stacks[index];
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		// TODO Auto-generated method stub
		stacks[index] = stack;
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 64;
	}

	@Override
	public void markDirty() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer playerIn) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void openInventory(EntityPlayer playerIn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory(EntityPlayer playerIn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getField(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clearInventory() {
		// TODO Auto-generated method stub
		
	}
	
	public ItemStack[] getStacks(){
		return stacks;
	}
	
	public void setStacks(ItemStack[] st){
		stacks = st;
	}

}
