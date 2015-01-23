package net.mineloader.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3i;



public class MinePos extends Vector3{
	
	public MinePos(float x , float y , float z){
		super(x,y,z);
	}
	
	public MinePos multiply(float multi){
		this.X = X * multi;
		this.Y = Y * multi;
		this.Z = Z * multi;
		return this;
	}
	
	public void divide(float div){
		this.multiply(1f/div);
	}
	
	public void add(Vector3 a){
		this.X += a.X;
		this.Y += a.Y;
		this.Z += a.Z;
	}
	
	public void substract(Vector3 a){
		this.X -= a.X;
		this.Y -= a.Y;
		this.Z -= a.Z;
	}
	
	public float distanceSquared(Vector3 a){
		MinePos b = copy();
		b.substract(a);
		float disSquared = b.X * b.X + b.Y * b.Y + b.Z * b.Z;
		return disSquared;
	}
	
	public float distance(Vector3 a){
		return (float) Math.sqrt(this.distanceSquared(a));
	}
	
	public MinePos(Vector3 vec){
		super(vec.X , vec.Y , vec.Z);
	}
	
	public MinePos(Vec3i vec){
		super(vec.getX() , vec.getY() , vec.getZ());
	}
	
	public MinePos(Vec3 vec) {
		super(vec.xCoord , vec.yCoord , vec.zCoord);
	}
	
	public MinePos(Entity ent) {
		super(ent.posX , ent.posY , ent.posZ);
	}

	public MinePos(double x , double y , double z) {
		super((float)x , (float)y , (float)z);
	}

	public void rotate(float xRot , float yRot , float zRot){
		rotateX(xRot);
		rotateY(yRot);
		rotateZ(zRot);
	}
	
	public MinePos copy(){
		return new MinePos(this);
	}
	
	public Angle3D toAngle(){

		float radius = (float)Math.sqrt(X*X + Y*Y + Z*Z);
		float yaw = (float)Math.atan(X/Z);
		float pitch = (float) Math.acos(Y/radius);
		return new Angle3D(pitch , yaw);
	}
	
	public BlockPos toBlockPos(){
		return new BlockPos(X,Y,Z);
	}
	
	
	public void rotateZ(float angle){
		Float oldX = X; 
	    X = (float)(X * Math.cos(angle) - Y * Math.sin(angle));
	    Y = (float)(oldX * Math.sin(angle) + Y * Math.cos(angle));
	}
	
	public void rotateX(float angle){
		Float oldY = Y; 
	    Y = (float)(Y *Math.cos(angle) - Z * Math.sin(angle));
	    Z = (float)(oldY * Math.sin(angle) + Z * Math.cos(angle));
	}
	
	public void rotateY(float angle){
		Float oldZ = Z; 
	    this.Z = (float)(Z *Math.cos(angle) - X * Math.sin(angle));
	    this.X = (float)(oldZ * Math.sin(angle) + X * Math.cos(angle));
	}
}
