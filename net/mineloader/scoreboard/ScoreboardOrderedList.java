package net.mineloader.scoreboard;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S3BPacketScoreboardObjective;
import net.minecraft.network.play.server.S3CPacketUpdateScore;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.server.MinecraftServer;
import net.mineloader.network.PacketSender;
import net.mineloader.util.MineEntry;
import net.mineloader.util.MineName;

public class ScoreboardOrderedList extends AbstractScoreboardList{

	
	public ScoreboardOrderedList(String name){
		super(name);
		index = 1024;
	}
	private int index;
	
	
	public void addEntry(MineName name){
		scoreboards.add(new MineEntry<Integer>(name , index));
		index--;
	}
}

	