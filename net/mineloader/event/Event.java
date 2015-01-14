package net.mineloader.event;

import net.minecraft.entity.player.EntityPlayerMP;

public class Event {
	public static int id;
	private boolean canceled = false;
	
	public boolean isCanceled(){
		return canceled;
	}
	
	public void Cancel(){
		canceled = true;
	}
}


