package si.fri.prpo.v1.vmesniki;

import javax.ws.rs.core.Response;

import si.fri.prpo.genericni.IzdelkiInKolicine;
import si.fri.prpo.izjeme.IzdelekNotFoundException;
import si.fri.prpo.izjeme.NiNaZalogiException;

public interface NarocniskaRazmerjaREST {

	public Response ustvariNarocniskoRazmerje(IzdelkiInKolicine ik) throws NiNaZalogiException, IzdelekNotFoundException;

}
