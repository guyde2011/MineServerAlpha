package net.mineloader.util;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.mineloader.wrap.EntityWrapper;
import net.mineloader.wrap.entity.PlayerWrapper;

public abstract class MineCommand extends CommandBase{

	private final String command_name;
	
	public MineCommand(String name){
		command_name = name;
	}
	
	public abstract String CommandUsage(EntityWrapper<EntityPlayerMP> sender);
	public abstract void onCommandActivated(EntityWrapper<EntityPlayerMP> sender , String[] args) throws CommandException;
	public abstract void onEntityCommandRun(EntityWrapper<Entity> sender , String[] args) throws CommandException;
	
	@Override
	public String getCommandName() {
		return command_name;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return CommandUsage(new PlayerWrapper((EntityPlayerMP)sender.getCommandSenderEntity()));
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args)
			throws CommandException {
		if (sender.getCommandSenderEntity() instanceof EntityPlayer){
			onCommandActivated(new PlayerWrapper((EntityPlayerMP) sender.getCommandSenderEntity()), args);
		} else {
			onEntityCommandRun(EntityWrapper.Generic(sender.getCommandSenderEntity()), args);
		}
		
		
	}

}
