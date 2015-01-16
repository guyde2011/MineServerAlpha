package net.mineloader.main;

import net.mineloader.event.BlockMinedEvent;
import net.mineloader.event.EventHandler;
import net.mineloader.perms.PermissionsRegistry;

public class MSEventHandler {
	@EventHandler
	public static void onBlockDestroyed(BlockMinedEvent ev){
		if (!PermissionsRegistry.MINE_BLOCKS.isAllowedFor(ev.player)){
			ev.Cancel();
		}
	}
}
