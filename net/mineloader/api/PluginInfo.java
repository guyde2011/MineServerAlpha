package net.mineloader.api;

import net.mineloader.main.MineServer;

public final class PluginInfo {
	public String Author;
	public String mod_loc_name;
	public String mod_unloc_name;
	public MinecraftVersion MC_version;
	public String mod_version;
	public String path;
	public PluginInfo(Plugin m , String auth , String version , String Path){
		MC_version = MinecraftVersion.current;
		mod_version = version;
		mod_unloc_name = m.UnlocalizedName;
		mod_loc_name = m.LocalizedName;
		Author = auth;
		path = Path;
	}
}
