package si.fri.prpo.prestrezniki;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import si.fri.prpo.vmesniki.StetjeZahtevkovSBLocal;
import si.fri.prpo.zrna.StetjeZahtevkovSB;

public class StetjeZahtevkovPrestreznik {

	@EJB
	private StetjeZahtevkovSBLocal stetjeZahtevkovZrno;
	
	@AroundInvoke
	public Object prestreziZahtevek(InvocationContext kontekstProzenja){
		stetjeZahtevkovZrno.povecajInIzpisiZahtevek();
		try {
			return kontekstProzenja.proceed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
