package si.fri.prpo.zrna;

import javax.annotation.PostConstruct;
import javax.annotation.security.PermitAll;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import si.fri.prpo.vmesniki.StetjeZahtevkovSBLocal;
import si.fri.prpo.vmesniki.StetjeZahtevkovSBRemote;

/**
 * Session Bean implementation class StetjeZahtevkovSB
 */
@Singleton
@Remote(StetjeZahtevkovSBRemote.class)
@Local(StetjeZahtevkovSBLocal.class)
@PermitAll
@Startup
public class StetjeZahtevkovSB implements StetjeZahtevkovSBRemote, StetjeZahtevkovSBLocal {

	private int stevec;
	
    /**
     * Default constructor. 
     */
    public StetjeZahtevkovSB() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    @PostConstruct
    public void inicializirajStevec() {
    	stevec = 0;
    }
    
	@Override
	public void povecajInIzpisiZahtevek() {
		stevec++;
		System.out.println(stevec);
	}
	
	

}
