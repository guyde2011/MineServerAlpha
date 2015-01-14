package net.mineloader.main;

public class Time {
	private long time;
	private boolean sync;
	
	public Time(long Time){
		time = Time;
		sync = false;
	}
	
	public static Time synced(){
		Time ti = new Time(1);
		ti.sync = true;
		return ti;
	}
	
	public void set(long t){
		time = t;
	}
	
	public long get(){
		return time;
	}
	
	public boolean isSynced(){
		return sync;
	}
}
