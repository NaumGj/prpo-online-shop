package si.fri.prpo.zrna;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import si.fri.prpo.spletna_trgovina_jpa.entitete.Izdelek;
import si.fri.prpo.spletna_trgovina_jpa.entitete.NakupovalniList;
import si.fri.prpo.spletna_trgovina_jpa.entitete.NakupovalniListIzdelek;
import si.fri.prpo.genericni.IzdelkiInKolicine;
import si.fri.prpo.vmesniki.NakupovalniList.UpravljalecNakupovalniListIzdelekSBLocal;
import si.fri.prpo.vmesniki.NakupovalniList.UpravljalecNakupovalniListIzdelekSBRemote;

/**
 * Session Bean implementation class UpravljalecNakupovalniListIzdelekSB
 */
@Stateless
@Local(UpravljalecNakupovalniListIzdelekSBLocal.class)
@Remote(UpravljalecNakupovalniListIzdelekSBRemote.class)
public class UpravljalecNakupovalniListIzdelekSB implements UpravljalecNakupovalniListIzdelekSBRemote, UpravljalecNakupovalniListIzdelekSBLocal {

	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public UpravljalecNakupovalniListIzdelekSB() {
        // TODO Auto-generated constructor stub
    }

    @Override
    @PermitAll
    /***ustvari objekt NakupovalniListIzdelek - doda Izdelek v nakupovalni list**/
    public void ustvariNakupovalniListIzdelek(int idIzd, int kolicina, int idList) {
    	NakupovalniList nl = em.find(NakupovalniList.class, idList);
    	Izdelek i = em.find(Izdelek.class, idIzd);
    	
    	boolean update = true;
		try{
			javax.persistence.Query query = em.createNamedQuery("NakupovalniListIzdelka");
			query.setParameter("izdelek", i);
			query.setParameter("nakupovalniList", nl);
			NakupovalniListIzdelek unl = (NakupovalniListIzdelek) query.getSingleResult();
			unl.setKolicina(kolicina);
			em.merge(unl);
	    } catch(NoResultException e) {
	    	update = false;
	    }
		
    	if(nl != null && i != null && !update) {
    		NakupovalniListIzdelek nli = new NakupovalniListIzdelek();
    		nli.setIzdelek(i);
    		nli.setKolicina(kolicina);
    		nli.setNakupovalniList(nl);
    		em.persist(nli);
    	}
    	
    }
    
    @Override
    @PermitAll
    /***ustvari objekte NakupovalniListIzdelek - doda Izdeleke v nakupovalni list, glede na kolicino**/
    public void ustvariNakupovalniListZaIzdelke(IzdelkiInKolicine ik, int idList) {
    	List<Integer> idIzdelkov = ik.getIdIzdelkov();
		List<Integer> kolicine = ik.getKolicine();
		for(int i = 0; i < idIzdelkov.size(); i++) {
			ustvariNakupovalniListIzdelek(idIzdelkov.get(i), kolicine.get(i), idList);
		}
    }
    
    @Override
    @PermitAll
    /***izbrisi vse**/
    public void zbrisiVse(int idList) {
    	NakupovalniList nl =  em.find(NakupovalniList.class, idList);
    	try{
    		javax.persistence.Query query = em.createNamedQuery("vseOdNakupovalnegaLista");
    		query.setParameter("nakupovalniList", nl);
    		List<NakupovalniListIzdelek> unl = (List<NakupovalniListIzdelek>)query.getResultList();
    		for(NakupovalniListIzdelek n: unl) {
    			em.remove(n);
            	em.flush();
    		}
		} catch(NoResultException e) {
			System.out.println("Nobenega izdeleka ni v kosarici.");
	    }
    }
    
    @Override
    @PermitAll
    /***izbrise objekt NakupovalniListIzdelek po id-ju - izbrise izdelek iz nakupovalnega lista**/
    public void zbrisiNakupovalniListIzdelek(int idList, int idIzd) {
    	NakupovalniList nl =  em.find(NakupovalniList.class, idList);
    	Izdelek i = em.find(Izdelek.class, idIzd);
    	if(nl != null && i != null) {
    		try{
	    		javax.persistence.Query query = em.createNamedQuery("NakupovalniListIzdelka");
	    		query.setParameter("izdelek", i);
	    		query.setParameter("nakupovalniList", nl);
	    		NakupovalniListIzdelek unl = (NakupovalniListIzdelek) query.getSingleResult();
	        	em.remove(unl);
	        	em.flush();
    		} catch(NoResultException e) {
    			System.out.println("Izdeleka ni v kosarici. ");
    	    }
    	}
    }
    
    @Override
    @PermitAll
    /***Vrne vse izdelke iz nakupovalnega lista, ki ima NakupovalniList.id = id**/
    public List<Object[]> vrniIzdelkeNakupovalnegaLista(int id) {
    	javax.persistence.Query query = em.createNamedQuery("vsiIzdelkiNakupovalnegaLista");
    	query.setParameter("nakupovalniList", em.find(NakupovalniList.class, id));
    	return (List<Object[]>) query.getResultList();
    }
    
}
