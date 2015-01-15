package net.mineloader.util;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S45PacketTitle;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.mineloader.network.PacketSender;

public class Title {
	private int fadeIn;
	private int stay;
	private int fadeOut;
	private String text;
	private String subtext;
	private boolean timed;
	private boolean texted;
	
	private Title(String title , String sub){
		subtext = sub;
		text = title;
		timed = false;
		texted = true;
	}
	
	private Title(String title){
		text = title;
		timed = false;
		texted = true;
	}
	
	private Title(){
		timed = false;
		texted = true;
	}
	
	private Title(int FadeIn , int Stay , int FadeOut){
		fadeIn = FadeIn;
		stay = Stay;
		fadeOut = FadeOut;
		timed = true;
		texted = true;
	}
	
	public static Title subtitle(String title){
		Title t = new Title();
		t.subtext = title;
		return t;
	}
	
	public static Title title(String title){
		Title t = new Title(title);
		return t;
	}
	
	public static Title timedSubtitle(String title , int FadeIn , int Stay , int FadeOut){
		Title t = new Title(FadeIn , Stay , FadeOut);
		t.subtext = title;
		return t;
	}
	
	public static Title timedTitle(String title , int FadeIn , int Stay , int FadeOut){
		Title t = new Title(FadeIn , Stay , FadeOut);
		t.text = title;
		return t;
	}
	
	public static Title timeUpdate(int FadeIn , int Stay , int FadeOut){
		Title t = new Title(FadeIn , Stay , FadeOut);
		t.texted = false;
		return t;
	}
	
	public static void clearTo(EntityPlayerMP player){
		player.playerNetServerHandler.sendPacket(new S45PacketTitle(S45PacketTitle.Type.CLEAR , new ChatComponentText("")));
	}
	
	public static void clearToAll(){
		PacketSender.broadcastPacket(new S45PacketTitle(S45PacketTitle.Type.CLEAR , new ChatComponentText("")));
	}
	
	public static void clear(EntityPlayerMP... players){
		for (EntityPlayerMP player : players){
			clearTo(player);
		}
	}
	
	public void sendTo(EntityPlayerMP player){
		if (timed){
			player.playerNetServerHandler.sendPacket(new S45PacketTitle(fadeIn , stay , fadeOut));
		}
		if (texted){
			if (subtext!=null){
				player.playerNetServerHandler.sendPacket(new S45PacketTitle(S45PacketTitle.Type.SUBTITLE,new ChatComponentText(subtext)));
			} else {
				player.playerNetServerHandler.sendPacket(new S45PacketTitle(S45PacketTitle.Type.TITLE,new ChatComponentText(text)));
			}
		}	
	}
	
	public void broadcast(){
		if (timed){
			PacketSender.broadcastPacket(new S45PacketTitle(fadeIn , stay , fadeOut));
		}
		if (texted){
			if (subtext!=null){
				PacketSender.broadcastPacket(new S45PacketTitle(S45PacketTitle.Type.SUBTITLE,new ChatComponentText(subtext)));
			} else {
				PacketSender.broadcastPacket(new S45PacketTitle(S45PacketTitle.Type.TITLE,new ChatComponentText(text)));
			}
		}
	}
	
	public void send(EntityPlayerMP... players){
		for (EntityPlayerMP player : players){
			sendTo(player);
		}
	}
	
	
}
