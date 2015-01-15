package net.mineloader.inv;

import io.netty.buffer.Unpooled;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.inventory.InventoryMerchant;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.S2DPacketOpenWindow;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;

public class TradeInventory implements IMerchant{
	private EntityPlayerMP inGUI;
	private String name;
	private MerchantRecipeList recipes;
	public TradeInventory(String d){
		name = d;
		recipes = new MerchantRecipeList();
	}
	
	public void addTrade(Trade trade){
		recipes.add(trade.toRecipe());
	}
	public void openInventory(EntityPlayerMP player){
		this.setCustomer(player);
		player.displayVillagerTradeGui(this);
	}

	@Override
	public void setCustomer(EntityPlayer player) {
		// TODO Auto-generated method stub
		inGUI = (EntityPlayerMP) player;
	}

	@Override
	public EntityPlayer getCustomer() {
		// TODO Auto-generated method stub
		return inGUI;
	}

	@Override
	public MerchantRecipeList getRecipes(EntityPlayer p_70934_1_) {
		// TODO Auto-generated method stub
		return recipes;
	}

	@Override
	public void useRecipe(MerchantRecipe recipe) {
		// TODO Auto-generated method stub
		recipe.incrementToolUses();
	}

	@Override
	public void verifySellingItem(ItemStack p_110297_1_) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IChatComponent getDisplayName() {
		// TODO Auto-generated method stub
		return new ChatComponentText(name);
	} 
}
