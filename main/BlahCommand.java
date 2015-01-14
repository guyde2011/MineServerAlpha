package main;

import java.util.Arrays;
import java.util.List;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class BlahCommand implements ICommand {

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return "blah";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "/blah [number] xplode";
	}

	@Override
	public List getCommandAliases() {
		// TODO Auto-generated method stub
		return Arrays.asList("blaah","blaaah");
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args)
			throws CommandException {
		// TODO Auto-generated method stu
		if(args.length==0){
			throw new CommandException("Illegal arguments", "");
		}
		if (!args[0].matches("-?\\d+(\\.\\d+)?")){
			throw new CommandException("Illegal arguments", args[0]);
		}
		int amount = Integer.parseInt(args[0]);
		Vec3 V = sender.getPositionVector();
		World world = sender.getEntityWorld();
		for (int i =0;i<amount;i++){
			EntitySquid sq = new EntitySquid(world);
			sq.setPosition(V.xCoord , V.yCoord , V.zCoord);
			world.spawnEntityInWorld(sq);
			
		}

	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		// TODO Auto-generated method stub
		return sender.canCommandSenderUseCommand(4, "reload");
	}

	@Override
	public List addTabCompletionOptions(ICommandSender sender, String[] args,
			BlockPos pos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		// TODO Auto-generated method stub
		return false;
	}

}
