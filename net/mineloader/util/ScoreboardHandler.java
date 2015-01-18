package net.mineloader.util;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S3DPacketDisplayScoreboard;
import net.mineloader.network.PacketSender;

public class ScoreboardHandler {
	
	private ScoreboardHandler(){
		
	}
	
	public static void broadcastScoreboard(IScoreboardList list){
		list.broadcastObjective();
		PacketSender.broadcastPacket(new S3DPacketDisplayScoreboard(1 , list.getObjective()));
		list.broadcastScores();
	}
	
	public static void SetScoreboardTo(IScoreboardList list , EntityPlayerMP... players){
		for (EntityPlayerMP player : players){
			getManagerFor(player).setScoreboard(list);
		}
	}
	
	public static ScoreboardManager getManagerFor(EntityPlayerMP player){
		return new ScoreboardHandler().new ScoreboardManager(player);
	}
	
	public class ScoreboardManager{
		
		private EntityPlayerMP owner;
		
		public ScoreboardManager(EntityPlayerMP player){
			owner = player;
		}
		
		public void setScoreboard(IScoreboardList list){
			list.sendObjective(owner);
			PacketSender.sendPacket(new S3DPacketDisplayScoreboard(1 , list.getObjective()),owner);
			list.sendScores(owner);
		}
	}
}
