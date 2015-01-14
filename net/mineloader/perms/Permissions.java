package net.mineloader.perms;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumChatFormatting;

public class Permissions {
	private List<String> players;
	private String name;
	private String UUID;
	private List<String> commands;
	private String color;
	public Permissions(String[] part , String localName , String unlocName , String[] Commands ){
		players = Arrays.asList(part);
		name = localName;
		UUID = unlocName;
		commands = Arrays.asList(Commands);
		color = EnumChatFormatting.WHITE + "";
	}
	
	public Permissions(String[] part , String localName , String unlocName){
		players = Arrays.asList(part);
		name = localName;
		UUID = unlocName;
		commands = Arrays.asList(new String[]{});
		color = EnumChatFormatting.WHITE + "";
	}
	
	public Permissions(List<String> part , String localName , String unlocName , List<String> Commands ){
		players = part;
		name = localName;
		UUID = unlocName;
		commands = Commands;
		color = EnumChatFormatting.WHITE + "";
	}
	
	public Permissions(List<String> part , String localName , String unlocName){
		players = part;
		name = localName;
		UUID = unlocName;
		commands = Arrays.asList(new String[]{});
		color = EnumChatFormatting.WHITE + "";
	}
	
	public Permissions(String localName , String unlocName){
		players = Arrays.asList(new String[]{});
		name = localName;
		UUID = unlocName;
		commands = Arrays.asList(new String[]{});
		color = EnumChatFormatting.WHITE + "";
	}
	
	public Permissions(String unlocName){
		players = Arrays.asList(new String[]{});
		name = null;
		UUID = unlocName;
		commands = Arrays.asList(new String[]{});
		color = EnumChatFormatting.WHITE + "";
	}
	
	public void setColor(String col){
		color = col;
	}
	
	public boolean hasName(){
		return name!=null;
	}
	
	public String getName(){
		return name;
	}
	
	public String[] getCommands(){
		return commands.toArray(new String[commands.size()]);
	}
	
	public boolean hasPlayer(String player){
		return players.contains(player);
	}
	
	public void addPlayer(String pName){
		players.add(pName);
	}
	
	public boolean addCommand(String com_name){
			commands.add(com_name);
			return true;
	}
	
	public String getUUID(){
		return UUID;
	}

	public String[] getPlayers() {
		return players.toArray(new String[players.size()]);
	}
	
	public void removePlayer(String player){
		Collections.replaceAll(players, player, null);
		players.removeAll(Collections.singleton(null));
	}
	
	public void removeCommand(String Command){
		Collections.replaceAll(commands , Command , null);
		commands.removeAll(Collections.singleton(null));
	}
	
	
	public String toString(){
		return color +"[" + name + "]";
	}

	public String getColor() {
		// TODO Auto-generated method stub
		return color;
	}
	
	

	
}
