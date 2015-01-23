package net.mineloader.util;

import net.minecraft.network.play.server.S27PacketExplosion;
import net.minecraft.network.play.server.S28PacketEffect;
import net.minecraft.network.play.server.S2APacketParticles;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.mineloader.util.packets.Effects;
import net.mineloader.util.packets.Particles;

public class PacketsUtil {

	
	public static S28PacketEffect EffectPacket(int id , double x , double y , double z , int data , boolean enableData){
		return new S28PacketEffect(id, new BlockPos(x,y,z), data, !enableData);
	}
	
	public static S28PacketEffect BlockBreakEffectPacket(double x , double y , double z , int blockId){
		return new S28PacketEffect(Effects.BLOCK_BREAK, new BlockPos(x,y,z), blockId, false);
	}
	
	public static S2APacketParticles ParticlePacket(Particles particle , MinePos pos , boolean long_distance , MinePos off , float particle_data , int count , int[] data){
		return new S2APacketParticles(EnumParticleTypes.func_179342_a(particle.getID()),long_distance , pos.X , pos.Y , pos.Z , off.X , off.Y , off.Z , particle_data , count , data);
	}
	

	
	
}
