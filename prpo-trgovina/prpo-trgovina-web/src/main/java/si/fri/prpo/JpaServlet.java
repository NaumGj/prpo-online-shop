package si.fri.prpo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.management.Query;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import javax.persistence.EntityTransaction;

import si.fri.prpo.spletna_trgovina_jpa.entitete.Izdelek;
import si.fri.prpo.spletna_trgovina_jpa.entitete.Narocilo;
import si.fri.prpo.spletna_trgovina_jpa.entitete.Racun;
import si.fri.prpo.spletna_trgovina_jpa.entitete.Uporabnik;

/**
 * Servlet implementation class JpaServlet
 */

public class JpaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;
	
	@Resource
	private UserTransaction ut;
    
	/**
     * @see HttpServlet#HttpServlet()
     */
    public JpaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//    @TransactionAttribute(TransactionAttributeType.REQUIRED)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.append("Served at: ").append(request.getContextPath()).append("\n\n\n");
		try {
			// posodabljanje uporabnikov
			try{
				ut.begin();
				javax.persistence.Query query1 = em.createNamedQuery("Uporabnik.posodobiFiksnePriimekInEmail");
				query1.setParameter("ime", "Naum");
				query1.executeUpdate();
				ut.commit();
			} catch (Exception e){
				ut.rollback();
				e.printStackTrace();
			}

			int id = -1;
			// klicanje statične pozvedbe, ki vrne izdelke kategorije Tehnika
            javax.persistence.Query query2 = em.createNamedQuery("Izdelek.izberiVseIzdelekeKategorijeTehnika");
			List<Izdelek> izdelekiTehnika = (List<Izdelek>)query2.getResultList();
			out.append("Vse izdelke kategorije Tehnika: \n");
            for (Izdelek i : izdelekiTehnika) {
                out.append("ID: " + i.getId() + ", ime: " + i.getIme() + ", cena: " + i.getCena() 
                			+ ", kategorija: " + i.getKategorija() + "\n");
                id = i.getId();
            }
            out.append("\n\n\n");
            
            // izberi po id-ju
            javax.persistence.Query query3 = em.createNamedQuery("Izdelek.izberiIzdelekPoId");
            query3.setParameter("id", id);
			Izdelek izdelek = (Izdelek)query3.getSingleResult();
			out.append("Izberi izdelek po id " + id + ": \n");
			out.append("ID: " + izdelek.getId() + ", ime: " + izdelek.getIme() + ", cena: " + izdelek.getCena() 
						+ ", kategorija: " + izdelek.getKategorija() + "\n");
			out.append("\n\n\n");
			

			
			// brisanje izdeljka po id-ju (dodajanje transakcije)
			try{

				ut.begin();
	            javax.persistence.Query query4 = em.createNamedQuery("Izdelek.izbrisiIzdelekPoId");
	            out.append(String.format("Brisanje izdelka z id-jem: %d\n", id));
	            query4.setParameter("id", id);
	            query4.executeUpdate();
				ut.commit();
			} catch(Exception e) {
				ut.rollback();
				e.printStackTrace();
			}
			
            out.append("\n\n\n");
            
            // ostranjevanje (4 zapise na eno stran)
            int stZapisov = 4;
            int zacetek = 0;
            while(true){
	            javax.persistence.Query query5 = em.createNamedQuery("Izdelek.findAll");
	            query5.setMaxResults(stZapisov);
	            query5.setFirstResult(zacetek);
				List<Izdelek> vsiIzdelki = (List<Izdelek>)query5.getResultList();
				if(vsiIzdelki.isEmpty()){
					break;
				}
				out.append("Stran: " + ((zacetek / stZapisov) + 1) + "\n");
	            for (Izdelek i : vsiIzdelki) {
	                out.append("ID: " + i.getId() + ", ime: " + i.getIme() + ", cena: " + i.getCena() 
	                + ", kategorija: " + i.getKategorija() + "\n");
	                id = i.getId();
	            }
	            em.clear();
	            zacetek += vsiIzdelki.size();
            }
            out.append("\n\n\n");

            // ostranjevanje (izdelke druge strani)
            int stran = 2;
            out.append("Stran: " + stran + "\n");
            javax.persistence.Query query6 = em.createNamedQuery("Izdelek.findAll");
            query6.setMaxResults(stZapisov);
            query6.setFirstResult(stZapisov * (stran - 1));
			List<Izdelek> vsiIzdelki = (List<Izdelek>)query6.getResultList();
            for (Izdelek i : vsiIzdelki) {
                out.append("ID: " + i.getId() + ", ime: " + i.getIme() + ", cena: " + i.getCena() 
                + ", kategorija: " + i.getKategorija() + "\n");
                id = i.getId();
            }
            em.clear();
            out.append("\n\n\n");
            
            // pridobi narocila uporabnika z danim uporabniskim imenom
	        javax.persistence.Query query7 = em.createNamedQuery("Uporabnik.pridobiNarocilaUporabnika");
	        String uporabniskoIme = "naumgj";
	        query7.setParameter("uporabnisko_ime", uporabniskoIme);
	        List<Narocilo> narocila = (List<Narocilo>)query7.getResultList();
	        out.append("Pridobi narocila uporabnika z uporabniskim imenom " + uporabniskoIme + ": \n");
	        for (Narocilo n : narocila) {
	        	out.append("ID: " + n.getId() + ", lokacija dostave: " + n.getLokacija_Dostave() + ", nacin prevzema: " + n.getNacin_prevzema());
	        }
	        out.append("\n\n\n");
	        
			id = 1;
	        javax.persistence.Query query8 = em.createNamedQuery("Narocilo.pridobiIzdelkeNarocila");
	        query8.setParameter("id", id);
	        List<Object[]> izdelkiInKolicina = (List<Object[]>)query8.getResultList();
	        out.append("Pridobljene izdelke narocila z id-jem " + id + ": \n");
	        for (Object[] izdelekInKolicina : izdelkiInKolicina) {
	            Izdelek izd = (Izdelek)izdelekInKolicina[0];
	           	int kol = (Integer)izdelekInKolicina[1];
	           	out.append("Ime izdelka: " + izd.getIme() + ", cena: " + izd.getCena() 
	           				+ ", kategorija: " + izd.getKategorija() + ", Kolicina: " + kol + "\n");
	            }
            out.append("\n\n\n");
            
			id = 2;
	        javax.persistence.Query query9 = em.createNamedQuery("Narocilo.pridobiRacunNarocila");
	        query9.setParameter("id", id);
	        Racun racun = (Racun)query9.getSingleResult();
	        out.append("Racun narocila z id-jem " + id + ": \n");
	        out.append("Znesek: " + racun.getCena() + ", nacin placila: " + racun.getNacin_placila());
            out.append("\n\n\n");
            
            
            
            //----------------------------
			//--------Criteria API--------
            //----------------------------
            
            // iskanje uporabnika z določenim imenom in priimkom
            CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Uporabnik> cq = cb.createQuery(Uporabnik.class);
			Root<Uporabnik> uporabnik = cq.from(Uporabnik.class);
			cq.select(uporabnik).where(cb.equal(uporabnik.get("ime"), "Naum"), cb.equal(uporabnik.get("priimek"), "Gjorgjeski"));
			TypedQuery<Uporabnik> query4 = em.createQuery(cq);
			Uporabnik user = query4.getSingleResult();
			out.append("Criteria API iskanje uporabnika po imenu\n");
			out.append(String.format("ime: %s, priimek: %s, id: %s, uporabniski ime: %s, mail: %s", user.getIme(), 
					user.getPriimek(), user.getId(), user.getUporabnisko_ime(), user.getE_mail()));
			out.append("\n\n\n");
			
			// štetje uporabnikov
			CriteriaQuery criteriaQuery = cb.createQuery();
			Root u = criteriaQuery.from(Uporabnik.class);
			criteriaQuery.select(cb.count(u));
			javax.persistence.Query query5 = em.createQuery(criteriaQuery);
			java.lang.Long cnt = (java.lang.Long)query5.getSingleResult();
			out.append("Stevilo uporabnikov: " + cnt);
			out.append("\n\n\n");
			
			//za vsakega uporabnika izpisi vsa narocila
			CriteriaQuery criteriaQuery2 = cb.createQuery();
			Root uporabnik2 = criteriaQuery2.from(Uporabnik.class);
			Root narocilo = criteriaQuery2.from(Narocilo.class);
			criteriaQuery2.multiselect(uporabnik2, narocilo);
			criteriaQuery2.where(cb.equal(uporabnik2.get("id"), narocilo.get("id")));
			javax.persistence.Query query10 = em.createQuery(criteriaQuery2);
			List<Object[]> result = (List<Object[]>)query10.getResultList();
			out.append(String.format("Uporabniki in njegova narocila\n"));
			for (Object[] user2 : result) {
            	Uporabnik usr= (Uporabnik)user2[0];
            	out.append(String.format("Ime: %s, Preiimek: %s:%n", usr.getIme(), usr.getPriimek()));
            	Narocilo n = (Narocilo)user2[1];
        		out.append(String.format("Id: %s, Prevzem: %s, Lokacija: %s, Racin_id: %s%n", 
        				n.getId(), n.getNacin_prevzema(), n.getLokacija_Dostave(), n.getRacun().getId()));
            	out.append("\n");
			}
			
			
			
       
        }catch(Exception e) {
			e.printStackTrace();
		}
		finally {
            out.close();
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}



}
