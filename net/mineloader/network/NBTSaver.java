package net.mineloader.network;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.mineloader.main.MineServer;

public class NBTSaver {
	
	public static void saveCompound(String fileName , NBTTagCompound comp){
		DataOutputStream stream = null;
		try {
			getFile(fileName).createNewFile();
			stream = new DataOutputStream(new FileOutputStream(getFile(fileName)));
			CompressedStreamTools.write(comp, stream);
			stream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static NBTTagCompound readCompound(String fileName){
		DataInputStream stream = null;
		NBTTagCompound comp = null;
		try {
			getFile(fileName).createNewFile();
			stream = new DataInputStream(new FileInputStream(getFile(fileName)));
			comp = CompressedStreamTools.read(stream);
			stream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comp;
	}
	
	public static void saveCompoundAt(String fileName , String subfolder , NBTTagCompound comp){
		DataOutputStream stream = null;
		if(!new File(MineServer.MC_Path + "/data/" + subfolder).exists()){
			try {
				new File(MineServer.MC_Path + "/data/" + subfolder).createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			getFile(fileName).createNewFile();
			stream = new DataOutputStream(new FileOutputStream(getFile(fileName)));
			CompressedStreamTools.write(comp, stream);
			stream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static NBTTagCompound readCompoundAt(String fileName , String subfolder){
		DataInputStream stream = null;
		NBTTagCompound comp = null;

		
		if (new File(MineServer.MC_Path + "/data/" + subfolder).exists()){
		try {
			getFile(subfolder + "/" + fileName).createNewFile();
			stream = new DataInputStream(new FileInputStream(getFile(subfolder + "/" + fileName)));
			comp = CompressedStreamTools.read(stream);
			stream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return comp;
	}
	
	private static File getFile(String fileName){
		return new File(MineServer.MC_Path + "/data/" + fileName + ".dat");
	}
	
	

}
