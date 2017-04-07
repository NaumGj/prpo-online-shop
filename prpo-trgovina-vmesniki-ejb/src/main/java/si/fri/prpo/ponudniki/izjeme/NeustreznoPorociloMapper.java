package si.fri.prpo.ponudniki.izjeme;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import si.fri.prpo.izjeme.NeustreznoPorociloException;
import si.fri.prpo.izjeme.odgovor.ErrorResponse;

@Provider
public class NeustreznoPorociloMapper implements ExceptionMapper<NeustreznoPorociloException>{

	@Override
	public Response toResponse(NeustreznoPorociloException exception) {
		return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(Response.Status.BAD_REQUEST.getStatusCode(), Response.Status.BAD_REQUEST.getStatusCode()*1000 + 1, exception.getMessage())).build();
	}

}
