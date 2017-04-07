package si.fri.prpo;

import java.io.Serializable;

/**
 * Abstraktni razred ki predstavlja genericno entiteto z enolicnim kljucem id
 * @author skupina05
 *
 */
public abstract class Entiteta implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
}
