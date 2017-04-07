package si.fri.prpo.izjeme;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class IzdelekNotFoundException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IzdelekNotFoundException(String message) {
        super(message);
    }
}

