package net.mineloader.util;

public class Vector3 {
	public float X;
	public float Y;
	public float Z;
	public Vector3(float x , float y , float z){
		X = x;
		Y = y;
		Z = z;
		
	}
	public Vector3(double x, double y, double z) {
		X = (float) x;
		Y = (float) y;
		Z = (float) z;
	}
	
	public String toString(){
		return "Vector3: X: " + X + " Y: " + Y + " Z: " + Z;
	}
}
