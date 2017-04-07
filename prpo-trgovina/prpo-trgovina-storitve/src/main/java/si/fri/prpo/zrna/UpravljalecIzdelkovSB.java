package si.fri.prpo.zrna;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import si.fri.prpo.izjeme.IzdelekNotFoundException;
import si.fri.prpo.spletna_trgovina_jpa.entitete.Izdelek;
import si.fri.prpo.vmesniki.Izdelek.UpravljalecIzdelkovSBLocal;
import si.fri.prpo.vmesniki.Izdelek.UpravljalecIzdelkovSBRemote;

/**
 * Session Bean implementation class UpravljalecIzdelkovSB
 */
@Stateless
@Remote(UpravljalecIzdelkovSBRemote.class)
@Local(UpravljalecIzdelkovSBLocal.class)
@PermitAll
public class UpravljalecIzdelkovSB implements UpravljalecIzdelkovSBRemote, UpravljalecIzdelkovSBLocal {

	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public UpravljalecIzdelkovSB() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Izdelek vrniIzdelek(int id) throws IzdelekNotFoundException {
    	Izdelek izd = em.find(Izdelek.class, id);
    	if(izd != null) {
    		return izd;
    	} else {
    		throw new IzdelekNotFoundException("Izdelek z id-jem " + id + " ni bil najden.");
    	}
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public List<Izdelek> vrniIzdelke() {
    	javax.persistence.Query query2 = em.createNamedQuery("Izdelek.findAll");
		return (List<Izdelek>)query2.getResultList();
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void spremeniIzdelek(Izdelek iz, int id) {
    	Izdelek i = em.find(Izdelek.class, id);
    	i.setIme(iz.getIme());
    	i.setKategorija(iz.getKategorija());
    	i.setCena(iz.getCena());
    	i.setDatum(iz.getDatum());
    	i.setSlika(iz.getSlika());
    	i.setDavek(iz.getDavek());
    	i.setOpis(iz.getOpis());
    	i.setZaloga(iz.getZaloga());
    	if(iz.getOpis() != null){
    		i.setOpis(iz.getOpis());;
    	}
    	if(iz.getSlika() != null){
    		i.setSlika(iz.getSlika());;
    	}
    	em.merge(i);
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void shraniIzdelek(Izdelek n) {
    	try{
       		em.persist(n);
       	} catch (EntityExistsException e) {
       		System.err.println("Izdelek ze obstaja!");
       	} catch (Exception e) {
       		System.out.println(e.getMessage());
       		System.err.println("Zgodila se je napaka pri vstavljanju izdelka.");
        }
    }
    
    @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void odstraniIzdelek(int id){
    	Izdelek n = (Izdelek)em.find(Izdelek.class, id);
    	em.remove(n);
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Izdelek> vrniOstranjeneIzdelke(int offset, int limit, String order, boolean ascending, String[] whereParams) {
//		javax.persistence.Query q = em.createNamedQuery("Izdelek.getOrdered");
//        q.setMaxResults(limit);
//        q.setFirstResult(offset);
//		return (List<Izdelek>)q.getResultList();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Izdelek> cq = cb.createQuery(Izdelek.class);
        Root<Izdelek> izdelek = cq.from(Izdelek.class);
        if(whereParams != null){
        	List<Predicate> predicates = new ArrayList<Predicate>();
        	for(int i = 0; i < whereParams.length; i++) {
        		String[] condParams = whereParams[i].split(":");
        		ArrayList<String> condAst = new ArrayList<String>();
        		condAst.add(condParams[0]);
        		condAst.add(condParams.length == 2 ? "eq" : condParams[1]);
        		condAst.add(condParams.length == 2 ? condParams[1] : condParams[2]);
        		if("eq".equals(condAst.get(1))) {
        			predicates.add(cb.equal(izdelek.get(condAst.get(0)), condAst.get(2)));
        		} else if("lte".equals(condAst.get(1))) {
        			if("datum".equals(condAst.get(0).toLowerCase())) {
        				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        			    Date parsedDate = null;
						try {
							parsedDate = dateFormat.parse(condAst.get(2));
//	        			    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
							predicates.add(cb.lessThan(izdelek.get(condAst.get(0)), parsedDate));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        			} else {
        				predicates.add(cb.lessThan(izdelek.get(condAst.get(0)), condAst.get(2)));
        			}
        		} else if("gte".equals(condAst.get(1))) {
        			if("datum".equals(condAst.get(0).toLowerCase())) {
        				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        			    Date parsedDate = null;
						try {
							parsedDate = dateFormat.parse(condAst.get(2));
//	        			    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
							predicates.add(cb.greaterThan(izdelek.get(condAst.get(0)), parsedDate));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        			} else {
        				predicates.add(cb.greaterThan(izdelek.get(condAst.get(0)), condAst.get(2)));
        			}
        		}
        	}
        	cq.select(izdelek).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
//        	cq = cq.select(izdelek).where(cb.equal(izdelek.get("kategorija"), "Tehnika"));
//        	cq = cq.where(cb.greaterThan(izdelek.get("zaloga"), 31));
        } else {
        	cq.select(izdelek);
        }
        if(ascending) {
        	cq.orderBy(cb.asc(izdelek.get(order)));
        } else {
        	cq.orderBy(cb.desc(izdelek.get(order)));
        }
        javax.persistence.Query query = em.createQuery(cq);
        query.setMaxResults(limit);
        query.setFirstResult(offset);
        return (List<Izdelek>)query.getResultList();
       
	}

	@Override
	public long prestejIzdelke() {
		javax.persistence.Query q = em.createNamedQuery("Izdelek.prestejIzdelke");
		return (Long)q.getSingleResult();
	}

}
