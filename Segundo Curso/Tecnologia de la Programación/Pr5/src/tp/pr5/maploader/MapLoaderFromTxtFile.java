package tp.pr5.maploader;
import java.io.*;
import tp.pr5.*;
import tp.pr5.items.*;
import tp.pr5.maploader.exceptions.WrongMapFormatException;

import java.util.ArrayList;

public class MapLoaderFromTxtFile {
 
 public MapLoaderFromTxtFile(){
	 
 }
 
 public ArrayList<Room> LoaderRoom(BufferedReader b)throws IOException{
	
	 String [] pal;
	 int cont=0;
	 ArrayList<Room> hab=new ArrayList<Room>();
	 String aux;
	 
	 if (b.readLine().equalsIgnoreCase("BeginRooms")){
		while(!(aux=b.readLine()).equalsIgnoreCase("EndRooms")){
		   pal=aux.split(" ");
			
		   if (pal.length==1){
			  if (!pal[0].equalsIgnoreCase("EndRooms")) throw new WrongMapFormatException("Endrooms word is not in the file or read online to load the Rooms");  
		   }
			
		   if (pal.length==5){
			   String cad=pal[3].replace("_", " ");  
		       if (pal[0].equalsIgnoreCase("room")){
			       int num=Integer.parseInt(pal[1]);
			       if (cont==num) {
				      Room ro;
			          if (pal[4].equalsIgnoreCase("exit"))ro=new Room(true,cad,pal[2]);
			          else if (pal[4].equalsIgnoreCase("noexit")) ro=new Room(false,cad,pal[2]);
			          else throw new WrongMapFormatException(); 
			          hab.add(ro);
		           }//if cont
			       else throw new WrongMapFormatException("Read the number of rooms does not match the number of rooms created");
		       }//if room
		       else throw new WrongMapFormatException("Room word is not in the read line to load the Rooms");
		   }//if lenght
		   else throw new WrongMapFormatException("The number of words in the line read is not the desired to load the Rooms"); 
		   cont++;
		}//while 
		
	 }//if readline()
	 else throw new WrongMapFormatException("BeginRooms word is not in the file or read online to load the Rooms"); 
	 return hab;
	 	
 }
 
 public ArrayList<Door> LoadDoor(BufferedReader b, ArrayList<Room> r, Map map)throws IOException{
 
	 String [] pal;
	 int cont=0;
	 ArrayList<Door> dor=new ArrayList<Door>();
	 String aux1;
	 
	 if (b.readLine().equalsIgnoreCase("BeginDoors")){
        while(!(aux1=b.readLine()).equalsIgnoreCase("EndDoors")){
		   pal=aux1.split(" ");Door d;
			
		   if (pal.length==1){
			  if (!pal[0].equalsIgnoreCase("EndDoors")) throw new WrongMapFormatException("Enddoors word is not in the file or read online to load the Doors");  
		   }
		 
		   else if (pal.length==9){
		       if (pal[0].equalsIgnoreCase("door")){
		           if (cont==Integer.parseInt(pal[1])){
			           if ((pal[8].equalsIgnoreCase("closed"))||(pal[8].equalsIgnoreCase("open"))){ 
			              if ((pal[3].equalsIgnoreCase("room"))&&(pal[6].equalsIgnoreCase("room"))){
			   		         int source=Integer.parseInt(pal[4]);
					         int target=Integer.parseInt(pal[7]); 
					         Directions aux;
					           if (pal[5].equalsIgnoreCase("south"))aux=Directions.SOUTH;
					           else if(pal[5].equalsIgnoreCase("north"))aux=Directions.NORTH;
					           else if(pal[5].equalsIgnoreCase("east"))aux=Directions.EAST;
					           else if(pal[5].equalsIgnoreCase("west"))aux=Directions.WEST;
					           else throw new WrongMapFormatException("Read the address on file is inadequate");
					             if (pal[2].equalsIgnoreCase("directional")){
					    	       d=map.addDoor(r.get(source), aux, r.get(target));
					    	       dor.add(d);
					               if (pal[8].equalsIgnoreCase("closed"))d.close();
					               else d.open();
					             }
					             else if (pal[2].equalsIgnoreCase("bidirectional")){
					    	       d=map.addBidirectionalDoor(r.get(source), aux, r.get(target));
					    	       dor.add(d);
					               if (pal[8].equalsIgnoreCase("closed"))d.close();
					               else d.open(); 
			                     }//if bidirec o direc
					             else throw new WrongMapFormatException("Directional word or bidirectional word are not in the read line to load the Doors");
				          }//if room
				          else throw new WrongMapFormatException("Room word is not in the read line to load the Doors");
			           }//if closed
			           else throw new WrongMapFormatException("Closed word or open word are not in the read line to load the Doors");
		           }//if cont
		           else throw new WrongMapFormatException("Read the number of dorrs does not match the number of doors created");
		        }//if door
		        else throw new WrongMapFormatException("Room word is not in the read line to load the Doors");
		   }//length
		   else throw new WrongMapFormatException("The number of words in the line read is not the desired to load the Doors");
		   cont++;
		}//while
	 }//if readline()
	 else throw new WrongMapFormatException("Begindoor word is not in the file or read online to load the Doors"); 
	 return dor;
	 
 }
 
