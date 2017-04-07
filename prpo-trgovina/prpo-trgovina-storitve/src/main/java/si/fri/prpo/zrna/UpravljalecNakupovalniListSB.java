package si.fri.prpo.zrna;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import si.fri.prpo.spletna_trgovina_jpa.entitete.NakupovalniList;
import si.fri.prpo.vmesniki.NakupovalniList.UpravljalecNakupovalniListLocal;
import si.fri.prpo.vmesniki.NakupovalniList.UpravljalecNakupovalniListRemote;

/**
 * Session Bean implementation class UpravljalecNakupovalniList
 */
@Stateless
@Local(UpravljalecNakupovalniListLocal.class)
@Remote(UpravljalecNakupovalniListRemote.class)
public class UpravljalecNakupovalniListSB implements UpravljalecNakupovalniListRemote, UpravljalecNakupovalniListLocal {

	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public UpravljalecNakupovalniListSB() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    @PermitAll
    /***vrne nakupovalni list kot objekt**/
    public NakupovalniList vrniNakupovalniList(int id) {
    	return em.find(NakupovalniList.class, id);
    }
    
    @Override
    @PermitAll
	public void zbrisiNakupovalniList(int id) {
    	try{
    		NakupovalniList n = em.find(NakupovalniList.class, id);
    		em.remove(n);
		} catch(NoResultException e) {
			System.out.println("Ne obstaja.");
	    }
	}	 

	@Override
    @PermitAll
	public NakupovalniList dodajNovNakupovalniList(NakupovalniList n) {
		try{
    		em.persist(n);
    	} catch (Exception e) {
    		System.err.println("Zgodila se je napaka pri vstavljanju.");
    		return null;
    	}
    	return n;
	}
	
}
