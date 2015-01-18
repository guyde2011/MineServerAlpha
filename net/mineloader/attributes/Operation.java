package net.mineloader.attributes;

public enum Operation
{
  SET(0),  ADD(1),  MULTIPLY(2);
  
  private int operation;
  
  private Operation(int i)
  {
    this.operation = i;
  }
  
  public int getOperation()
  {
    return this.operation;
  }
  
  public static Operation Parse(int id)
  {
    switch (id)
    {
    case 0: 
      return SET;
    case 1: 
      return ADD;
    case 2: 
      return MULTIPLY;
    }
    return null;
  }
}