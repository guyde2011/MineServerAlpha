package net.mineloader.inv;

import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;

public class Trade {
	private ItemStack main;
	private ItemStack secondary;
	private ItemStack traded;
	
	private Trade(ItemStack s1 , ItemStack s2 , ItemStack s3){
		main = s1;
		secondary = s2;
		traded = s3;
	}
	
	public MerchantRecipe toRecipe(){
		return new MerchantRecipe(main , secondary , traded);
	}
	
	public static Trade BasicTrade(ItemStack requested , ItemStack awarded){
		return new Trade(requested,null ,awarded);
	}
	
	public static Trade FullTrade(ItemStack requested , ItemStack extra , ItemStack awarded){
		return new Trade(requested, extra ,awarded);
	}
}
