package net.mineloader.perms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermissionsRegistry {
	public static final ConfigPerm ALL = ConfigPerm.all();
	public static final ConfigPerm MINE_BLOCKS = ConfigPerm.New("mine_blocks");
	public static final ConfigPerm CLICK_BLOCKS = ConfigPerm.New("click_blocks");
	private static final List<ConfigPerm> perms = new ArrayList<ConfigPerm>();
	private static final Map<String , Integer> indexes = new HashMap<String , Integer>();
	
	public static void addPerm(ConfigPerm perm){
		indexes.put(perm.toString() , perms.size());
		perms.add(perm);
	}
	static{
		addPerm(ALL);
		addPerm(MINE_BLOCKS);
		addPerm(CLICK_BLOCKS);
	}
	
	public static List<ConfigPerm> getAllPermissions(){
		return perms;
	}
	
	public static ConfigPerm get(String str){
		return perms.get(indexes.get(str));
	}
	
}
