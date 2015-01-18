package net.mineloader.perms;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayerMP;

public class ConfigPerm {
	private String permission;
	private boolean hasNegative;
	private boolean isAll = false;
	
	private ConfigPerm(String perm){
		permission = "perms." + perm;
	}
	
	static ConfigPerm all(){
		ConfigPerm p = new ConfigPerm("all");
		p.isAll = true;
		p.hasNegative = false;
		return p;
	}
	
	public static ConfigPerm New(String permission){
		ConfigPerm p = new ConfigPerm(permission);
		p.hasNegative = true;
		return p;
	}
	
	public static ConfigPerm Positive(String permission){
		ConfigPerm p = new ConfigPerm(permission);
		p.hasNegative = false;
		return p;
	}
	
	public boolean isAllowed(Permissions perm){
		List<String> commands = new ArrayList<String>();
		if (!this.isAll){
			return ( commands.contains(permission) || (commands.contains("perms.all") && !(commands.contains("-" + this.permission) && this.hasNegative)));
		} else {
			return commands.contains("perms.all");
		}
	} 
	
	public boolean isAllowedFor(EntityPlayerMP player){
		if (PermissionsManager.Instance.onPerm(player.getName())){
			return isAllowed(PermissionsManager.Instance.getPerm(player.getName()));
		}
		
		return false;
	}
	
	public String toString(){
		return permission.substring(6);
	}
	
	
}
