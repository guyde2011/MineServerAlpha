package net.mineloader.perms;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.mineloader.main.MineServer;

import com.google.common.io.Files;

public class PermissionLoader {
	
	private static List<String> readFile(File file){
		try {
			return Files.readLines(file, Charset.forName("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private static Permissions parseFile(File file){
		List<String> lines = readFile(file);
		int pInd = lines.indexOf("players:");
		int cInd = lines.indexOf("commands:");
		List<String> players = lines.subList(pInd+1, cInd);
		Collections.replaceAll(players, "", null);
		players.removeAll(Collections.singleton(null));
		List<String> commands = lines.subList(cInd+1, lines.size()-1);
		String col = lines.get(lines.size()-1);
		Collections.replaceAll(commands, "", null);
		commands.removeAll(Collections.singleton(null));
		Permissions per = new Permissions(players , lines.get(0) , lines.get(1) , commands);
		per.setColor(col);
		return per;
	}
	
	public static List<Permissions> parseAllPerms(){
		File[] files = new File(MineServer.MC_Path + "/perms").listFiles();
		List<Permissions> perms = new ArrayList<Permissions>();
		for (File file : files){
			if (file.getPath().endsWith(".mpc")){
				perms.add(parseFile(file));
			}
		}
		return perms;
	}
}
