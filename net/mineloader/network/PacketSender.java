package net.mineloader.network;

import io.netty.buffer.Unpooled;

import java.nio.ByteBuffer;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.network.play.server.S28PacketEffect;
import net.minecraft.server.MinecraftServer;

public class PacketSender {
	
	public static void sendPacket(Packet packet , EntityPlayerMP toPlayer){
		toPlayer.playerNetServerHandler.sendPacket(packet);
	}
	
	public static void broadcastPacket(Packet packet){
		MinecraftServer.getServer().getConfigurationManager().sendPacketToAllPlayers(packet);
	}
	
	public static void sendPacketInRadius(Packet packet , double x , double y , double z , double radius , int dimension){
		MinecraftServer.getServer().getConfigurationManager().sendToAllNear(x, y, z, radius, dimension, packet);
	}
	
	public static void sendPacketInDimension(Packet packet , int dim){
		MinecraftServer.getServer().getConfigurationManager().sendPacketToAllPlayersInDimension(packet, dim);
	}
}
