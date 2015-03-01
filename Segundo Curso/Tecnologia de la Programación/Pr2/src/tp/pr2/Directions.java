package tp.pr2;

public enum Directions {

	EAST, NORTH, SOUTH, WEST, UNKNOWN; 
	
 public Directions getOposite(){
	 switch (this){
	 case EAST: return WEST; 
	 case NORTH: return SOUTH;
	 case SOUTH: return NORTH;
	 case WEST: return EAST;
	 }
	 return UNKNOWN;
	 }
 }



