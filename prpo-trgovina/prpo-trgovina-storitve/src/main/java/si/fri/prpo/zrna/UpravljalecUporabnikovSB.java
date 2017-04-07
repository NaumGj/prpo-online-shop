package si.fri.prpo.zrna;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import si.fri.prpo.spletna_trgovina_jpa.entitete.Uporabnik;
import si.fri.prpo.vmesniki.Uporabnik.UpravljalecUporabnikovSBLocal;
import si.fri.prpo.vmesniki.Uporabnik.UpravljalecUporabnikovSBRemote;

/**
 * Session Bean implementation class UpravljalecUporabnikovSB
 */

@Stateless
@Remote(UpravljalecUporabnikovSBRemote.class)
@Local(UpravljalecUporabnikovSBLocal.class)
@PermitAll
public class UpravljalecUporabnikovSB implements UpravljalecUporabnikovSBRemote, UpravljalecUporabnikovSBLocal {

	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public UpravljalecUporabnikovSB() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
//    @PermitAll
    public void dodajNovegaUporabnika(Uporabnik u) {
//    	if(em.find(Uporabnik.class, u.getId()) == null)
    	try{
    		em.persist(u);
    	} catch (EntityExistsException e) { // ce pademo tukaj, smo ujeli da uporabnik ze obstaja
    		System.err.println("Uporabnik ze obstaja!");
    	} catch (Exception e) {
    		System.err.println("Zgodila se je napaka pri vstavljanju uporabnika.");
    	}
    }
    
    @SuppressWarnings("unchecked")
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
//    @RolesAllowed({"Uporabnik", "Administrator"})
    public List<Uporabnik> vrniVseUporabnike() {
    	javax.persistence.Query query = em.createNamedQuery("vsiUporabniki");
    	return (List<Uporabnik>)query.getResultList();
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
//    @RolesAllowed({"Uporabnik", "Administrator"})
    public Uporabnik vrniUporabnika(int id) {
		return em.find(Uporabnik.class, id);
	}

}
