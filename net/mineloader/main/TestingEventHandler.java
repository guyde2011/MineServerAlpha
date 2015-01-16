package net.mineloader.main;

import java.util.Arrays;
import java.util.List;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.mineloader.event.BlockMinedEvent;
import net.mineloader.event.EntityClickEvent;
import net.mineloader.event.EventHandler;
import net.mineloader.inv.Trade;
import net.mineloader.inv.TradeInventory;
import net.mineloader.perms.PermissionsManager;
import net.mineloader.perms.PermissionsRegistry;

public class TestingEventHandler {
	@EventHandler
	public static void onEntityClick(EntityClickEvent ev){
		if (ev.clickedEntity instanceof EntityVillager){
			if (ev.clickedEntity.getName().equals("George")){
				TradeInventory inv = new TradeInventory("Testing Testificate");
				inv.addTrade(Trade.BasicTrade(new ItemStack(Items.melon,3), new ItemStack(Items.cookie)));
				inv.openInventory(ev.player);
				ev.Cancel();
			}
		}
	}
	

}
