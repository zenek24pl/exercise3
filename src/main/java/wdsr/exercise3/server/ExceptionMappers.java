package wdsr.exercise3.server;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.JsonMappingException;

public class ExceptionMappers  {
	
	@Provider
	public static class JsonMappingExceptionMapper implements ExceptionMapper<JsonMappingException> { 
	    @Override
	    public Response toResponse(JsonMappingException ex) {
	        return Response.status(Response.Status.BAD_REQUEST).entity("Malformed input").build();
	    }
	}
	
	@Provider
	public static class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> { 
	    @Override
	    public Response toResponse(NotFoundException ex) {
	        return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build();
	    }
	}	
}