package net.mineloader.perms;

import java.util.Arrays;
import java.util.List;

public class PermissionsManager {
	
	private List<Permissions> perms;
	
	private PermissionsManager(){
		perms = PermissionLoader.parseAllPerms();
	}
	
	public static PermissionsManager Instance;
	
	
	public static void init(){
		Instance = new PermissionsManager();
	}

	
	public void addPlayerToPermission(String name , String pn){
		int k = 0;
		for (Permissions perm : perms){
			if (perm.hasPlayer(name)){
				perm.removePlayer(name);
				PermissionWriter.writeGroupFile(perm);
				perms.set(k, perm);
			}
			if (perm.getUUID().equals(pn)){
				perm.addPlayer(pn);
				PermissionWriter.writeGroupFile(perm);
				perms.set(k, perm);
			}
			k++;
		}
	}
	
	public void RemovePlayerFromPermission(String name){
		int k = 0;
		for (Permissions perm : perms){
			if (perm.hasPlayer(name)){
				perm.removePlayer(name);
				PermissionWriter.writeGroupFile(perm);
				perms.set(k, perm);
			}
			k++;
		}
	}
	
	public void addCommandToPermission(String cmd , String pn){
		int k = 0;
		for (Permissions perm : perms){
			if (perm.getUUID().equals(pn)){
				perm.addCommand(cmd);
				PermissionWriter.writeGroupFile(perm);
				perms.set(k, perm);
			}
			k++;
		}
	}
	
	public void removeCommandFromPermission(String cmd , String pn){
		int k = 0;
		for (Permissions perm : perms){
			if (perm.getUUID().equals(pn)){
				perm.removeCommand(cmd);
				PermissionWriter.writeGroupFile(perm);
				perms.set(k, perm);
			}
			k++;
		}
	}
	
	public Permissions getPermission(String uuid){
		for (Permissions perm : perms){
			if (perm.getUUID().equals(uuid)){
				return perm;
			}
		}
		return null;
	}
	
	public void addPermissions(Permissions perm){
		this.perms.add(perm);
		PermissionWriter.writeGroupFile(perm);
	}
	
	public boolean canPlayerUseCommand(String name , String cmd){
		for (Permissions perm : perms){
			if (perm.hasPlayer(name)){
				List<String> cmds = Arrays.asList(perm.getCommands());
				if (cmds.contains(cmd) || (cmds.contains("all.perms") && !(cmds.contains("-" + cmd)))){
					return true;
				}
			}
		}
		return false;
	}
	
	public Permissions getPerm(String name){
		for (Permissions perm : perms){
			if (perm.hasPlayer(name)){
				return perm;
			}
		}
		return null;
	}
	
	public boolean onPerm(String name){
		return getPerm(name)!=null;
	}
}
