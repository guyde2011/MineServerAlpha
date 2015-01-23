package net.mineloader.util;

public class Angle3D {
	private float pitch;
	private float yaw;
	
	public Angle3D(float Pitch , float Yaw){
		pitch = Pitch;
		yaw = Yaw;
	}
	
	public float getPitch(){
		return pitch;
	}
	
	public float getYaw(){
		return yaw;
	}
}
