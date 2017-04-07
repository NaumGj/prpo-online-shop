package si.fri.prpo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IzdelekServlet
 */
public class IzdelekServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IzdelekServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		IzdelekDaoImpl izdelekDao = new IzdelekDaoImpl();
		Connection conn = izdelekDao.getConnection();	//pridobi konekcijo

		//Ustvari nov izdelek in ga vstavi v bazo
		IzdelekJDBC izdelek = new IzdelekJDBC();
		izdelek.setIme("Rokometna zoga");
		izdelek.setKategorija("Zoge");
		izdelek.setOpis("Rokometna zoga za otroke, velikost 2");
		izdelek.setCena(17.87);
		izdelek.setZaloga(3);
		try{
			izdelekDao.vstavi(izdelek);
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		//Pridobi izdelka z ID-jem 29 in ga spremeni
		int id = 29;
		izdelek = izdelekDao.vrni(id);
		izdelek.setIme("Updatirana nogometna zoga");
		izdelek.setKategorija("Updatirane zoge");
		izdelek.setOpis("Poceni updatirane nogometne zoge");
		izdelek.setCena(1.24);
		izdelek.setZaloga(10);
		izdelekDao.posodobi((Entiteta)izdelek);
		
		//Pridobi vse izdelke v bazi in vsakega zapisi v svojo vrstico v konzoli
		List<Entiteta> vsiIzdelki = izdelekDao.vrniVse();
		for(Entiteta ent : vsiIzdelki){
			izdelek = (IzdelekJDBC)ent;
			System.out.print("ID: " + izdelek.getId() + "; ");
			System.out.print("Ime: " + izdelek.getIme() + "; ");
			System.out.print("Kategorija: " + izdelek.getKategorija() + "; ");
			System.out.print("Opis: " + izdelek.getOpis() + "; ");
			System.out.print("Cena: " + izdelek.getCena() + "; ");
			System.out.println("Zaloga: " + izdelek.getZaloga() + ";");
		}
		
// koda za odstranjevanje izdelka
//		izdelekDao.odstrani(4);
		
		//zapri konekcijo
		try{
			if(conn != null) 
				conn.close(); 
		}catch(SQLException e){
			System.err.println("Ne morem zapreti povezavo.");
			e.printStackTrace();
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
