package net.mineloader.util;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S45PacketTitle;

public enum TitleType{
	TITLE(false),
	SUBTITLE(true);
	
	private boolean sub;
	
	TitleType(boolean b){
		sub = b;
	}
	
	public boolean isSubtitle(){
		return sub;
	}
}
	
