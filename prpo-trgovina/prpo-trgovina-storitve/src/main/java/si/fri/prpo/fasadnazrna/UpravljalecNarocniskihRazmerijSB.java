package si.fri.prpo.fasadnazrna;

import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import si.fri.prpo.fasade.UpravljalecNarocniskihRazmerijSBLocal;
import si.fri.prpo.fasade.UpravljalecNarocniskihRazmerijSBRemote;
import si.fri.prpo.izjeme.IzdelekNotFoundException;
import si.fri.prpo.izjeme.NeustreznoPorociloException;
import si.fri.prpo.izjeme.NiNaZalogiException;
import si.fri.prpo.spletna_trgovina_jpa.entitete.Narocilo;
import si.fri.prpo.spletna_trgovina_jpa.entitete.NarociloIzdelek;
import si.fri.prpo.spletna_trgovina_jpa.entitete.Racun;
import si.fri.prpo.vmesniki.SpremljanjeIzvajanjaZrnoLocal;
import si.fri.prpo.vmesniki.UpravljalecObvestilSBLocal;
import si.fri.prpo.vmesniki.Izdelek.UpravljalecIzdelkovSBLocal;
import si.fri.prpo.vmesniki.Narocilo.UpravljalecNarocilSBLocal;
import si.fri.prpo.vmesniki.Narocilo.UpravljalecNarociloIzdelekSBLocal;
import si.fri.prpo.vmesniki.Uporabnik.UpravljalecUporabnikovSBLocal;

/**
 * Session Bean implementation class UpravljavecNarocniskihRazmerijSB
 */
@Stateless
@Remote(UpravljalecNarocniskihRazmerijSBRemote.class)
@Local(UpravljalecNarocniskihRazmerijSBLocal.class)
@DeclareRoles({"Uporabnik","Gost","Administrator"})
//@RunAs("Administrator")
@PermitAll
@TransactionAttribute(value=TransactionAttributeType.MANDATORY)
//@Interceptors({StetjeZahtevkovPrestreznik.class})
public class UpravljalecNarocniskihRazmerijSB implements UpravljalecNarocniskihRazmerijSBRemote, UpravljalecNarocniskihRazmerijSBLocal {

	@EJB
	private UpravljalecUporabnikovSBLocal uporabnikZrno;
	
	@EJB
	private UpravljalecNarocilSBLocal narociloZrno;
	
	@EJB
	private UpravljalecObvestilSBLocal obvestilaZrno;
	
	@EJB
	private UpravljalecIzdelkovSBLocal izdelkeZrno;
	
	@EJB
	private UpravljalecNarociloIzdelekSBLocal narociloIzdelekZrno;
	
	@EJB
	private SpremljanjeIzvajanjaZrnoLocal spremljanjeIzvajanjaZrno;
	
	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public UpravljalecNarocniskihRazmerijSB() {
        // TODO Auto-generated constructor stub
    }

	@Override
//	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
//	@PermitAll
	public String ustvariNarociloInVrniHTMLRacun(List<Integer> idIzdelkov, List<Integer> kolicineIzdelkov) throws IzdelekNotFoundException {
//		if(idIzdelkov.size() != kolicineIzdelkov.size()){
//			throw new NeustreznoPorociloException("Racun ne more biti generiran ker tabela z id-je izdelkov in njihove kolicine nimata iste dimenzije");
//		}
		//dodaj narocilo
		Narocilo n = new Narocilo();
		n.setLokacija_Dostave("Ljubljana");
		n.setNacin_prevzema("Osebno");
		n.setUporabnik(uporabnikZrno.vrniUporabnika(1));
		narociloZrno.shraniNarocilo(n);

		//vstavi izdelke v narocilu
		for(int i = 0; i < idIzdelkov.size(); i++) {
			NarociloIzdelek nIzd = new NarociloIzdelek();
			nIzd.setNarocilo(n);
			nIzd.setIzdelek(izdelkeZrno.vrniIzdelek(idIzdelkov.get(i)));
			nIzd.setKolicina(kolicineIzdelkov.get(i));
			narociloIzdelekZrno.vstaviIzdelekVNarocilu(nIzd);
		}

		// naredi racun in ga vrni kot HTML
		List<Object[]> izdelkiInKolicina = narociloZrno.pridobiIzdelkeNarocila(n.getId());
		Racun racun = null;
		try{
			racun = obvestilaZrno.narediRacun(izdelkiInKolicina, "Gotovina");
		} catch (NiNaZalogiException e) {
			System.err.println(e.getMessage());
		}
		narociloZrno.prirediRacunNarocilu(n, racun);
		String racunHTML = "";
		try {
			racunHTML = obvestilaZrno.vrniHTMLRacun(izdelkiInKolicina);
		} catch (NeustreznoPorociloException e) {
			System.err.println(e.getMessage());
		}
		System.out.println(racunHTML);
		
		return racunHTML;
	}

