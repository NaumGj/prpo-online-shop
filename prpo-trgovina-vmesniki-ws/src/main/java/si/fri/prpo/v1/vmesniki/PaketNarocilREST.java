package si.fri.prpo.v1.vmesniki;

import java.util.List;

import javax.ws.rs.core.Response;
import si.fri.prpo.spletna_trgovina_jpa.entitete.*;

public interface PaketNarocilREST {

	public Response vstaviPaket(List<Narocilo> n);
	
}
