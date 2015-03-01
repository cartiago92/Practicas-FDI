package tp.pr5;

import java.util.ArrayList;

public abstract class Observable<T> {

	//ATRIBUTOS
	private ArrayList<T> lista;
	
	//CONTRUCTORA
	public Observable(){
		this.lista= new ArrayList<T>();
	}
	//METODOS
	
	public void addObserver(T elem){
		if (!lista.contains(elem))
		this.lista.add(elem);
	}
	
	public void deleteObserver(T elem){
				
		int i=0;
		boolean enc=false;
		
		while(!enc && i < lista.size()){
			if(lista.get(i).equals(elem)){
				enc=true;
				lista.remove(i);
			}
		i++;	
		}
	}
	
	public ArrayList<T> getObservers(){
		return this.lista;		
		
	}
	
	
	
}
