package net.mineloader.event;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.BlockPos;

public class BlockMinedEvent extends PlayerEvent {
	public static int id = 6;
	public final int xCoord;
	public final int yCoord;
	public final int zCoord;
	public final Block block;
	
	public BlockMinedEvent(EntityPlayerMP Player , BlockPos pos , Block bl) {
		super(Player);
		block = bl;
		xCoord = pos.getX();
		yCoord = pos.getY();
		zCoord = pos.getZ();
	}
	
	

}
