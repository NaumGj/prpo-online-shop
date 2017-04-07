package si.fri.prpo.izjeme;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class NeustreznoPorociloException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NeustreznoPorociloException(String message) {
        super(message);
    }
}
