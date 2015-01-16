package net.mineloader.perms;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import net.mineloader.main.MineServer;

public class PermissionWriter {
	
	
	private static File newFile(String name){
		try {
			new File(MineServer.MC_Path + "/perms/" + name + ".mpc").createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new File(MineServer.MC_Path + "/perms/" + name + ".mpc");
	}
	private static List<String> writeGroup(Permissions perms){
		List<String> lines = new ArrayList<String>();
		lines.add(perms.getName());
		lines.add(perms.getUUID());
		lines.add("players:");
		for (String name : perms.getPlayers()){
			lines.add(name);
		}
		lines.add("commands:");
		for (String command : perms.getCommands()){
			lines.add(command);
		}
		lines.add(perms.getColor());
		return lines;
	}
	
	public static void writeGroupFile(Permissions perms){
		List<String> lines = writeGroup(perms);
		FileOutputStream stream;
		BufferedWriter writer = null;
		try {
			stream = new FileOutputStream(newFile(perms.getName()));
			writer = new BufferedWriter(new OutputStreamWriter(stream , "utf-8"));
			for (String line : lines){
				writer.write(line + "\n");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
