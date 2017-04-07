package si.fri.prpo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Vmesnik ki definira osnovne operacije nad entitetami ki omogočajo branje in pisanje 
 * v bazo
 * @author skupina05
 *
 */
public interface BaseDao {

	/**
	 * @return konekcija pridobljena iz pool-a
	 */
	public Connection getConnection();
	/**
	 * @param  id ID entitete
	 * @return entiteto z danim id-jem
	 */
	public Entiteta vrni(int id);
	/**
	 * Vstavi entiteto v bazi
	 * @param  ent entiteto katero moramo vstaviti
	 */
	public void vstavi(Entiteta ent) throws SQLException;
	/**
	 * Odstrani entiteto v bazi
	 * @param id ID entitete ki jo moramo odstraniti
	 */
	public void odstrani(int id);
	/**
	 * Posodobi entiteto v bazi
	 * @param ent entiteto katero moramo posodobiti
	 */
	public void posodobi(Entiteta ent);
	/**
	 * @return vse entitete iz baze
	 */
	public List<Entiteta> vrniVse();
	
}
