package si.fri.prpo.zrna;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import si.fri.prpo.izjeme.NeustreznoPorociloException;
import si.fri.prpo.izjeme.NiNaZalogiException;
import si.fri.prpo.spletna_trgovina_jpa.entitete.Izdelek;
import si.fri.prpo.spletna_trgovina_jpa.entitete.Narocilo;
import si.fri.prpo.spletna_trgovina_jpa.entitete.Obvestila;
import si.fri.prpo.spletna_trgovina_jpa.entitete.Racun;
import si.fri.prpo.vmesniki.SpremljanjeIzvajanjaZrnoLocal;
import si.fri.prpo.vmesniki.UpravljalecObvestilSBLocal;
import si.fri.prpo.vmesniki.UpravljalecObvestilSBRemote;

/**
 * Session Bean implementation class UpravljalecObvestilSB
 */
@Stateless
@Remote(UpravljalecObvestilSBRemote.class)
@Local(UpravljalecObvestilSBLocal.class)
@PermitAll
public class UpravljalecObvestilSB implements UpravljalecObvestilSBRemote, UpravljalecObvestilSBLocal {

	@EJB
	private SpremljanjeIzvajanjaZrnoLocal spremljanjeIzvajanjaZrno;
	
	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public UpravljalecObvestilSB() {
        // TODO Auto-generated constructor stub
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
//    @RolesAllowed({"Uporabnik", "Administrator"})
    public String vrniHTMLRacun(List<Object[]> izdelkiInKolicina) throws NeustreznoPorociloException {
//    	izdelkiInKolicina = null;
    	if(izdelkiInKolicina == null || izdelkiInKolicina.size() == 0) {
			throw new NeustreznoPorociloException("Racun v HTML obliki ni bil generiran ker ni podanih izdelkov.");
    	}
    	
    	double cenaSkupaj = 0;
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append("<h2>Racun<h2>\n");
    	sb.append("<h4>Ben&Naum Spletna Trgovina</h4>\n");
    	sb.append("<span>Artikel</span>\n");
    	sb.append("<span style='padding-left:10';>Kolicina</span>\n");
    	sb.append("<span style='padding-left:10';>Cena izdelka</span>\n");
    	sb.append("<span style='padding-left:10';>Cena skupaj</span>\n");
    	sb.append("<ul style='list-style-type:none; padding-left:0;'>\n");
    	//izdelki, kolicina in cena posameznega izdelka
    	for (Object[] izdelekInKolicina : izdelkiInKolicina) {
            Izdelek izd = (Izdelek)izdelekInKolicina[0];
           	int kol = (Integer)izdelekInKolicina[1];
           	cenaSkupaj += kol*izd.getCena();
           	
           	sb.append("<li>\n");
           	sb.append("<span>" + izd.getIme() + "</span>\n");
           	sb.append("<span style='padding-left:40;>" + kol + "</span>\n");
           	sb.append("<span style='padding-left:40;>" + String.format("%1$,.2f", izd.getCena()) + "</span>\n");
           	sb.append("<span style='padding-left:40;>" + String.format("%1$,.2f", kol * izd.getCena()) + "</span>\n");
           	sb.append("</li>\n");
    	}
    	sb.append("</ul>\n");
    	sb.append("<p>-------------------------------------------------</p>\n");
    	sb.append("<span style='padding-left:210;'>" + String.format("%1$,.2f", cenaSkupaj) + "</span>\n");
    	
//    	if (true){
//    		throw new RuntimeException();
//    	}
    	
    	return sb.toString();
    }

	@Override
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
//	@RolesAllowed({"Uporabnik", "Administrator"})
	public Racun narediRacun(List<Object[]> izdelkiInKolicina, String nacinPlacila) throws NiNaZalogiException {
		spremljanjeIzvajanjaZrno.napredekIzvajanja("Zacetek kreiranja racuna za narocilo");
		Racun racun = new Racun();
		double cenaSkupaj = 0;
		for (Object[] izdelekInKolicina : izdelkiInKolicina) {
            Izdelek izd = (Izdelek)izdelekInKolicina[0];
           	int kol = (Integer)izdelekInKolicina[1];
           	cenaSkupaj += kol * izd.getCena();
           	if(izd.getZaloga() - kol >= 0){
           		izd.setZaloga(izd.getZaloga() - kol);
           	} else {
           		throw new NiNaZalogiException("En od izdelkov narocila ni na zalogi.");
           	}
           	em.merge(izd);
		}
		
//    	if (true){
//    		throw new RuntimeException();
//    	}
		
		racun.setCena(cenaSkupaj);
    	racun.setNacin_placila(nacinPlacila);
    	em.persist(racun);
    	spremljanjeIzvajanjaZrno.napredekIzvajanja("Racun (id=" + racun.getId() + ") je kreiran");
    	return racun;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Obvestila vrniObvestilo(int id) {
	    return em.find(Obvestila.class, id);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void shraniObvestilo(Obvestila obv) {
    	try{
       		em.persist(obv);
       	} catch (EntityExistsException e) {
       		System.err.println("Obvestilo ze obstaja!");
       	} catch (Exception e) {
       		System.out.println(e.getMessage());
       		System.err.println("Zgodila se je napaka pri vstavljanju obvestila.");
        }
	}
}
