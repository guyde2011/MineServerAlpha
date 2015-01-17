package net.mineloader.scoreboard;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S3BPacketScoreboardObjective;
import net.minecraft.network.play.server.S3CPacketUpdateScore;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.mineloader.network.PacketSender;
import net.mineloader.util.MineEntry;
import net.mineloader.util.MineName;

public interface IScoreboardList {


	public ScoreObjective getObjective();
	
	public List<MineEntry<Integer>> getEntries();
	
	public void removeEntry(MineName name);
}
