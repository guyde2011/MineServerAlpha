package net.mineloader.util;

import net.minecraft.network.play.server.S28PacketEffect;
import net.minecraft.util.BlockPos;

public class PacketsUtil {
	
	public static final int SMOKE = 2000;
	public static final int BLOCK_BREAK = 2001;
	public static final int SPLASH_POTION = 2002;
	public static final int ENDER_EYE = 2003;
	public static final int MOB_SPAWN = 2004;
	public static final int HAPPY_VILLAGER = 2005;
	public static final int CLICK1 = 1000;
	public static final int CLICK2 = 1001;
	public static final int BOW = 1002;
	public static final int DOOR = 1003;
	public static final int FIZZ = 1004;
	public static final int DISC = 1005;
	public static final int GHAST_CHARGE = 1007;
	public static final int GHAST_FIREBALL1 = 1008;
	public static final int GHAST_FIREBALL2 = 1009;
	public static final int ZOMBIE_WOOD1 = 1010;
	public static final int ZOMBIE_METAL = 1011;
	public static final int ZOMBIE_WOOD2 = 1012;
	public static final int WITHER_SPAWN = 1013;
	public static final int WITHER_SHOOT = 1014;
	public static final int BAT_TAKEOFF = 1015;
	public static final int ZOMBIE_INFECT = 1016;
	public static final int ZOMBIE_UNINFECT = 1017;
	public static final int ENDERDRAGON_DEATH = 1018;
	public static final int ANVIL_BREAK = 1020;
	public static final int ANVIL_USE = 1021;
	public static final int ANVIL_LAND = 1022;
	
	public S28PacketEffect effectPacket(int id , double x , double y , double z , int data , boolean enableData){
		return new S28PacketEffect(id, new BlockPos(x,y,z), data, !enableData);
	}
	
	public S28PacketEffect blockBreakEffectPacket(double x , double y , double z , int blockId){
		return new S28PacketEffect(PacketsUtil.BLOCK_BREAK, new BlockPos(x,y,z), blockId, false);
	}
	
	
}
