package si.fri.prpo.izjeme;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class NiNaZalogiException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NiNaZalogiException(String message) {
        super(message);
    }
}
