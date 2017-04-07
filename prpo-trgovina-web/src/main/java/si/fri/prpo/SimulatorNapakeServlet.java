package si.fri.prpo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//ID, konstruktorje, kje naredim connection?

/**
 * Servlet implementation class SimulatorNapakeServlet
 */
public class SimulatorNapakeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SimulatorNapakeServlet() {
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
		try{
			conn.setAutoCommit(false);	//nastavi auto-commit na false
			
			//Ustvari nov pravilen izdelek in ga vstavi
			IzdelekJDBC izd1 = new IzdelekJDBC();
			izd1.setIme("Testni izdelek 1");
			izd1.setKategorija("Testni izdelki");
			izd1.setOpis("Testni izdelki nimajo opisa.");
			izd1.setCena(2.5);
			izd1.setZaloga(4);
			izdelekDao.vstavi(izd1);
			
			//Ustvari nepravilen izdelek, kateremu ime je nastavljeno na null,
			//v bazi pa to polje prepoveduje vrednost null (NOT NULL)
			IzdelekJDBC izd2 = new IzdelekJDBC();
			izd2.setIme(null);
			izd2.setKategorija("Testni izdelki");
			izd2.setOpis("Testni izdelki nimajo opisa.");
			izd2.setCena(3.5);
			izd2.setZaloga(2);
			izdelekDao.vstavi(izd2);
			
			//committaj vse spremembe, torej vstavi 2 izdelka hkrati
			conn.commit();
		} catch(Exception e) {
			try{
				//ker drugi izdelek ni pravilen, bomo ujeli izjemo in razveljavili spremembe,
				//torej ne bo dodan noben izdelek
				conn.rollback();
			}catch(SQLException s){
				System.err.println("Rollback ni bil narejen!");
				s.printStackTrace();
			}finally{
				//vrni auto-commit na true in zapri konekcijo
				try{
					if(conn != null){
						conn.setAutoCommit(true);
						conn.close();
					}
				}catch(SQLException s2) {
					System.err.println("Ne morem zapreti povezavo.");
					s2.printStackTrace();
				}
			} 
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
