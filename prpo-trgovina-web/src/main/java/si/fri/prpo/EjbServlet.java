package si.fri.prpo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import si.fri.prpo.fasade.UpravljalecNarocniskihRazmerijSBInterface;
import si.fri.prpo.spletna_trgovina_jpa.entitete.Narocilo;
import si.fri.prpo.spletna_trgovina_jpa.entitete.NarociloIzdelek;
import si.fri.prpo.spletna_trgovina_jpa.entitete.Racun;
import si.fri.prpo.spletna_trgovina_jpa.entitete.Uporabnik;
import si.fri.prpo.fasade.UpravljalecNarocniskihRazmerijSBLocal;
import si.fri.prpo.vmesniki.UpravljalecObvestilSBLocal;
import si.fri.prpo.vmesniki.Izdelek.UpravljalecIzdelkovSBLocal;
import si.fri.prpo.vmesniki.Narocilo.UpravljalecNarocilSBLocal;
import si.fri.prpo.vmesniki.Narocilo.UpravljalecNarociloIzdelekSBLocal;
import si.fri.prpo.vmesniki.Uporabnik.UpravljalecUporabnikovSBLocal;

/**
 * Servlet implementation class EjbServlet
 */
public class EjbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UpravljalecNarocniskihRazmerijSBLocal narocniskaRazmerjaZrno;
       
//	@EJB
//	private UpravljalecUporabnikovSBLocal uporabnikZrno;
//	
//	@EJB
//	private UpravljalecNarocilSBLocal narociloZrno;
//	
//	@EJB
//	private UpravljalecObvestilSBLocal obvestilaZrno;
//	
//	@EJB
//	private UpravljalecIzdelkovSBLocal izdelkeZrno;
//	
//	@EJB
//	private UpravljalecNarociloIzdelekSBLocal narociloIzdelekZrno;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EjbServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.append("Served at: ").append(request.getContextPath());
		out.append("\n\n\n");
		
		List<Integer> idIzdelkov = new ArrayList<Integer>();
		idIzdelkov.add(1);
		idIzdelkov.add(2);
		
		List<Integer> kolicineIzdelkov = new ArrayList<Integer>();
		kolicineIzdelkov.add(2);
		kolicineIzdelkov.add(3);
		
		String racunHTML = "";
		try {
			racunHTML = narocniskaRazmerjaZrno.ustvariNarociloInVrniHTMLRacun(idIzdelkov, kolicineIzdelkov);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.append(racunHTML);
		out.append("\n\n\n");
	
		//dodaj uporabnika
//		Uporabnik up = new Uporabnik();
//		up.setIme("Benjamin");
//		up.setPriimek("Novak");
//		up.setGeslo("benovpassword");
//		up.setE_mail("ben@novak.com");
//		up.setUporabnisko_ime("ben");
//		uporabnikZrno.dodajNovegaUporabnika(up);
		
		//izpis vseh uporabnikov v sortiranem vrsnem redu
//		List<Uporabnik> userList = uporabnikZrno.vrniVseUporabnike();
//		for (Uporabnik u : userList) {
//            out.append("ID: " + u.getId() + ", ime: " + u.getIme() + ", priimek: " + u.getPriimek() 
//            + ", uporabnisko ime: " + u.getUporabnisko_ime() + "\n");
//        }
//		out.append("\n\n\n");
		
		
		//dodaj narocilo
//		Narocilo n = new Narocilo();
//		n.setLokacija_Dostave("Murska Sobota");
//		n.setNacin_prevzema("Osebno");
//		n.setUporabnik(uporabnikZrno.vrniUporabnika(1));
//		narociloZrno.shraniNarocilo(n);
		
		//izpis narocila
//		Narocilo nar2 = narociloZrno.vrniNarocilo(n.getId());
//		out.append("ID: " + nar2.getId() + ", lokacija dostave: " + nar2.getLokacija_Dostave() 
//					+ ", nacin prevzema: " + nar2.getNacin_prevzema() + "\n\n\n");
		
		
		//vstavi 2 izdelka v narocilu
//		NarociloIzdelek nIzd = new NarociloIzdelek();
//		nIzd.setNarocilo(n);
//		nIzd.setIzdelek(izdelkeZrno.vrniIzdelek(1));
//		nIzd.setKolicina(1);
//		narociloIzdelekZrno.vstaviIzdelekVNarocilu(nIzd);
		
//		NarociloIzdelek nIzd2 = new NarociloIzdelek();
//		nIzd2.setNarocilo(n);
//		nIzd2.setIzdelek(izdelkeZrno.vrniIzdelek(2));
//		nIzd2.setKolicina(2);
//		narociloIzdelekZrno.vstaviIzdelekVNarocilu(nIzd2);
		
		//sestavi in izpisi racun
//		List<Object[]> izdelkiInKolicina = narociloZrno.pridobiIzdelkeNarocila(n.getId());
//		Racun racun = obvestilaZrno.narediRacun(izdelkiInKolicina, "Gotovina");
//		n.setRacun(racun);
//		narociloZrno.spremeniNarocilo(n);
//		String racunHTML = obvestilaZrno.vrniHTMLRacun(izdelkiInKolicina);
//		out.append(racunHTML);
//		System.out.println(racunHTML);
		
//		out.append("\n\n\n");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
