package si.fri.prpo.zrna;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import si.fri.prpo.spletna_trgovina_jpa.entitete.Narocilo;
import si.fri.prpo.spletna_trgovina_jpa.entitete.Racun;
import si.fri.prpo.vmesniki.Narocilo.UpravljalecNarocilSBLocal;
import si.fri.prpo.vmesniki.Narocilo.UpravljalecNarocilSBRemote;

/**
 * Session Bean implementation class UpravljalecNarocilSB
 */
@Stateless
@Remote(UpravljalecNarocilSBRemote.class)
@Local(UpravljalecNarocilSBLocal.class)
@PermitAll
public class UpravljalecNarocilSB implements UpravljalecNarocilSBRemote, UpravljalecNarocilSBLocal {

	
	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public UpravljalecNarocilSB() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
//    @RolesAllowed({"Uporabnik", "Administrator"})
    public void shraniNarocilo(Narocilo n) {
    	try{
//    		System.out.println(n.getId());
       		em.persist(n);
       	} catch (EntityExistsException e) {
       		System.err.println("Narocilo ze obstaja!");
       	} catch (Exception e) {
       		System.out.println(e.getMessage());
       		System.err.println("Zgodila se je napaka pri vstavljanju narocila.");
        }
    }
    
    public List<Narocilo> vrniVsaNarocila() {
    	javax.persistence.Query query = em.createNamedQuery("Narocilo.findAll");
    	return (List<Narocilo>)query.getResultList();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
//    @RolesAllowed({"Uporabnik", "Administrator"})
    public void spremeniNarocilo(Narocilo nar, int id) {
    	Narocilo n = em.find(Narocilo.class, id);
    	n.setLokacija_Dostave(nar.getLokacija_Dostave());
    	n.setNacin_prevzema(nar.getNacin_prevzema());
    	if(n.getRacun() != null){
    		n.setRacun(n.getRacun());
    	}
    	if(n.getUporabnik() != null){
    		n.setUporabnik(n.getUporabnik());
    	}
    	if(n.getNarociloIzdeleks() != null){
    		n.setNarociloIzdeleks(n.getNarociloIzdeleks());
    	}
    	em.merge(n);
    }
    
    @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void odstraniNarocilo(int id){
    	Narocilo n = (Narocilo)em.find(Narocilo.class, id);
    	em.remove(n);
    }
    
	@SuppressWarnings("unchecked")
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
//	@RolesAllowed({"Uporabnik", "Administrator"})
	public List<Object[]> pridobiIzdelkeNarocila(int id) {
		javax.persistence.Query query = em.createNamedQuery("Narocilo.pridobiIzdelkeNarocila");
		query.setParameter("id", id);
    	return (List<Object[]>)query.getResultList();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
//	@RolesAllowed({"Uporabnik", "Administrator"})
	public void prirediRacunNarocilu(Narocilo n, Racun r) {
		n.setRacun(r);
		em.merge(n);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Racun pridobiRacunNarocila(int id) {
		javax.persistence.Query query = em.createNamedQuery("Narocilo.pridobiRacunNarocila");
		query.setParameter("id", id);
    	return (Racun)query.getSingleResult();
	}

	@Override
	public Narocilo vrniNarocilo(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
