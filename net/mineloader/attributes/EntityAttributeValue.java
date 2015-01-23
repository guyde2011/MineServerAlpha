package net.mineloader.attributes;

import java.util.UUID;

public class EntityAttributeValue {
	private Operation operation;
	private double amount;
	private UUID id;
	
	public EntityAttributeValue(Operation op , double value , UUID uuid){
		operation = op;
		id = uuid;
		amount = value;
	}
	
	public UUID getUUID(){
		return id;
	}
	
	public double getValue(){
		return amount;
	}
	

	
	public Operation getOperation(){
		return operation;
	}
}