 public void LoadItem(BufferedReader b, ArrayList<Room> r,ArrayList<Door> d)throws IOException{
	
 	 String []pal;
	 int cont=0;
	 int m,n,p;
	 String aux;
	
	 if (b.readLine().equalsIgnoreCase("BeginItems")){
	
	    while(!(aux=b.readLine()).equalsIgnoreCase("EndItems")){
		    pal=aux.split(" ");
		  
		    if (pal.length==1){
			   if (!pal[0].equalsIgnoreCase("EndItems")) throw new WrongMapFormatException("EndItems word is not in the file or read online to load the Items");  
		    }
		   
		    else if (cont==Integer.parseInt(pal[1])){
		        if (pal.length==8){
		            String cad=pal[3].replaceAll("_", " ");
			        if (pal[6].equalsIgnoreCase("room")){
				        if (pal[0].equalsIgnoreCase("food")){
					       m=Integer.parseInt(pal[7]);
					       n=Integer.parseInt(pal[4]);
					       p=Integer.parseInt(pal[5]);
					       r.get(m).addItem(new Food(pal[2], cad, n, p)); 
				        }//if food
				        else if (pal[0].equalsIgnoreCase("key")){
					       if (pal[4].equalsIgnoreCase("door")){
					          m=Integer.parseInt(pal[7]);
					          n=Integer.parseInt(pal[5]);
					          r.get(m).addItem(new Key(pal[2], cad, d.get(n))); 
					       }//if key   
					       else throw new WrongMapFormatException("Door word was not found in the file to load the item");
				        }//if food key
				        else throw new WrongMapFormatException("Key word or food word were not found in the file to load the item"); 
			        }//if room
		        }//if length
		        
		        else if (pal.length==7){
		           if (pal[0].equalsIgnoreCase("valuable")){
		    	      if (pal[5].equalsIgnoreCase("room")){
		    		     m=Integer.parseInt(pal[6]);
		    		     p=Integer.parseInt(pal[4]);
		    		     String cad=pal[3].replaceAll("_", " ");
		    		     r.get(m).addItem(new Valuable(pal[2], cad, p)); 
		    	      }//if room
		    	      else throw new WrongMapFormatException("Room word is not in the file or read online to load the Items"); 
		           }//if valuable
		           else throw new WrongMapFormatException("Valuable word is not in the file or read online to load the Items"); 
		        }//if length
		        else throw new WrongMapFormatException("The number of words in the line read is not the desired to load the Rooms"); 
		    }//if cont
		    else throw new WrongMapFormatException("Read the number of items does not match the number of items created");
		    cont++;
	    }//while
	 
	 }//if readline()
	 else throw new WrongMapFormatException("BeginItems word is not in the file or read online to load the Doors"); 
 }
	 
 
 public Map loadMap(InputStream file)throws IOException{
	 
  try{	
	 BufferedReader b = new BufferedReader(new InputStreamReader(file));
	 Map mapa;
	 if (b.readLine().equalsIgnoreCase("BeginMap")){
	    ArrayList<Room> rom=LoaderRoom(b);
	    mapa=new Map(rom.get(0));
        ArrayList<Door> dor=LoadDoor(b,rom,mapa);
	    LoadItem(b,rom,dor);
	 }
	 else throw new WrongMapFormatException("BeginMap word is not in the file or read online to load the Map"); 
	 
	 if (!b.readLine().equalsIgnoreCase("EndMap")) throw new WrongMapFormatException("EndMap word is not in the file or read online to load the Map"); 
	 b.close();
	 return mapa;
     }
  
 catch(Exception e){
		throw new WrongMapFormatException("Error: File format is not suitable",e);}
  
 }
 
}
