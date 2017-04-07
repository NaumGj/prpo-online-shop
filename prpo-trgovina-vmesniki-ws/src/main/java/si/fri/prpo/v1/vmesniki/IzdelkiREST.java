package si.fri.prpo.v1.vmesniki;

import javax.ws.rs.core.Response;

import si.fri.prpo.izjeme.IzdelekNotFoundException;
import si.fri.prpo.izjeme.NeustreznoPorociloException;
import si.fri.prpo.spletna_trgovina_jpa.entitete.Izdelek;

public interface IzdelkiREST {

	
	public Response vrniIzdelke(int offset, int limit, String order, String where) throws NeustreznoPorociloException;
	
	
	public Response vrniIzdelek(int id) throws IzdelekNotFoundException;


	public Response ustvariIzdelek(Izdelek i) throws IzdelekNotFoundException;


	public Response spremeniIzdelek(Izdelek i, int id) throws IzdelekNotFoundException;


	public Response odstraniIzdelek(int id);
	
	
}
