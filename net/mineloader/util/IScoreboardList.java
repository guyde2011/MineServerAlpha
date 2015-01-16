package net.mineloader.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S3BPacketScoreboardObjective;
import net.minecraft.network.play.server.S3CPacketUpdateScore;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.mineloader.network.PacketSender;

public interface IScoreboardList {
	public void sendScores(EntityPlayerMP player);
	
	public void broadcastScores();
	public void sendObjective(EntityPlayerMP player);
	
	public void broadcastObjective();
	
	public ScoreObjective getObjective();
	
	public void removeEntry(MineName name);
}