	@Override
//	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public int ustvariNarociloInPripadajociRacun(List<Integer> idIzdelkov, List<Integer> kolicine) throws NiNaZalogiException, IzdelekNotFoundException {
		//dodaj narocilo
		Narocilo n = new Narocilo();
		n.setLokacija_Dostave("Ljubljana");
		n.setNacin_prevzema("Osebno");
		n.setUporabnik(uporabnikZrno.vrniUporabnika(1));
		narociloZrno.shraniNarocilo(n);

		//vstavi izdelke v narocilu
		for(int i = 0; i < idIzdelkov.size(); i++) {
			NarociloIzdelek nIzd = new NarociloIzdelek();
			nIzd.setNarocilo(n);
			nIzd.setIzdelek(izdelkeZrno.vrniIzdelek(idIzdelkov.get(i)));
			nIzd.setKolicina(kolicine.get(i));
//			System.out.println("OVDE SUM");
//			System.out.println(narociloIzdelekZrno);
//			System.out.println(n);
			narociloIzdelekZrno.vstaviIzdelekVNarocilu(nIzd);
		}

		// naredi racun
		List<Object[]> izdelkiInKolicina = narociloZrno.pridobiIzdelkeNarocila(n.getId());
		Racun racun = obvestilaZrno.narediRacun(izdelkiInKolicina, "Gotovina");
//		try{
//			racun = obvestilaZrno.narediRacun(izdelkiInKolicina, "Gotovina");
//		} catch (NiNaZalogiException e) {
//			System.out.println("OK OVDE SUM");
//			throw e;
//		}
		narociloZrno.prirediRacunNarocilu(n, racun);

		return n.getId();
	}

	@Override
	@TransactionAttribute(value=TransactionAttributeType.MANDATORY)
	public void vstaviNarociloInGenerirajRacun(Narocilo n) throws NiNaZalogiException, IzdelekNotFoundException {
		//dodaj narocilo
		narociloZrno.shraniNarocilo(n);
		spremljanjeIzvajanjaZrno.napredekIzvajanja("Narocilo (id=" + n.getId() + ") je obdelano.");

		//vstavi izdelke v narocilu
		int idIzdelka = 1;
		int kolicinaIzdelka = 2;
		NarociloIzdelek nIzd = new NarociloIzdelek();
		nIzd.setNarocilo(n);
		nIzd.setIzdelek(izdelkeZrno.vrniIzdelek(idIzdelka));
		nIzd.setKolicina(kolicinaIzdelka);
		narociloIzdelekZrno.vstaviIzdelekVNarocilu(nIzd);
		spremljanjeIzvajanjaZrno.napredekIzvajanja("Izdelki v narocilo(id=" + n.getId() + ") so vstavljeni.");

		// naredi racun
		List<Object[]> izdelkiInKolicina = narociloZrno.pridobiIzdelkeNarocila(n.getId());
		Racun racun = obvestilaZrno.narediRacun(izdelkiInKolicina, "Kartica");
		narociloZrno.prirediRacunNarocilu(n, racun);
	}

}
