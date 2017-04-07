package si.fri.prpo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Implementacija vmesnika BaseDao za izdelke
 * @author skupina05
 *
 */
public class IzdelekDaoImpl implements BaseDao{
	
	private Connection connection;	//konekcija, ki jo rabimo za vse operacije nad bazo

	public Connection getConnection() {
		try{
			Context initCtx = new InitialContext();
	        DataSource ds = (DataSource) initCtx.lookup("java:jboss/datasources/prpoDS");
	        this.connection = ds.getConnection();
		}catch(NamingException e){
			System.err.println("JDBC vir ne obstaja.");
			e.printStackTrace();
		}catch (SQLException e) {
			System.err.println("Data Source ni vrnil konekcijo.");
			e.printStackTrace();
		}
		
        return this.connection;
	}
	
	public IzdelekJDBC vrni(int id){
		PreparedStatement ps = null;	//za poizvedbo uporabimo PreparedStatement
		try{
			String sql = "select * from izdelek where id = ?";
			ps = this.connection.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			// obdelava rezultatov
			if(rs.next()){
				// ustvarimo objekt tipa Izdelek in ga napolnimo s podatki iz ResultSet-a
				IzdelekJDBC izd = new IzdelekJDBC();
				izd.setId(rs.getInt("id"));
				izd.setIme(rs.getString("ime"));
				izd.setKategorija(rs.getString("kategorija"));
				izd.setOpis(rs.getString("opis"));
				izd.setCena(rs.getDouble("cena"));
				izd.setZaloga(rs.getInt("zaloga"));
				return izd;
			// nadaljna obdelava rezultatov
			} else {
				System.out.println("Ne najdem izdelka.");
			}
		}catch(SQLException e){
			System.err.println("Zgodila se je napaka pri izvrsevanju poizvedbe.");
			e.printStackTrace();
		}finally{
			if(ps != null){
				try {
					ps.close();
				} catch (SQLException e) {
					System.err.println("Ne morem zapreti Statement.");
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
	
	
	public void vstavi(Entiteta ent) throws SQLException{
		IzdelekJDBC izd = (IzdelekJDBC)ent;		//cast-amo entiteto v izdelek
		PreparedStatement ps = null;	//za vstavljanje v bazo uporabimo PreparedStatement
		String sql = "INSERT INTO prpo.izdelek (Ime, Kategorija, Opis, Cena, Zaloga) VALUES (?, ?, ?, ?, ?);";
		try{
			System.out.println(this.connection.getAutoCommit());
			ps = this.connection.prepareStatement(sql);
			// napolnimo PreparedStatement-a s podatki objekta tipa Izdelek in ga vstavimo v bazo
			ps.setString(1, izd.getIme());
			ps.setString(2, izd.getKategorija());
			ps.setString(3, izd.getOpis());
			ps.setDouble(4, izd.getCena());
			ps.setInt(5, izd.getZaloga());
			ps.executeUpdate();
		} catch(SQLException e){
			System.err.println("Zgodila se je napaka pri vstavljanju izdelka.");
			throw e;
		}
		if(ps != null){
			try {
				ps.close();
			} catch (SQLException e) {
				System.err.println("Ne morem zapreti Statement.");
				e.printStackTrace();
			}
		}
	}
	
	public void odstrani(int id){
		PreparedStatement ps = null;	//za odstranjevanje iz baze uporabimo PreparedStatement
		try{
			String sql = "DELETE FROM prpo.izdelek where id = ?";
			ps = this.connection.prepareStatement(sql);
			ps.setInt(1, id); 
			ps.executeUpdate();
		}catch(SQLException e){
			System.err.println("Zgodila se je napaka pri odstranjevanju izdelka.");
			e.printStackTrace();
		}finally{
			 if(ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					System.err.println("Ne morem zapreti Statement.");
					e.printStackTrace();
				}
		}
	}
	
	public void posodobi(Entiteta ent){
		IzdelekJDBC izd = (IzdelekJDBC)ent;		//cast-amo entiteto v izdelek
		PreparedStatement ps = null;	//za posodabljanje izdelka uporabimo PreparedStatement
		try{
			String sql = "UPDATE prpo.izdelek SET ime = ?, kategorija = ?, opis = ?, cena = ?, zaloga = ? WHERE id = ?";
			ps = this.connection.prepareStatement(sql);
			ps.setInt(6, izd.getId());	//entiteto ki jo moramo posodobiti najdemo preko ID-ja
			ps.setString(1, izd.getIme());
			ps.setString(2, izd.getKategorija());
			ps.setString(3, izd.getOpis());
			ps.setDouble(4, izd.getCena());
			ps.setInt(5, izd.getZaloga());
			ps.executeUpdate();
		}catch(SQLException e){
			System.err.println("Zgodila se je napaka pri posodabljanju izdelka.");
			e.printStackTrace();
		}finally{
			 if(ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					System.err.println("Ne morem zapreti Statement.");
					e.printStackTrace();
				}
		}
	}
	
	public List<Entiteta> vrniVse(){
		PreparedStatement ps = null;	//za poizvedbo uporabimo PreparedStatement
		List<Entiteta> vsiIzdelki = new LinkedList<Entiteta>();	//seznam izdelkov ki ga bomo vrnili
		try{
			String sql = "select * from prpo.izdelek";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			//obdelava rezultatov
			while(rs.next()){
				// za vsak vnos v ResultSet ustvarimo objekt tipa Izdelek in ga napolnimo s podatki
				IzdelekJDBC izd = new IzdelekJDBC();
				izd.setId(rs.getInt("id"));
				izd.setIme(rs.getString("ime"));
				izd.setOpis(rs.getString("opis"));
				izd.setKategorija(rs.getString("kategorija"));
				izd.setCena(rs.getDouble("cena"));
				izd.setZaloga(rs.getInt("zaloga"));
				//na koncu izdelek dodam v seznam izdelkov
				vsiIzdelki.add((Entiteta)izd);
			}
		}catch(SQLException e){
			System.err.println("Zgodila se je napaka pri izvrsevanju poizvedbe.");
			e.printStackTrace();
		}finally{
			if(ps != null){
				try {
					ps.close();
				} catch (SQLException e) {
					System.err.println("Ne morem zapreti Statement.");
					e.printStackTrace();
				}
			}
		}
		return vsiIzdelki;
	}

}
