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

import si.fri.prpo.spletna_trgovina_jpa.entitete.NarociloIzdelek;
import si.fri.prpo.vmesniki.Narocilo.UpravljalecNarociloIzdelekSBLocal;
import si.fri.prpo.vmesniki.Narocilo.UpravljalecNarociloIzdelekSBRemote;

/**
 * Session Bean implementation class UpravljalecNarociloIzdelekSB
 */
@Stateless
@Remote(UpravljalecNarociloIzdelekSBRemote.class)
@Local(UpravljalecNarociloIzdelekSBLocal.class)
@PermitAll
public class UpravljalecNarociloIzdelekSB implements UpravljalecNarociloIzdelekSBLocal, UpravljalecNarociloIzdelekSBRemote {
	
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public UpravljalecNarociloIzdelekSB() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
//    @RolesAllowed({"Uporabnik", "Administrator"})
    public void vstaviIzdelekVNarocilu(NarociloIzdelek nIzd){
    	try{
       		em.persist(nIzd);
       	} catch (EntityExistsException e) {
       		System.err.println("Izdelek je ze vstavljen v narocilu!");
       	} catch (Exception e) {
       		System.err.println("Zgodila se je napaka pri vstavljanju izdelka v narocilo.");
        }
    }

//	@Override
//	public void vrniIzdelkeNarocila(int id) {
//		javax.persistence.Query query = em.createNamedQuery("vsiUporabniki");
//    	return (List<Izdelek>)query.getResultList();
//		
//	}
    
    
}
