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
import si.fri.prpo.spletna_trgovina_jpa.entitete.Uporabnik;
import si.fri.prpo.spletna_trgovina_jpa.entitete.UporabnikNakupovalniList;
import si.fri.prpo.vmesniki.NakupovalniList.UpravljalecNakupovalniListUporabnikaSBLocal;
import si.fri.prpo.vmesniki.NakupovalniList.UpravljalecNakupovalniListUporabnikaSBRemote;

/**
 * Session Bean implementation class UpravljalecNakupovalniListUporabnikaSB
 */
@Local(UpravljalecNakupovalniListUporabnikaSBLocal.class)
@Remote(UpravljalecNakupovalniListUporabnikaSBRemote.class)
@Stateless
public class UpravljalecNakupovalniListUporabnikaSB implements UpravljalecNakupovalniListUporabnikaSBRemote, UpravljalecNakupovalniListUporabnikaSBLocal {

	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public UpravljalecNakupovalniListUporabnikaSB() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    @PermitAll
    /***ustvari objekt NakupovalniListUporabnika - doda Uporabnika v nakupovalni list**/
    public void ustvariNakupovalniListUporabnika(int idU, int idList) {
    	NakupovalniList nl =  em.find(NakupovalniList.class, idList);
    	Uporabnik u = em.find(Uporabnik.class, idU);
    	
    	boolean exist = true;
		try{
			javax.persistence.Query query = em.createNamedQuery("NakupovalniListUporabnika");
			query.setParameter("uporabnik", u);
			query.setParameter("nakupovalniList", nl);
			query.getSingleResult();
	    } catch(NoResultException e) {
	    	exist = false;
	    }
    	
    	if(nl != null && u != null && !exist) {
    		UporabnikNakupovalniList unl = new UporabnikNakupovalniList();
    		unl.setUporabnik(u);
    		unl.setNakupovalniList(nl);
    		em.persist(unl);
    	}
    }
	
    @Override
    @PermitAll
    /***izbrise objekt NakupovalniListUporabnika - izbrise Uporabnika iz nakupovalnega lista**/
	public void zbrisiNakupovalniListUporabnika(int idU, int idList) {
    	NakupovalniList nl =  em.find(NakupovalniList.class, idList);
    	Uporabnik u = em.find(Uporabnik.class, idU);
    	if(nl != null && u != null) {
    		try{
	    		javax.persistence.Query query = em.createNamedQuery("NakupovalniListUporabnika");
	    		query.setParameter("uporabnik", u);
	    		query.setParameter("nakupovalniList", nl);
	    		UporabnikNakupovalniList unl = (UporabnikNakupovalniList) query.getSingleResult();
	        	em.remove(unl);
	        	em.flush();
    		} catch(NoResultException e) {
    	    	System.out.println("Uporabnika ni v tej kosarici.");
    	    }
    	}
    }
    
    @Override
    @PermitAll
    /**zbrisi vse**/
    public void zbrisiVse(int idList) {
    	NakupovalniList nl =  em.find(NakupovalniList.class, idList);
    	try{
    		javax.persistence.Query query = em.createNamedQuery("VseOdNakupovalnegaLista");
    		query.setParameter("nakupovalniList", nl);
    		List<UporabnikNakupovalniList> unl = (List<UporabnikNakupovalniList>)query.getResultList();
    		for(UporabnikNakupovalniList n: unl) {
    			em.remove(n);
            	em.flush();
    		}
		} catch(NoResultException e) {
			System.out.println("Nobenega izdeleka ni v kosarici.");
	    } catch(Exception e) {
			e.printStackTrace();
	    }
    }
	
    @Override
    @PermitAll
    /***Vrne nakupovalne liste Uporabnika z Uporabnik.id = id**/
	public List<NakupovalniList> vrniNakupovalneListeUporabnika(int id) {
		javax.persistence.Query query = em.createNamedQuery("UporabnikoviNakupovalniListi");
		query.setParameter("uporabnik", em.find(Uporabnik.class, id));
		return (List<NakupovalniList>)query.getResultList();
	}
    
    @Override
    @PermitAll
    /***Vrne Uporabnike nakupovalnega lista z NakupovalniList.id = id**/
	public List<Uporabnik> vrniUporabnikeNakupovalnegaLista(int id) {
		javax.persistence.Query query = em.createNamedQuery("UporabnikiNakupovalnegaLista");
		query.setParameter("nakupovalniList", em.find(NakupovalniList.class, id));
		return (List<Uporabnik>)query.getResultList();
	}
    
}
