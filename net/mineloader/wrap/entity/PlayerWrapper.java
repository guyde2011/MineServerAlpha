package net.mineloader.wrap.entity;

import java.util.Calendar;
import java.util.Date;

import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.UserListBansEntry;
import net.mineloader.wrap.EntityLivingWrapper;
import net.mineloader.wrap.EntityWrapper;

public class PlayerWrapper extends EntityLivingWrapper<EntityPlayerMP> {

	public PlayerWrapper(EntityPlayerMP ent) {
		super(ent);
	}
	
	public InventoryPlayer getInventory(){
		return owner.inventory;
	}
	
	public void setInventory(InventoryPlayer inv){
		owner.inventory = inv;
	}
	
	public void kick(String message){
        String var4 = "Kicked from the server.";
        if (message!=null){
        	var4 = message;
        }
        owner.playerNetServerHandler.kickPlayerFromServer(var4);
	}
	
	public void ban(String message , String reason){
        String var4 = "Banned from the server.";
        UserListBansEntry ent = new UserListBansEntry(MinecraftServer.getServer().getPlayerProfileCache().getGameProfileForUsername(getName()), (Date)null, getName(), (Date)null, reason);
        MinecraftServer.getServer().getConfigurationManager().getBannedPlayers().addEntry(ent);
        if (message!=null){
        	var4 = message;
        }
        owner.playerNetServerHandler.kickPlayerFromServer(var4);
	}
	
	public void tempban(String message , String reason , long ticks){
        String var4 = "Tempbanned from the server for " + ticks/1200 + " minutes.";
        UserListBansEntry ent = new UserListBansEntry(MinecraftServer.getServer().getPlayerProfileCache().getGameProfileForUsername(getName()), (Date)null, getName(), new Date(Calendar.getInstance().getTime().getTime()+ticks*50), reason);
        MinecraftServer.getServer().getConfigurationManager().getBannedPlayers().addEntry(ent);
        if (message!=null){
        	var4 = message;
        }
        owner.playerNetServerHandler.kickPlayerFromServer(var4);
	}
	
	public void setHunger(int hunger){
		owner.foodStats.setFoodLevel(hunger);
	}
	
	public int getHunger(){
		return owner.foodStats.getFoodLevel();
	}
	
	
	

}
