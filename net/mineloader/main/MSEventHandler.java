package net.mineloader.main;

import net.minecraft.init.Blocks;
import net.mineloader.event.BlockClickedEvent;
import net.mineloader.event.BlockMinedEvent;
import net.mineloader.event.EventHandler;
import net.mineloader.inv.CustomChestInventory;
import net.mineloader.perms.PermissionsRegistry;

public class MSEventHandler {
	@EventHandler
	public static void onBlockDestroyed(BlockMinedEvent ev){
		if (!PermissionsRegistry.MINE_BLOCKS.isAllowedFor(ev.player)){
			ev.Cancel();
		}
	}
	
	@EventHandler
	public static void onBlockClicked(BlockClickedEvent ev){
		if (!PermissionsRegistry.CLICK_BLOCKS.isAllowedFor(ev.player)){
			ev.Cancel();
		}
	}
}
