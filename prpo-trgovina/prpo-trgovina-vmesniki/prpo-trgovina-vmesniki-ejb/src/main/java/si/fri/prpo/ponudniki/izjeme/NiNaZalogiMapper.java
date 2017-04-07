package si.fri.prpo.ponudniki.izjeme;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import si.fri.prpo.izjeme.NiNaZalogiException;
import si.fri.prpo.izjeme.odgovor.ErrorResponse;

@Provider
public class NiNaZalogiMapper implements ExceptionMapper<NiNaZalogiException>{

	@Override
	public Response toResponse(NiNaZalogiException exception) {
		return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(Response.Status.BAD_REQUEST.getStatusCode(), Response.Status.BAD_REQUEST.getStatusCode()*1000 + 2, exception.getMessage())).build();
	}

}
