package net.mineloader.scoreboard;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
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

public class ScoreboardNBTList extends AbstractScoreboardList{

	
	public ScoreboardNBTList(String name){
		super(name);
	}
	
	public ScoreboardNBTList(IScoreboardList scores){
		super(scores.getObjective().getName());
		scoreboards = scores.getEntries();
	}
	
	public void addEntry(MineEntry<Integer> entry){
		scoreboards.add(entry);
	}
	
	public void addEntry(MineName name , int value){
		scoreboards.add(new MineEntry<Integer>(name , value));
	}
	
	public NBTTagCompound toNBT(){
		NBTTagCompound comp = new NBTTagCompound();
		NBTTagList list = new NBTTagList();
		for (MineEntry<Integer> entry : scoreboards){
			NBTTagCompound scoresFor = new NBTTagCompound();
			scoresFor.setInteger("value",entry.getValue());
			scoresFor.setString("name",entry.getName());
			list.appendTag(scoresFor);
		}
		comp.setTag("values", list);
		comp.setString("name", obj.getName());
		return comp;
	}
	
	public static ScoreboardNBTList fromNBT(NBTTagCompound comp){
		NBTTagList list = comp.getTagList("values",10);
		ScoreboardNBTList ret =  new ScoreboardNBTList(comp.getString("name"));
		List<MineEntry<Integer>> entries = new ArrayList<MineEntry<Integer>>();
		for (int cur = 0; cur<list.tagCount(); cur++){
			NBTTagCompound value = list.getCompoundTagAt(cur);
			entries.add(MineEntry.IntEntry(comp));
		}
		ret.scoreboards = entries;
		return ret;
	}
	
	
}

	